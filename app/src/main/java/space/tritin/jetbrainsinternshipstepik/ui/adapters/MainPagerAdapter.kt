package space.tritin.jetbrainsinternshipstepik.ui.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import space.tritin.jetbrainsinternshipstepik.ui.FavoriteFragment
import space.tritin.jetbrainsinternshipstepik.ui.SearchFragment

/**
 * <p>Date: 02.05.18</p>
 * @author Simon
 */
class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    companion object {
        private const val MAIN_FRAGMENTS_COUNT = 2
    }

    override fun getItem(position: Int): Fragment {

        val fragment = when (position){
            0 -> SearchFragment()
            1 -> FavoriteFragment()
            else -> SearchFragment()
        }

        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> "Search"
        1 -> "Favorite"
        else -> "Search"
    }

    override fun getCount() = MAIN_FRAGMENTS_COUNT
}