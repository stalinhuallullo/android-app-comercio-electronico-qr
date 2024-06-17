package pe.gob.msi.presentation.feature.camera

import pe.gob.msi.data.net.viewmodel.LicenseViewModel
import pe.gob.msi.domain.executor.PostExecutionThread
import pe.gob.msi.domain.executor.ThreadExecutor
import javax.inject.Inject

class CameraQrPresenter
/**
 * Constructor del caso de uso
 *
 * @param threadExecutor      Ejecutor de un metodo
 * @param postExecutionThread Tipo de ejecucion en un hilo diferente
 */ @Inject constructor(
    var licenseUseCase: LicenseViewModel,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread) {

    fun findByCodeLicense(code: String) {
        this.licenseUseCase.findByCodeLicense(code)
    }

}