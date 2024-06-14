package pe.gob.msi.data.model

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

data class HttpResponseLicense (
    val CodigoRespuesta: String?,
    val Respuesta: String?,
    val Datos: ArrayList<License>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.createTypedArrayList(License)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(CodigoRespuesta)
        parcel.writeString(Respuesta)
        parcel.writeTypedList(Datos)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HttpResponseLicense> {
        override fun createFromParcel(parcel: Parcel): HttpResponseLicense {
            return HttpResponseLicense(parcel)
        }

        override fun newArray(size: Int): Array<HttpResponseLicense?> {
            return arrayOfNulls(size)
        }
    }
}
