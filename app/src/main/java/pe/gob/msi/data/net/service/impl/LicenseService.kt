package pe.gob.msi.data.net.service.impl

import pe.gob.msi.data.model.HttpResponseLicense
import pe.gob.msi.data.net.RestApi
import pe.gob.msi.data.net.service.ILicenseService
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable

class LicenseService: ILicenseService {

    private val apiService: ILicenseService = RestApi.retrofitInstance!!.create(ILicenseService::class.java)
    override fun findByCodeLicense(data: String): Observable<HttpResponseLicense> {
        return apiService.findByCodeLicense(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}