package pe.gob.msi.data.net.service

import io.reactivex.rxjava3.core.Observable
import pe.gob.msi.data.model.HttpResponseLicense
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ILicenseService {

    //@GET(value = "/WSITSE/ListarItseCertificado?IDCERTIFICADO={code}")
    //fun findByCodeLicense(@Path("code") code: String) : Observable<HttpResponseLicense>
    @GET(value = "/WSITSE/ListarItseCertificado")
    fun findByCodeLicense(@Query("IDCERTIFICADO") code: String) : Observable<HttpResponseLicense>
}