package space.tritin.jetbrainsinternshipstepik.ui.adapters.coursesrecycler

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import space.tritin.jetbrainsinternshipstepik.mvp.models.CourseModel
import java.util.ArrayList

/**
 * @author varivoda.s
 * created 03.05.2018.
 */
class CoursesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()


    private val loadingItem = object : ViewType {
        override fun getViewType(): Int {
            return AdapterConstants.LOADING
        }
    }

    init {
        delegateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
        delegateAdapters.put(AdapterConstants.COURSE, CourseDelegateAdapter())
        items = ArrayList()
        items.add(loadingItem)
    }

    fun addCourses(courses: List<CourseModel>) {
        // сначала убираем загрузку
        val initPosition = items.size - 1
        items.removeAt(initPosition)
        notifyItemRemoved(initPosition)

        // вставляем курсы и загрузку в конец
        items.addAll(courses)
        items.add(loadingItem)
        notifyItemRangeChanged(initPosition, items.size + 1 /* plus loading item */)
    }

    fun clearAndAddCourses(courses: List<CourseModel>){
        items.clear()
        notifyItemRangeRemoved(0, getLastPosition())
        items.addAll(courses)
        items.add(loadingItem)
        notifyItemRangeInserted(0, items.size)
    }

    fun getCourses(): List<CourseModel> = items
            .filter { it.getViewType() == AdapterConstants.COURSE }
            .map { it as CourseModel }

    private fun getLastPosition() = if (items.lastIndex == -1) 0 else items.lastIndex

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, this.items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(viewType).onCreateViewHolder(parent)
    }

    override fun getItemCount() = items.size


    override fun getItemViewType(position: Int) = this.items[position].getViewType()


}