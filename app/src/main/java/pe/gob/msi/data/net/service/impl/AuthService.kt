package pe.gob.msi.data.net.service.impl

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import pe.gob.msi.data.model.HttpResponseLicense
import pe.gob.msi.data.model.User
import pe.gob.msi.data.net.RestApi
import pe.gob.msi.data.net.service.IAuthService
import pe.gob.msi.data.net.service.ILicenseService

class AuthService: IAuthService {

    private val apiService: IAuthService = RestApi.retrofitInstance!!.create(IAuthService::class.java)

    override fun login(email: String, password: String): Observable<User> {
        return apiService.login(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}