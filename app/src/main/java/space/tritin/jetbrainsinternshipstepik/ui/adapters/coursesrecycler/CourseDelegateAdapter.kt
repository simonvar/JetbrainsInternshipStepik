package space.tritin.jetbrainsinternshipstepik.ui.adapters.coursesrecycler

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_course.view.*
import space.tritin.jetbrainsinternshipstepik.R
import space.tritin.jetbrainsinternshipstepik.common.inflate
import space.tritin.jetbrainsinternshipstepik.common.loadImg
import space.tritin.jetbrainsinternshipstepik.mvp.models.CourseModel

/**
 * <p>Date: 04.05.18</p>
 * @author Simon
 */
class CourseDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup) = TurnsViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as TurnsViewHolder
        holder.bind(item as CourseModel)
    }

    class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_course)) {
        fun bind(item: CourseModel) = with(itemView){

            itemView.setOnClickListener {
                var url = item.url
                if (!url.startsWith("http://") && !url.startsWith("https://"))
                    url = "http://" + url;

                try {
                    val UrlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    ContextCompat.startActivity(context, UrlIntent, null)
                } catch (e: ActivityNotFoundException) {
                    Snackbar.make(itemView, "No application can handle this request." + " Please install a webbrowser", Snackbar.LENGTH_LONG).show()
                    e.printStackTrace()
                }

            }

            item_course_image.loadImg(item.thumbnail)
            item_course_description.text = item.description
            item_course_title.text = item.title
        }
    }
}