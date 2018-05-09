package space.tritin.jetbrainsinternshipstepik.ui.adapters.coursesrecycler

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import space.tritin.jetbrainsinternshipstepik.mvp.models.StepikCourseItem
import space.tritin.jetbrainsinternshipstepik.ui.adapters.coursesrecycler.delegate.CourseDelegateAdapter
import space.tritin.jetbrainsinternshipstepik.ui.adapters.coursesrecycler.delegate.EndDelegateAdapter
import space.tritin.jetbrainsinternshipstepik.ui.adapters.coursesrecycler.delegate.LoadingDelegateAdapter
import java.util.ArrayList

/**
 * @author varivoda.s
 * created 03.05.2018.
 */
class CoursesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()

    private val clickSubject = PublishSubject.create<StepikCourseItem>()
    val clickEvent: Observable<StepikCourseItem> = clickSubject


    private val loadingItem = object : ViewType {
        override fun getViewType(): Int {
            return AdapterConstants.LOADING
        }
    }

    private val endItem = object : ViewType {
        override fun getViewType(): Int {
            return AdapterConstants.END
        }
    }

    init {
        delegateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
        delegateAdapters.put(AdapterConstants.COURSE, CourseDelegateAdapter())
        delegateAdapters.put(AdapterConstants.END, EndDelegateAdapter())
        items = ArrayList()
        items.add(loadingItem)
    }

    fun addCourses(courses: List<StepikCourseItem>) {
        // сначала убираем загрузку
        val initPosition = items.size - 1
        items.removeAt(initPosition)
        notifyItemRemoved(initPosition)

        // вставляем курсы и загрузку в конец
        items.addAll(courses)
        items.add(loadingItem)
        notifyItemRangeChanged(initPosition, items.size + 1 /* plus loading item */)
    }

    fun clearAndAddCourses(courses: List<StepikCourseItem>){
        items.clear()
        items.addAll(courses)
        items.add(loadingItem)
        notifyItemRangeRemoved(0, items.size)
    }

    fun clear(){
        notifyItemRangeRemoved(0, getLastPosition())
        items.clear()
        items.add(loadingItem)

    }

    fun listEnd(){
        val initPosition = items.size - 1
        items.removeAt(initPosition)
        items.add(endItem)
        notifyItemChanged(initPosition)
    }

    fun addItem(course: StepikCourseItem){
        val initPosition = getLastPosition()
        items.removeAt(initPosition)
        notifyItemRemoved(initPosition)

        items.add(course)
        notifyItemInserted(getLastPosition())

        items.add(endItem)
        notifyItemInserted(getLastPosition())
    }

    fun removeItem(course: StepikCourseItem){
        items.remove(course)
        notifyDataSetChanged()
    }

    fun updateItem(course: StepikCourseItem){
        val ind = items.indexOf(course)
        notifyDataSetChanged()
    }

    fun getCourses(): List<StepikCourseItem> = items
            .filter { it.getViewType() == AdapterConstants.COURSE }
            .map { it as StepikCourseItem }

    private fun getLastPosition() = if (items.lastIndex == -1) 0 else items.lastIndex

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, this.items[position])

        if (holder is CourseDelegateAdapter.TurnsViewHolder){
            holder.favoriteView?.setOnClickListener {
                clickSubject.onNext(items[position] as StepikCourseItem)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(viewType).onCreateViewHolder(parent)
    }

    override fun getItemCount() = items.size


    override fun getItemViewType(position: Int) = this.items[position].getViewType()


}