package space.tritin.jetbrainsinternshipstepik.common

import android.os.Parcel
import android.os.Parcelable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import space.tritin.jetbrainsinternshipstepik.R

/**
 * <p>Date: 04.05.18</p>
 * @author Simon
 */

/**
 * Создает Parcelable
 * */
inline fun <reified T : Parcelable> createParcel(
        crossinline createFromParcel: (Parcel) -> T?): Parcelable.Creator<T> =
        object : Parcelable.Creator<T> {
            override fun createFromParcel(source: Parcel): T? = createFromParcel(source)
            override fun newArray(size: Int): Array<out T?> = arrayOfNulls(size)
        }

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

/**
 * @param imageUrl URL загружаемого изображения
 * */
fun ImageView.loadImg(imageUrl: String) {
    if (TextUtils.isEmpty(imageUrl)) {
        Picasso.with(context).load(R.drawable.tools_cover).into(this)
    } else {
        Picasso.with(context).load(imageUrl).into(this)
    }
}