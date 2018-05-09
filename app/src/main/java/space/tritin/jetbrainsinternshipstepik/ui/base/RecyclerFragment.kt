package space.tritin.jetbrainsinternshipstepik.ui.base

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import io.reactivex.disposables.Disposable
import space.tritin.jetbrainsinternshipstepik.common.DatabaseManager
import space.tritin.jetbrainsinternshipstepik.mvp.models.StepikCourseItem
import space.tritin.jetbrainsinternshipstepik.mvp.views.RecyclerFragmentView
import space.tritin.jetbrainsinternshipstepik.ui.adapters.coursesrecycler.CoursesAdapter

/**
 * <p>Date: 05.05.18</p>
 * @author Simon
 */
abstract class RecyclerFragment : RxMvpFragment(), RecyclerFragmentView {

    private var subscribeClickFavorite: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(recyclerId).apply {
            setHasFixedSize(true)
            layoutManager = recyclerLayoutManager
            clearOnScrollListeners()
            adapter = recyclerAdapter

            subscribeClickFavorite = recyclerAdapter.clickEvent
                    .subscribe({
                        Log.d(javaClass.simpleName, "click on favorite")
                        DatabaseManager.favoriteClick(it)
                    })
        }
    }


    override fun newList(list: List<StepikCourseItem>) {
        recyclerAdapter.clearAndAddCourses(list)
    }

    override fun endList() {
        recyclerAdapter.listEnd()
    }

    override fun clear() {
        recyclerAdapter.clear()
    }

    override fun addToList(list: List<StepikCourseItem>) {
        recyclerAdapter.addCourses(list)
    }

    override fun addSubscription(subscription: Disposable) {
        subscriptions.add(subscription)
    }

    abstract val recyclerLayoutManager: RecyclerView.LayoutManager

    abstract val layoutId: Int

    abstract val recyclerId: Int

    abstract val recyclerAdapter: CoursesAdapter

}