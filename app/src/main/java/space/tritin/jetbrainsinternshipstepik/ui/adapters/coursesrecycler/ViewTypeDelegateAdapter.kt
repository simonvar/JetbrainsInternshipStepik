package space.tritin.jetbrainsinternshipstepik.ui.adapters.coursesrecycler

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * <p>Date: 04.05.18</p>
 * @author Simon
 */
interface ViewTypeDelegateAdapter {
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType)
}