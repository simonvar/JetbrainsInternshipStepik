package space.tritin.jetbrainsinternshipstepik.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import space.tritin.jetbrainsinternshipstepik.R
import space.tritin.jetbrainsinternshipstepik.mvp.models.CourseModel

/**
 * @author varivoda.s
 * created 03.05.2018.
 */
class CourseRecyclerAdapter(val list: List<CourseModel>) : RecyclerView.Adapter<CourseRecyclerAdapter.CourseHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_course, parent, false)
        return CourseHolder(v)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: CourseHolder, position: Int) =
            holder.bind(list[position])


    class CourseHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(course: CourseModel){

        }
    }

}