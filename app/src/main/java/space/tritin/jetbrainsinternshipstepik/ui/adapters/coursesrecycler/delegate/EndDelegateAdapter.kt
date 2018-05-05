package space.tritin.jetbrainsinternshipstepik.ui.adapters.coursesrecycler.delegate

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import space.tritin.jetbrainsinternshipstepik.R
import space.tritin.jetbrainsinternshipstepik.common.inflate
import space.tritin.jetbrainsinternshipstepik.ui.adapters.coursesrecycler.ViewType
import space.tritin.jetbrainsinternshipstepik.ui.adapters.coursesrecycler.ViewTypeDelegateAdapter

/**
 * <p>Date: 05.05.18</p>
 * @author Simon
 */
class EndDelegateAdapter: ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup) = TurnsViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
    }

    class TurnsViewHolder(parent: ViewGroup) :
            RecyclerView.ViewHolder(parent.inflate(R.layout.item_end))
}