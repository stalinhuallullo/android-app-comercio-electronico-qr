package pe.gob.msi.data.net.service

import io.reactivex.rxjava3.core.Observable
import pe.gob.msi.data.model.HttpRequestLogin
import pe.gob.msi.data.model.HttpResponseUserLogin
import pe.gob.msi.data.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface IAuthService {
    /*@FormUrlEncoded
    @POST(value = "/WSITSE/LogeoUsaurioMSI")
    fun login(
        @Field("CODSISTEMA") codSystem: String,
        @Field("CODMODULO") codModule: String,
        @Field("CODUSARIO") codUser: String,
        @Field("CODPASSWORD") codPassword: String): Observable<HttpResponseUserLogin>*/

    @POST(value = "/WSITSE/LogeoUsaurioMSI")
    fun login(@Body request: HttpRequestLogin): Observable<HttpResponseUserLogin>
}