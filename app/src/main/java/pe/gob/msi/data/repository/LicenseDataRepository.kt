package pe.gob.msi.data.repository

import io.reactivex.rxjava3.core.Observable
import pe.gob.msi.data.model.HttpResponseLicense
import pe.gob.msi.data.repository.datasource.license.LicenseDataStore
import pe.gob.msi.data.repository.datasource.license.LicenseDataStoreFactory
import pe.gob.msi.domain.repository.LicenseRepoository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
//class LicenseDataRepository{
class LicenseDataRepository @Inject internal constructor(private val licenseDataStoreFactory: LicenseDataStoreFactory): LicenseRepoository {
//class ProductDataRepository @Inject internal constructor(private val productDataStoreFactory: ProductDataStoreFactory, private val productEntityDataMapper: ProductEntityDataMapper) : ProductRepository {
    override fun findByCodeLicense(data: String): Observable<HttpResponseLicense> {
        val dataStore: LicenseDataStore = licenseDataStoreFactory.createCloudLicense()
        return dataStore.findByCodeLicense(data).map(productEntityDataMapper::transformProductListModel)
    }
}