package pe.gob.msi.data.net.service.impl

import pe.gob.msi.data.model.HttpResponseLicense
import pe.gob.msi.data.net.RestApi
import pe.gob.msi.data.net.service.ILicenseService
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import pe.gob.msi.data.model.User
import pe.gob.msi.data.net.service.IAuthService

class LicenseService: ILicenseService {

    private val apiService: ILicenseService = RestApi.retrofitInstance!!.create(ILicenseService::class.java)
    override fun findByIdLicense(code: String): Observable<HttpResponseLicense> {
        return apiService.findByIdLicense(code)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun findByCodeLicense(code: String): Observable<HttpResponseLicense> {
        return apiService.findByCodeLicense(code)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}