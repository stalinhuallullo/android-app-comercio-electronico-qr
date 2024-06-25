package pe.gob.msi.data.net.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.gob.msi.data.model.HttpResponseLicense
import pe.gob.msi.data.net.service.impl.LicenseService

class LicenseViewModel : ViewModel() {
    private val licenseService = LicenseService()
    private val _liveData = MutableLiveData<HttpResponseLicense>()

    val licenseLiveData: LiveData<HttpResponseLicense> = _liveData

    fun findByIdLicense(code: String) {
        licenseService.findByIdLicense(code)
            .subscribe({ response ->
                _liveData.value = response
            }, { error ->
                _liveData.value = HttpResponseLicense(
                    CodigoRespuesta = "99",
                    TXTRESPUESTA = "Network Error: ${error.message}",
                    Datos = null
                )
            })
    }

    fun findByCodeLicense(code: String) {
        licenseService.findByCodeLicense(code)
            .subscribe({ response ->
                _liveData.value = response
            }, { error ->
                _liveData.value = HttpResponseLicense(
                    CodigoRespuesta = "99",
                    TXTRESPUESTA = "Network Error: ${error.message}",
                    Datos = null
                )
            })
    }

}