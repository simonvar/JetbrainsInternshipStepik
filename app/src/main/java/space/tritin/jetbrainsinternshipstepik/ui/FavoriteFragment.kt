package space.tritin.jetbrainsinternshipstepik.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_favorite.*
import space.tritin.jetbrainsinternshipstepik.R
import space.tritin.jetbrainsinternshipstepik.common.DatabaseManager
import space.tritin.jetbrainsinternshipstepik.mvp.presenters.FavoriteFragmentPresenter
import space.tritin.jetbrainsinternshipstepik.ui.adapters.coursesrecycler.CoursesAdapter
import space.tritin.jetbrainsinternshipstepik.ui.base.RecyclerFragment


/**
 * <p>Date: 02.05.18</p>
 * @author Simon
 */
class FavoriteFragment : RecyclerFragment() {

    @InjectPresenter
    lateinit var presenter: FavoriteFragmentPresenter


    override val layoutId = R.layout.fragment_favorite
    override val recyclerId = R.id.favorite_recycler
    override val recyclerAdapter = CoursesAdapter()
    override val recyclerLayoutManager = LinearLayoutManager(context)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipe_refresh.setOnRefreshListener {
            presenter.requestCoursesNew()
            swipe_refresh.isRefreshing = false
        }

        DatabaseManager.databaseEvent.subscribe {
            if (DatabaseManager.isFavorite(it))  {
                recyclerAdapter.addItem(it)
                Log.d(javaClass.simpleName, "Add item")
            } else {
                recyclerAdapter.removeItem(it)
                Log.d(javaClass.simpleName, "Remove item")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.requestCoursesNew()
    }


}