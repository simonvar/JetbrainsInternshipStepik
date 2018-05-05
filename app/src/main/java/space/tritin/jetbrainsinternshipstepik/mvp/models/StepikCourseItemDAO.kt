package space.tritin.jetbrainsinternshipstepik.mvp.models

import com.reactiveandroid.query.Select

/**
 * <p>Date: 05.05.18</p>
 * @author Simon
 */
class StepikCourseItemDAO {

    fun addToFavorite(courseItem: StepikCourseItem) = courseItem.save()

    fun removeFromFavorite(courseItem: StepikCourseItem) = courseItem.delete()

    fun loadAllFavorite(): MutableList<StepikCourseItem>? = Select.from(StepikCourseItem::class.java).fetch()

    fun isFavorite(id: Int): Boolean{
        val course = Select.from(StepikCourseItem::class.java).where("courseId = ?", id).fetchSingle()
        return course != null
    }


}