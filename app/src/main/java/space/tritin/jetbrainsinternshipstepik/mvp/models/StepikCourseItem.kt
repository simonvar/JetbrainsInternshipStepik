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
    var dataID: Long = 0

    @Column(name = "courseId")
    var id: Int = 0

    @Column(name = "title")
    var title: String = ""

    @Column(name = "thumbnail")
    var thumbnail: String? = null

    @Column(name = "favorite")
    var isFavorite: Boolean = false

    companion object {
        @JvmField
        @Suppress("unused")
        val CREATOR = createParcel { StepikCourseItem(it) }
    }

    constructor()

    constructor(id: Int, title: String, thumbnail: String?) {
        this.id = id
        this.title = title
        this.thumbnail = thumbnail
    }


    protected constructor(parcelIn: Parcel) : this(
            parcelIn.readInt(),
            parcelIn.readString(),
            parcelIn.readString()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
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
        if (title != other.title) return false
        if (thumbnail != other.thumbnail) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + (thumbnail?.hashCode() ?: 0)
        return result
    }


}