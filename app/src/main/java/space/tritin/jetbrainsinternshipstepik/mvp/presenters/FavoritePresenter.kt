package space.tritin.jetbrainsinternshipstepik.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import space.tritin.jetbrainsinternshipstepik.StepikApplication
import space.tritin.jetbrainsinternshipstepik.mvp.models.StepikCourseItem
import space.tritin.jetbrainsinternshipstepik.mvp.models.StepikCourseItemDAO
import space.tritin.jetbrainsinternshipstepik.mvp.views.FavoriteView

/**
 * <p>Date: 05.05.18</p>
 * @author Simon
 */
@InjectViewState
class FavoritePresenter : MvpPresenter<FavoriteView> {
    var from: Int = 0

    companion object {
        const val TAG = "FavoritePresenter"
    }

    object FROM {
        val FAVORITE = 0
        val SEARCH = 1
    }

    constructor()

    constructor(from: Int) {
        this.from = from
    }

    init {
        StepikApplication.graph.inject(this)
    }


    fun addFavorite(stepikCourseItem: StepikCourseItem, from: Int){
        viewState.addFavorite(stepikCourseItem, from)
    }

    fun removeFavorite(stepikCourseItem: StepikCourseItem, from: Int){
        viewState.removeFavorite(stepikCourseItem, from)
    }

}