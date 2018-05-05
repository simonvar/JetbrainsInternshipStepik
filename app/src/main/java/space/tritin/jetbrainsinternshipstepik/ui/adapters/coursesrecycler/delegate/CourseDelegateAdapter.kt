package space.tritin.jetbrainsinternshipstepik.ui.adapters.coursesrecycler.delegate

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_course.view.*
import space.tritin.jetbrainsinternshipstepik.R
import space.tritin.jetbrainsinternshipstepik.common.inflate
import space.tritin.jetbrainsinternshipstepik.common.loadImg
import space.tritin.jetbrainsinternshipstepik.mvp.models.StepikCourseItem
import space.tritin.jetbrainsinternshipstepik.mvp.models.StepikCourseItemDAO
import space.tritin.jetbrainsinternshipstepik.mvp.presenters.FavoritePresenter
import space.tritin.jetbrainsinternshipstepik.mvp.presenters.RequestCourses
import space.tritin.jetbrainsinternshipstepik.ui.adapters.coursesrecycler.ViewType
import space.tritin.jetbrainsinternshipstepik.ui.adapters.coursesrecycler.ViewTypeDelegateAdapter

/**
 * <p>Date: 04.05.18</p>
 * @author Simon
 */
class CourseDelegateAdapter(
        val courseItemDAO: StepikCourseItemDAO = StepikCourseItemDAO(),
        val favoritePresenter: FavoritePresenter,
        val from: Int
        ) : ViewTypeDelegateAdapter {


    override fun onCreateViewHolder(parent: ViewGroup) = TurnsViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as TurnsViewHolder
        holder.bind(item as StepikCourseItem)
    }


    inner class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_course)) {
        fun bind(item: StepikCourseItem) = with(itemView){

            item_course_image.loadImg(item.thumbnail)
            item_course_title.text = item.title

            itemView.setOnClickListener {
                val url = "https://stepik.org/course/${item.id}"

                try {
                    val UrlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    ContextCompat.startActivity(context, UrlIntent, null)
                } catch (e: ActivityNotFoundException) {
                    Snackbar.make(itemView, "No application can handle this request." + " Please install a webbrowser", Snackbar.LENGTH_LONG).show()
                    e.printStackTrace()
                }

            }


            item_course_favorite.setOnClickListener {
                changeFavorite(item, itemView)

                if (from == FavoritePresenter.FROM.SEARCH) {
                    updateViewFavorite(item, itemView)
                }
            }


            item.isFavorite = courseItemDAO.isFavorite(item.id)
            updateViewFavorite(item, itemView)
        }

        private fun changeFavorite(item: StepikCourseItem, view: View){
            if (item.isFavorite){
                courseItemDAO.removeFromFavorite(item)
                favoritePresenter.removeFavorite(item, from)
                item.isFavorite = false
            } else {
                item.isFavorite = true
                courseItemDAO.addToFavorite(item)
                favoritePresenter.addFavorite(item, from)
            }
        }

        private fun updateViewFavorite(item: StepikCourseItem, view: View){
            if (item.isFavorite){
                view.item_course_favorite.setImageDrawable(view.resources.getDrawable(R.drawable.ic_star))
            } else {
                view.item_course_favorite.setImageDrawable(view.resources.getDrawable(R.drawable.ic_star_border))
            }
        }
    }
}