package notification.reminder.aldefy

import android.os.Parcel
import android.os.Parcelable

/*
Solution for sending intent extras in alarm manager call for M and above
https://stackoverflow.com/a/18000094/1749596
 */
fun marshall(parceable: Parcelable): ByteArray {
    val parcel = Parcel.obtain()
    parceable.writeToParcel(parcel, 0)
    val bytes = parcel.marshall()
    parcel.recycle()
    return bytes
}

fun unmarshall(bytes: ByteArray): Parcel {
    val parcel = Parcel.obtain()
    parcel.unmarshall(bytes, 0, bytes.size)
    parcel.setDataPosition(0) // This is extremely important!
    return parcel
}

fun <T> unmarshall(bytes: ByteArray, creator: Parcelable.Creator<T>): T {
    val parcel = unmarshall(bytes)
    val result = creator.createFromParcel(parcel)
    parcel.recycle()
    return result
}
