package space.tritin.jetbrainsinternshipstepik.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.fragment_search.*
import space.tritin.jetbrainsinternshipstepik.R
import kotlinx.android.synthetic.main.fragment_search.view.*
import space.tritin.jetbrainsinternshipstepik.common.InfiniteScrollListener
import space.tritin.jetbrainsinternshipstepik.ui.adapters.coursesrecycler.CoursesAdapter
import space.tritin.jetbrainsinternshipstepik.ui.base.RxMvpFragment


/**
 * <p>Date: 02.05.18</p>
 * @author Simon
 */
class SearchFragment : RxMvpFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.search_view.clearFocus()

        val linearLayout = LinearLayoutManager(context)
        search_recycler.apply {
            setHasFixedSize(true)
            layoutManager = linearLayout
            clearOnScrollListeners()
            search_recycler.addOnScrollListener(InfiniteScrollListener({ requestCourses() }, linearLayout))
        }

        initAdapter()

    }

    private fun requestCourses(){

    }

    private fun initAdapter() {
        if (search_recycler.adapter == null) {
            search_recycler.adapter = CoursesAdapter()
        }
    }


}