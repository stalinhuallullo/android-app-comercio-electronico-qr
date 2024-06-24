package pe.gob.msi.presentation.feature.auth.login

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import pe.gob.msi.data.repository.UserRepository
import pe.gob.msi.presentation.utils.SessionManager

class LoginPresenter(
    private val view: LoginContract.View,
    private val repository: UserRepository,
    private val sessionManager: SessionManager
) : LoginContract.Presenter {

    override fun login(email: String, password: String, rememberMe: Boolean) {
        view.showLoading()
        repository.login(email, password)
            .subscribe({ user ->
                view.hideLoading()
                sessionManager.saveUserSession(user.Datos!!.get(0), rememberMe)
                view.navigateToUserActivity()
            }, { throwable ->
                view.hideLoading()
                view.showError("Error al iniciar sesión. Por favor verifique sus credenciales.")
            })
    }
}