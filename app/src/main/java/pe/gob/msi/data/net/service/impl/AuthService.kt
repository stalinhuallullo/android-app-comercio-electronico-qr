package pe.gob.msi.data.net.service.impl

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import pe.gob.msi.data.model.HttpRequestLogin
import pe.gob.msi.data.model.HttpResponseUserLogin
import pe.gob.msi.data.net.RestApi
import pe.gob.msi.data.net.service.IAuthService

class AuthService: IAuthService {

    private val apiService: IAuthService = RestApi.retrofitInstance!!.create(IAuthService::class.java)

    override fun login(request: HttpRequestLogin): Observable<HttpResponseUserLogin> {
        return apiService.login(request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}