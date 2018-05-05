package space.tritin.jetbrainsinternshipstepik.mvp.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import space.tritin.jetbrainsinternshipstepik.StepikApplication
import space.tritin.jetbrainsinternshipstepik.common.CoursesManager
import space.tritin.jetbrainsinternshipstepik.mvp.models.StepikCourses
import space.tritin.jetbrainsinternshipstepik.mvp.views.RecyclerFragmentView

/**
 * @author varivoda.s
 * created 03.05.2018.
 */
@InjectViewState
class SearchFragmentPresenter : MvpPresenter<RecyclerFragmentView>(), RequestCourses {

    private var stepikCourses: StepikCourses? = null
    private val coursesManager by lazy { CoursesManager() }
    private var query = ""

    init {
        StepikApplication.graph.inject(this)
    }


    override fun requestCoursesNew() {
        val subscription = coursesManager.getCourses(query, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { retrievedCourses ->
                            stepikCourses = retrievedCourses
                            viewState.newList(stepikCourses!!.courses)
                        },
                        { e ->
                            viewState.endList()
                            Log.e(javaClass.simpleName, "Error: ${e.message ?: "Error"}")
                        })

        viewState.addSubscription(subscription)
    }

    override fun requestCoursesAdd() {
        val subscription = coursesManager.getCourses(query, stepikCourses?.next ?: 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { retrievedCourses ->
                            stepikCourses = retrievedCourses
                            viewState.addToList(stepikCourses!!.courses)

                        },
                        { e ->
                            viewState.endList()
                            Log.e(javaClass.simpleName, "Error: ${e.message ?: "Error"}")
                        })
        viewState.addSubscription(subscription)
    }

    fun restore(){
        if (stepikCourses != null){
            viewState.newList(stepikCourses!!.courses)
            Log.d(javaClass.simpleName, "Resore List")
        } else {
            requestCoursesNew()
            Log.d(javaClass.simpleName, "Load New List")
        }
    }


    fun search(query: String){
        viewState.clear()

        this.query = query
        requestCoursesNew()

        Log.d(javaClass.simpleName, "Query: ${query}")
    }

    fun searchClose(){
        query = ""
        requestCoursesNew()
    }



}