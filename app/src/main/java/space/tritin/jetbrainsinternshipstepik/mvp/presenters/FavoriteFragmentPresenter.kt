package space.tritin.jetbrainsinternshipstepik.mvp.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.pawegio.kandroid.v
import kotlinx.android.synthetic.main.fragment_favorite.*
import space.tritin.jetbrainsinternshipstepik.StepikApplication
import space.tritin.jetbrainsinternshipstepik.mvp.models.StepikCourseItemDAO
import space.tritin.jetbrainsinternshipstepik.mvp.views.RecyclerFragmentView
import space.tritin.jetbrainsinternshipstepik.ui.adapters.coursesrecycler.CoursesAdapter

/**
 * @author varivoda.s
 * created 03.05.2018.
 */
@InjectViewState
class FavoriteFragmentPresenter : MvpPresenter<RecyclerFragmentView>(), RequestCourses {
    val stepikCourseItemDAO = StepikCourseItemDAO()

    init {
        StepikApplication.graph.inject(this)
    }


    override fun requestCoursesNew() {
        val courses = stepikCourseItemDAO.loadAllFavorite()
        Log.d(javaClass.simpleName, "Request Favorite: ${courses?.size}")
        if (courses != null && courses.isNotEmpty()){
            viewState.newList(courses)
        }
        viewState.endList()
    }

    override fun requestCoursesAdd() {
        //pass
    }
}