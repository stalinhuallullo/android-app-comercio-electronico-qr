package pe.gob.msi.data.model

import android.os.Parcel
import android.os.Parcelable


data class License(
    var CODCERTIFICADODC: String? = null,
    var TXTGLOSACERTIFICADO: String? = null,
    var TXTCERTIFICADODC: String? = null,
    var FECEMISION: String? = null,
    var FECVIGENCIA: String? = null,
    var FECHASOL: String? = null,
    var TXTGIRO: String? = null,
    var TXTAFORO: String? = null,
    var NUMAREA: String? = null,
    var NUMAREATECHADA: String? = null,
    var TXTEXPEDIENTE: String? = null,
    var TXTRESOLUCION: String? = null,
    var CODSOLICITUDDF: String? = null,
    var TXTSOLICITUD: String? = null,
    var TXTRAZONSOCIAL: String? = null,
    var TXTNOMBRECOMERCIAL: String? = null,
    var TXTDOMICILIO: String? = null,
    var TXTNOTA: String? = null,
    var FLGTIPODOCUMENTO: String? = null,
    var TIPOINSPECCION: String? = null,
    var CODRESULTADOSOLICITUD: String? = null,
    var FECHARES: String? = null,
    var CODRESULTADOINSP: String? = null,
    var TXTOBSERVACION: String? = null,
    var FECPROXINSP: String? = null,
    var FECRENOVACION: String? = null,
    var FECCADUCIDAD: String? = null,
    var CODRIESGO: String? = null,
    var CODRIESGO2: String? = null,
    var TXTNOMBRE: String? = null,
    var FECNOTIFICACION: String? = null,
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
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(CODCERTIFICADODC)
        parcel.writeString(TXTGLOSACERTIFICADO)
        parcel.writeString(TXTCERTIFICADODC)
        parcel.writeString(FECEMISION)
        parcel.writeString(FECVIGENCIA)
        parcel.writeString(FECHASOL)
        parcel.writeString(TXTGIRO)
        parcel.writeString(TXTAFORO)
        parcel.writeString(NUMAREA)
        parcel.writeString(NUMAREATECHADA)
        parcel.writeString(TXTEXPEDIENTE)
        parcel.writeString(TXTRESOLUCION)
        parcel.writeString(CODSOLICITUDDF)
        parcel.writeString(TXTSOLICITUD)
        parcel.writeString(TXTRAZONSOCIAL)
        parcel.writeString(TXTNOMBRECOMERCIAL)
        parcel.writeString(TXTDOMICILIO)
        parcel.writeString(TXTNOTA)
        parcel.writeString(FLGTIPODOCUMENTO)
        parcel.writeString(TIPOINSPECCION)
        parcel.writeString(CODRESULTADOSOLICITUD)
        parcel.writeString(FECHARES)
        parcel.writeString(CODRESULTADOINSP)
        parcel.writeString(TXTOBSERVACION)
        parcel.writeString(FECPROXINSP)
        parcel.writeString(FECRENOVACION)
        parcel.writeString(FECCADUCIDAD)
        parcel.writeString(CODRIESGO)
        parcel.writeString(CODRIESGO2)
        parcel.writeString(TXTNOMBRE)
        parcel.writeString(FECNOTIFICACION)
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