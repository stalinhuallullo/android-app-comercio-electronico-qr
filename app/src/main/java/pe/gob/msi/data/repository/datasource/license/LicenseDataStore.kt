package pe.gob.msi.data.repository.datasource.license

import io.reactivex.rxjava3.core.Observable
import pe.gob.msi.data.model.HttpResponseLicense

interface LicenseDataStore {
    fun findByCodeLicense(data: String) : Observable<HttpResponseLicense>
}