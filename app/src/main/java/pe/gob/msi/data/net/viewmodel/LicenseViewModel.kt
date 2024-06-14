package pe.gob.msi.data.net.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.gob.msi.data.model.HttpResponseLicense
import pe.gob.msi.data.net.service.impl.LicenseService

class LicenseViewModel : ViewModel() {
    private val licenseService = LicenseService()
    private val _liveData = MutableLiveData<HttpResponseLicense>()
    val LicenseLiveData: LiveData<HttpResponseLicense> = _liveData

}