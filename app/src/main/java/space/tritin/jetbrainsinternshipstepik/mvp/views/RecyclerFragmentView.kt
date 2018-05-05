package space.tritin.jetbrainsinternshipstepik.mvp.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import io.reactivex.disposables.Disposable
import space.tritin.jetbrainsinternshipstepik.mvp.models.StepikCourseItem

/**
 * <p>Date: 05.05.18</p>
 * @author Simon
 */
@StateStrategyType(value = AddToEndSingleStrategy::class)
interface RecyclerFragmentView : MvpView {
    /**
     * Полностью обновить список
     * */
    fun newList(list: List<StepikCourseItem>)

    /**
     * Добавить к списку новые элементы
     * */
    fun addToList(list: List<StepikCourseItem>)

    /**
     * Добавить элемент конца списка
     * */
    fun endList()

    /**
     * Очистить список
     * */
    fun clear()


    fun addSubscription(subscription: Disposable)

}