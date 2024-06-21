package pe.gob.msi.data.net.service

import io.reactivex.rxjava3.core.Observable
import pe.gob.msi.data.model.User
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface IAuthService {
    @FormUrlEncoded
    @POST(value = "login")
    fun login(@Field("email") email: String, @Field("password") password: String): Observable<User>
}