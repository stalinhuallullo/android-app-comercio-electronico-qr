package pe.gob.msi.presentation.feature.auth.login

interface LoginContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun showError(message: String)
        fun navigateToUserActivity()
    }

    interface Presenter {
        fun login(email: String, password: String, rememberMe: Boolean)
    }
}