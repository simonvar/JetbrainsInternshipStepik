package space.tritin.jetbrainsinternshipstepik.ui

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import kotlinx.android.synthetic.main.fragment_search.*
import space.tritin.jetbrainsinternshipstepik.R
import kotlinx.android.synthetic.main.fragment_search.view.*
import space.tritin.jetbrainsinternshipstepik.ui.listeners.InfiniteScrollListener
import space.tritin.jetbrainsinternshipstepik.ui.adapters.coursesrecycler.CoursesAdapter
import android.view.inputmethod.InputMethodManager
import com.arellomobile.mvp.presenter.InjectPresenter
import space.tritin.jetbrainsinternshipstepik.common.DatabaseManager
import space.tritin.jetbrainsinternshipstepik.mvp.presenters.SearchFragmentPresenter
import space.tritin.jetbrainsinternshipstepik.ui.base.RecyclerFragment


/**
 * <p>Date: 02.05.18</p>
 * @author Simon
 */
class SearchFragment : RecyclerFragment() {

    @InjectPresenter
    lateinit var presenter: SearchFragmentPresenter

    override val layoutId = R.layout.fragment_search
    override val recyclerId = R.id.search_recycler
    override val recyclerAdapter = CoursesAdapter()
    override val recyclerLayoutManager = LinearLayoutManager(context)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        search_recycler.apply {
            search_recycler.addOnScrollListener(InfiniteScrollListener({ presenter.requestCoursesAdd() }, recyclerLayoutManager))
        }

        swipe_refresh.setOnRefreshListener {
            presenter.requestCoursesNew()
            swipe_refresh.isRefreshing = false
        }

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                val query = search_view.query.toString()
                presenter.search(query)
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                val currentFocus = activity?.currentFocus
                if (currentFocus != null) {
                    val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(currentFocus.windowToken, 0)
                }
                return true
            }
        })

        search_view.setOnCloseListener {
            presenter.searchClose()
            true
        }

        DatabaseManager.databaseEvent.subscribe {
            recyclerAdapter.updateItem(it)
            Log.d(javaClass.simpleName, "update view ${it.title}")
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.restore()
    }



}