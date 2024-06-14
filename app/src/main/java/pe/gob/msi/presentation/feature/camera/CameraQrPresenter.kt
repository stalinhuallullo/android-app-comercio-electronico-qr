package pe.gob.msi.presentation.feature.camera

import javax.inject.Inject

class CameraQrPresenter
/**
 * Constructor del caso de uso
 *
 * @param threadExecutor      Ejecutor de un metodo
 * @param postExecutionThread Tipo de ejecucion en un hilo diferente
 */ @Inject constructor(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread)
    : UseCase(threadExecutor, postExecutionThread) {

    fun getShowcaseLicense(code: String) {

        this.productUseCase.getProductsByIds(aux, spo)
    }

}