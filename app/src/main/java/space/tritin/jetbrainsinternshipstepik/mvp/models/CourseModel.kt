package space.tritin.jetbrainsinternshipstepik.mvp.models

import com.activeandroid.Model
import space.tritin.jetbrainsinternshipstepik.ui.adapters.coursesrecycler.AdapterConstants
import space.tritin.jetbrainsinternshipstepik.ui.adapters.coursesrecycler.ViewType

/**
 * @author varivoda.s
 * created 03.05.2018.
 */
class CourseModel : Model(), ViewType {

    val url: String = ""
    val title: String = ""
    val description: String = ""
    val thumbnail: String = ""

    override fun getViewType(): Int = AdapterConstants.COURSE


}