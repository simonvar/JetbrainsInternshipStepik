package space.tritin.jetbrainsinternshipstepik.mvp.models

import android.os.Parcel
import android.os.Parcelable
import com.reactiveandroid.Model
import com.reactiveandroid.annotation.Column
import com.reactiveandroid.annotation.Table
import space.tritin.jetbrainsinternshipstepik.common.createParcel
import space.tritin.jetbrainsinternshipstepik.ui.adapters.coursesrecycler.AdapterConstants
import space.tritin.jetbrainsinternshipstepik.ui.adapters.coursesrecycler.ViewType
import java.util.*

/**
 * @author varivoda.s
 * created 03.05.2018.
 */
data class StepikCourses(
        var next: Int,
        val courses: List<StepikCourseItem>) : Parcelable {

    companion object {
        @JvmField
        @Suppress("unused")
        val CREATOR = createParcel { StepikCourses(it) }
    }

    protected constructor(parcelIn: Parcel) : this(
            parcelIn.readInt(),
            mutableListOf<StepikCourseItem>().apply {
                parcelIn.readTypedList(this, StepikCourseItem.CREATOR)
            }
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(next)
        dest.writeTypedList(courses)
    }

    override fun describeContents() = 0

}
