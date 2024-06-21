package pe.gob.msi.presentation.feature.auth.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.snackbar.Snackbar
import pe.gob.msi.R
import pe.gob.msi.data.net.service.IAuthService
import pe.gob.msi.data.net.service.impl.AuthService
import pe.gob.msi.data.repository.UserRepository
import pe.gob.msi.databinding.ActivityLoginBinding
import pe.gob.msi.presentation.feature.dashboard.DashboardActivity
import pe.gob.msi.presentation.utils.SessionManager
import pe.gob.msi.presentation.utils.Tools

class LoginActivity : AppCompatActivity(), View.OnClickListener, LoginContract.View {

    lateinit var btnLogin: AppCompatButton
    lateinit var progressBar: ProgressBar
    lateinit var parentView: View

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginPresenter: LoginPresenter
    private lateinit var sessionManager: SessionManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initToolbar()
        initComponent()
        initClickListener()
        addTextWatchers()
    }

    private fun initToolbar() {
        Tools.setSystemBarColor(this, R.color.white)
        Tools.setSystemBarLight(this)
    }
    private fun initComponent() {
        parentView = findViewById(android.R.id.content)
        btnLogin = findViewById(R.id.btnLogin)
        progressBar = findViewById(R.id.progressBar)

        sessionManager = SessionManager(this)

        if (sessionManager.isLoggedIn()) {
            navigateToUserActivity()
        }

        loginPresenter = LoginPresenter(this, UserRepository(AuthService()), sessionManager)

        /*binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val rememberMe = binding.cbRememberMe.isChecked

            if (validateInputs(email, password)) {
                loginPresenter.login(email, password, rememberMe)
            }
        }*/
    }
    private fun initClickListener() {
        // event clicks
        btnLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnLogin->{

                val email = binding.etEmail.text.toString()
                val password = binding.etPassword.text.toString()
                val rememberMe = binding.cbRememberMe.isChecked

                if (validateInputs(email, password)) {
                    loginPresenter.login(email, password, rememberMe)
                }

                /*progressBar.visibility = View.VISIBLE
                btnLogin.visibility = View.GONE
                Handler(Looper.getMainLooper()).postDelayed({
                    progressBar.setVisibility(View.GONE)
                    btnLogin.setVisibility(View.VISIBLE)
                    //Snackbar.make(parentView, "Login data submitted", Snackbar.LENGTH_SHORT).show()

                    val intent = Intent(this, DashboardActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    finish()
                }, 2000)*/
            }
        }
    }

    /*******************************************************************/


    private fun validateInputs(email: String, password: String): Boolean {
        var isValid = true
        if (email.isEmpty()) {
            binding.etEmail.error = "El correo electronico es requerido"
            isValid = false
        }
        if (password.isEmpty()) {
            binding.etPassword.error = "La contraseña es requerido"
            isValid = false
        }
        return isValid
    }

    override fun showLoading() {
        binding.btnLogin.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.btnLogin.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
    }

    override fun showError(message: String) {
        binding.tvErrorMessage.text = message
        binding.tvErrorMessage.visibility = View.VISIBLE
    }

    override fun navigateToUserActivity() {
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun addTextWatchers() {
        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                binding.tvErrorMessage.visibility = View.GONE
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                binding.tvErrorMessage.visibility = View.GONE
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}