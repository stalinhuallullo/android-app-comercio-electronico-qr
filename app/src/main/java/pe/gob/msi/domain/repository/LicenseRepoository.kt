package pe.gob.msi.domain.repository

import io.reactivex.rxjava3.core.Observable
import pe.gob.msi.data.model.HttpResponseLicense
import retrofit2.http.Query

interface LicenseRepoository {

    fun findByCodeLicense(data: String) : Observable<HttpResponseLicense>
}