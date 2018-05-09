package space.tritin.jetbrainsinternshipstepik.mvp.models

import android.os.Parcel
import android.os.Parcelable
import com.reactiveandroid.Model
import com.reactiveandroid.annotation.Column
import com.reactiveandroid.annotation.PrimaryKey
import com.reactiveandroid.annotation.Table
import space.tritin.jetbrainsinternshipstepik.common.createParcel
import space.tritin.jetbrainsinternshipstepik.ui.adapters.coursesrecycler.AdapterConstants
import space.tritin.jetbrainsinternshipstepik.ui.adapters.coursesrecycler.ViewType

/**
 * <p>Date: 05.05.18</p>
 * @author Simon
 */
@Table(name = "Favorite", database = AppDatabase::class)
class StepikCourseItem : ViewType, Parcelable, Model {

    @PrimaryKey
    var id: Long = 0

    @Column(name = "title")
    var title: String = ""

    @Column(name = "thumbnail")
    var thumbnail: String? = null

    companion object {
        @JvmField
        @Suppress("unused")
        val CREATOR = createParcel { StepikCourseItem(it) }
    }

    constructor()

    constructor(id: Long, title: String, thumbnail: String?) {
        this.id = id
        this.title = title
        this.thumbnail = thumbnail
    }


    protected constructor(parcelIn: Parcel) : this(
            parcelIn.readLong(),
            parcelIn.readString(),
            parcelIn.readString()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(id)
        dest.writeString(title)
        dest.writeString(thumbnail)
    }

    override fun describeContents() = 0

    override fun getViewType(): Int = AdapterConstants.COURSE

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as StepikCourseItem

        if (id != other.id) return false
//        if (title != other.title) return false
//        if (thumbnail != other.thumbnail) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + (thumbnail?.hashCode() ?: 0)
        return result
    }


}