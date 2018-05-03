package space.tritin.jetbrainsinternshipstepik.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import space.tritin.jetbrainsinternshipstepik.R

/**
 * <p>Date: 02.05.18</p>
 * @author Simon
 */
class FavoriteFragment : MvpAppCompatFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)

        return view
    }

}