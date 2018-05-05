package space.tritin.jetbrainsinternshipstepik.ui.base

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*
import space.tritin.jetbrainsinternshipstepik.R
import space.tritin.jetbrainsinternshipstepik.ui.adapters.coursesrecycler.CoursesAdapter
import space.tritin.jetbrainsinternshipstepik.ui.listeners.InfiniteScrollListener

/**
 * <p>Date: 05.05.18</p>
 * @author Simon
 */
abstract class RecyclerFragment : RxMvpFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayout = LinearLayoutManager(context)
        recycler.apply {
            setHasFixedSize(true)
            layoutManager = linearLayout
            clearOnScrollListeners()
        }

        initAdapter()
    }

    private fun initAdapter() {
        if (recycler.adapter == null) {
            recycler.adapter = adapter
        }
    }


    abstract val layoutId: Int

    abstract val recycler: RecyclerView

    abstract val adapter: CoursesAdapter

    abstract fun requestCourses()

}