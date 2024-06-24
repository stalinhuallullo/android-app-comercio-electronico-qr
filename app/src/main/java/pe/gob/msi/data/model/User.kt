package pe.gob.msi.data.model

import android.os.Parcel
import android.os.Parcelable

data class User(
    var CODUSUARIO: String? = null,
    var TXTPASSWORD: String? = null,
    var CODPERSONA: String? = null,
    var CODESTADOUSUARIO: String? = null,
    var TXTEMAIL: String? = null,
    var TXTPREGUNTA: String? = null,
    var TXTRESPUESTA: String? = null,
    var CODTIPOUSUARIO: String? = null,
    var ESTADOUSUARIO: String? = null,
    var ESTADOUSUARIOGLOSA: String? = null,
    var TIPOUSUARIO: String? = null,
    var CODAREASUPERIOR: String? = null,
    var AREA: String? = null,
    var CODAREA: String? = null,
    var AREANOMBRECORTO: String? = null,
    var AREAANTIGUA: String? = null,
    var NOMBRE: String? = null,
    var APELLIDOPATERNO: String? = null,
    var DOCIDENTIDAD: String? = null,
    var NOMBREPERSONA: String? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    ) {
    }
    override fun describeContents(): Int = 0

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(CODUSUARIO)
        parcel.writeString(TXTPASSWORD)
        parcel.writeString(CODPERSONA)
        parcel.writeString(CODESTADOUSUARIO)
        parcel.writeString(TXTEMAIL)
        parcel.writeString(TXTPREGUNTA)
        parcel.writeString(TXTRESPUESTA)
        parcel.writeString(CODTIPOUSUARIO)
        parcel.writeString(ESTADOUSUARIO)
        parcel.writeString(ESTADOUSUARIOGLOSA)
        parcel.writeString(TIPOUSUARIO)
        parcel.writeString(CODAREASUPERIOR)
        parcel.writeString(AREA)
        parcel.writeString(CODAREA)
        parcel.writeString(AREANOMBRECORTO)
        parcel.writeString(AREAANTIGUA)
        parcel.writeString(NOMBRE)
        parcel.writeString(APELLIDOPATERNO)
        parcel.writeString(DOCIDENTIDAD)
        parcel.writeString(NOMBREPERSONA)
    }
    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }



}