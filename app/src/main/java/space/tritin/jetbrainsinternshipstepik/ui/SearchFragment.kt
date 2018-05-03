package space.tritin.jetbrainsinternshipstepik.ui

import android.os.Bundle
import android.view.*
import com.arellomobile.mvp.MvpAppCompatFragment
import space.tritin.jetbrainsinternshipstepik.R
import android.support.v7.widget.SearchView
import kotlinx.android.synthetic.main.fragment_search.view.*


/**
 * <p>Date: 02.05.18</p>
 * @author Simon
 */
class SearchFragment : MvpAppCompatFragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        view.search_view.clearFocus()

        return view
    }


}