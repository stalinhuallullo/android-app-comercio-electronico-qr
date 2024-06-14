package pe.gob.msi.data.model

import android.os.Parcel
import android.os.Parcelable


data class License(
    val CODLINEA: String,
    val TXTLINEA: String,
    val TXTRGBBUS: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(CODLINEA)
        parcel.writeString(TXTLINEA)
        parcel.writeString(TXTRGBBUS)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<License> {
        override fun createFromParcel(parcel: Parcel): License {
            return License(parcel)
        }

        override fun newArray(size: Int): Array<License?> {
            return arrayOfNulls(size)
        }
    }
}