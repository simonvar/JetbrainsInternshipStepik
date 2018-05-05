package space.tritin.jetbrainsinternshipstepik.ui

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import kotlinx.android.synthetic.main.fragment_search.*
import space.tritin.jetbrainsinternshipstepik.R
import kotlinx.android.synthetic.main.fragment_search.view.*
import space.tritin.jetbrainsinternshipstepik.ui.listeners.InfiniteScrollListener
import space.tritin.jetbrainsinternshipstepik.ui.adapters.coursesrecycler.CoursesAdapter
import space.tritin.jetbrainsinternshipstepik.ui.base.RxMvpFragment
import android.view.inputmethod.InputMethodManager
import com.arellomobile.mvp.presenter.InjectPresenter
import io.reactivex.disposables.Disposable
import space.tritin.jetbrainsinternshipstepik.mvp.models.StepikCourseItem
import space.tritin.jetbrainsinternshipstepik.mvp.presenters.SearchFragmentPresenter
import space.tritin.jetbrainsinternshipstepik.mvp.views.RecyclerFragmentView


/**
 * <p>Date: 02.05.18</p>
 * @author Simon
 */
class SearchFragment : RxMvpFragment(), RecyclerFragmentView {

    @InjectPresenter
    lateinit var presenter: SearchFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val linearLayout = LinearLayoutManager(context)
        search_recycler.apply {
            setHasFixedSize(true)
            layoutManager = linearLayout
            clearOnScrollListeners()
            search_recycler.addOnScrollListener(InfiniteScrollListener({ presenter.requestCoursesAdd() }, linearLayout))
            adapter = CoursesAdapter()
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
    }

    override fun onResume() {
        super.onResume()
        presenter.restore()
    }

    override fun clear() {
        (search_recycler.adapter as CoursesAdapter).clear()
    }

    override fun newList(list: List<StepikCourseItem>) {
        (search_recycler.adapter as CoursesAdapter).clearAndAddCourses(list)
    }

    override fun addToList(list: List<StepikCourseItem>) {
        (search_recycler.adapter as CoursesAdapter).addCourses(list)
    }

    override fun addSubscription(subscription: Disposable) {
        subscriptions.add(subscription)
    }

    override fun endList() {
        (search_recycler.adapter as CoursesAdapter).listEnd()
    }



}