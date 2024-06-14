package pe.gob.msi.data.repository.datasource.license

import android.content.Context
import pe.gob.msi.data.net.service.ILicenseService
import pe.gob.msi.data.net.service.impl.LicenseService
import pe.gob.msi.data.repository.LicenseDataRepository
import javax.inject.Inject

class LicenseDataStoreFactory @Inject internal constructor(context: Context) {
    private val context: Context = context.applicationContext

    fun createCloudLicense(): LicenseDataStore {
        val service: ILicenseService = LicenseService()
        return LicenseDataRepository(service)
    }

}