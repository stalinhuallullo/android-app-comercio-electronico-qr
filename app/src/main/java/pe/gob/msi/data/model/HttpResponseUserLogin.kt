package pe.gob.msi.data.model

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

data class HttpResponseUserLogin (
    val CodigoRespuesta: String?,
    val TXTRESPUESTA: String?,
    val Datos: ArrayList<User>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.createTypedArrayList(User)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(CodigoRespuesta)
        parcel.writeString(TXTRESPUESTA)
        parcel.writeTypedList(Datos)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HttpResponseUserLogin> {
        override fun createFromParcel(parcel: Parcel): HttpResponseUserLogin {
            return HttpResponseUserLogin(parcel)
        }

        override fun newArray(size: Int): Array<HttpResponseUserLogin?> {
            return arrayOfNulls(size)
        }
    }
}
