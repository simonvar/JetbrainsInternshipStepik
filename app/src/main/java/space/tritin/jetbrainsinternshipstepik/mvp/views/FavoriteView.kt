package space.tritin.jetbrainsinternshipstepik.mvp.views

import com.arellomobile.mvp.MvpView
import space.tritin.jetbrainsinternshipstepik.mvp.models.StepikCourseItem

/**
 * <p>Date: 05.05.18</p>
 * @author Simon
 */
interface FavoriteView : MvpView {

    fun addFavorite(stepikCourseItem: StepikCourseItem, from: Int)

    fun removeFavorite(stepikCourseItem: StepikCourseItem, from: Int)


}