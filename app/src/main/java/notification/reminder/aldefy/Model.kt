package notification.reminder.aldefy

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by aditlal on 17/08/17.
 */
data class Model(
        var msg: String,
        var title: String
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(msg)
        writeString(title)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Model> = object : Parcelable.Creator<Model> {
            override fun createFromParcel(source: Parcel): Model = Model(source)
            override fun newArray(size: Int): Array<Model?> = arrayOfNulls(size)
        }
    }
}
