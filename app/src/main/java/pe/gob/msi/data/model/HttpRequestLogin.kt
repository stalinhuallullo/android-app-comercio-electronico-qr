package pe.gob.msi.data.model

import android.os.Parcel
import android.os.Parcelable

data class HttpRequestLogin (
    var CODSISTEMA: String? = null,
    var CODMODULO: String? = null,
    var CODUSARIO: String? = null,
    var CODPASSWORD: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(CODSISTEMA)
        parcel.writeString(CODMODULO)
        parcel.writeString(CODUSARIO)
        parcel.writeString(CODPASSWORD)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HttpRequestLogin> {
        override fun createFromParcel(parcel: Parcel): HttpRequestLogin {
            return HttpRequestLogin(parcel)
        }

        override fun newArray(size: Int): Array<HttpRequestLogin?> {
            return arrayOfNulls(size)
        }
    }

}