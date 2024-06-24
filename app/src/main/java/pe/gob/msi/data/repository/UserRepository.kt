package pe.gob.msi.data.repository

import io.reactivex.rxjava3.core.Observable
import pe.gob.msi.data.model.HttpRequestLogin
import pe.gob.msi.data.model.HttpResponseUserLogin
import pe.gob.msi.data.model.User
import pe.gob.msi.data.net.service.impl.AuthService
import javax.inject.Singleton

@Singleton
class UserRepository(private val authService: AuthService) {

    fun login(email: String, password: String): Observable<HttpResponseUserLogin> {
        val codSystem: String = "0"
        val codModule: String = "0"
        //codUser: String,
        //codPassword: String

        val request = HttpRequestLogin(codSystem, codModule, email, password)
        return authService.login(request)
    }

}