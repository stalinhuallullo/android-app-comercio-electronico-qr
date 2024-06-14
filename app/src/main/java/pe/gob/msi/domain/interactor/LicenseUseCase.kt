package pe.gob.msi.domain.interactor

import androidx.core.util.Preconditions
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import pe.gob.msi.domain.executor.PostExecutionThread
import pe.gob.msi.domain.executor.ThreadExecutor
import javax.inject.Inject

class LicenseUseCase
/**
 * Constructor del caso de uso
 *
 * @param threadExecutor      Ejecutor de un metodo
 * @param postExecutionThread Tipo de ejecucion en un hilo diferente
 */ @Inject constructor(
    private val productRepository: ProductRepository,
    private val authRepository: AuthRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : UseCase(threadExecutor, postExecutionThread) {
}

