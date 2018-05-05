package space.tritin.jetbrainsinternshipstepik.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_favorite.*
import space.tritin.jetbrainsinternshipstepik.R
import space.tritin.jetbrainsinternshipstepik.mvp.models.StepikCourseItem
import space.tritin.jetbrainsinternshipstepik.mvp.models.StepikCourseItemDAO
import space.tritin.jetbrainsinternshipstepik.mvp.presenters.FavoriteFragmentPresenter
import space.tritin.jetbrainsinternshipstepik.mvp.presenters.RequestCourses
import space.tritin.jetbrainsinternshipstepik.mvp.presenters.SearchFragmentPresenter
import space.tritin.jetbrainsinternshipstepik.mvp.views.RecyclerFragmentView
import space.tritin.jetbrainsinternshipstepik.ui.adapters.coursesrecycler.CoursesAdapter

/**
 * <p>Date: 02.05.18</p>
 * @author Simon
 */
class FavoriteFragment : MvpAppCompatFragment(), RecyclerFragmentView {
    @InjectPresenter
    lateinit var presenter: FavoriteFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val linearLayout = LinearLayoutManager(context)
        favorite_recycler.apply {
            setHasFixedSize(true)
            layoutManager = linearLayout
            adapter = CoursesAdapter()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.restore()
    }


    override fun newList(list: List<StepikCourseItem>) {
        (favorite_recycler.adapter as CoursesAdapter).clearAndAddCourses(list)
    }


    override fun endList() {
        (favorite_recycler.adapter as CoursesAdapter).listEnd()
    }

    override fun clear() {
        (favorite_recycler.adapter as CoursesAdapter).clear()
    }

    override fun addToList(list: List<StepikCourseItem>) {
        //pass
    }

    override fun addSubscription(subscription: Disposable) {
        //pass
    }


}