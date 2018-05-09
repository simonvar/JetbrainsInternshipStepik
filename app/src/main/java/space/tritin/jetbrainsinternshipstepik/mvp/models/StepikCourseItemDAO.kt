package space.tritin.jetbrainsinternshipstepik.mvp.models

import android.util.Log
import com.reactiveandroid.query.Select

/**
 * <p>Date: 05.05.18</p>
 * @author Simon
 */
class StepikCourseItemDAO {

    fun addToFavorite(courseItem: StepikCourseItem) {
        courseItem.save()
        Log.d(javaClass.simpleName, "Save to DB ${courseItem.title}")
    }

    fun removeFromFavorite(courseItem: StepikCourseItem) {
        courseItem.delete()
        Log.d(javaClass.simpleName, "Remove from DB ${courseItem.title}")
    }

    fun loadAllFavorite(): MutableList<StepikCourseItem>? = Select.from(StepikCourseItem::class.java).fetch()

    fun isFavorite(id: Long): Boolean {
        val course = Select.from(StepikCourseItem::class.java).where("id = ?", id).fetchSingle()
        return course != null
    }


}