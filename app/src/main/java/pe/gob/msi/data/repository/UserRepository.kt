package pe.gob.msi.data.repository

import io.reactivex.rxjava3.core.Observable
import pe.gob.msi.data.model.User
import pe.gob.msi.data.net.service.impl.AuthService
import javax.inject.Singleton

@Singleton
class UserRepository(private val authService: AuthService) {

    fun login(email: String, password: String): Observable<User> {
        return authService.login(email, password)
    }

}