package pe.gob.msi.presentation.feature.dashboard

import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import pe.gob.msi.R
import pe.gob.msi.presentation.base.mvp.LoadingView
import pe.gob.msi.presentation.feature.auth.login.LoginActivity
import pe.gob.msi.presentation.feature.form.SearchForCodeActivity
import pe.gob.msi.presentation.feature.camera.CameraQrActivity
import pe.gob.msi.presentation.feature.noPage.EmptyResultActivity
import pe.gob.msi.presentation.utils.SessionManager
import pe.gob.msi.presentation.utils.Tools

class DashboardActivity : AppCompatActivity(), View.OnClickListener , LoadingView {
    private lateinit var toolbar: Toolbar
    private lateinit var btnQueryCamara: CardView
    private lateinit var btnQueryForm: CardView
    private lateinit var tvUserName: TextView
    private lateinit var btnLogout: AppCompatButton
    private lateinit var scanQrResultLauncher : ActivityResultLauncher<Intent>
    private lateinit var viewLoading: View

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_dashboard)

        initToolbar()
        initComponent()
        initClickListener()
        setupScanner()
    }

    private fun initToolbar() {
        toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.setNavigationIcon(R.drawable.ic_menu_notes)
        toolbar.navigationIcon?.colorFilter = PorterDuffColorFilter(resources.getColor(R.color.grey_60, theme), PorterDuff.Mode.SRC_ATOP)
        toolbar.setBackgroundColor(resources.getColor(R.color.white, theme))
        toolbar.setTitleTextColor(resources.getColor(R.color.black, theme))
        toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
        setSupportActionBar(toolbar)

        supportActionBar!!.title = "Inicio"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        Tools.setSystemBarColor(this, R.color.white)
        Tools.setSystemBarLight(this)
    }

    private fun initComponent() {
        btnQueryCamara = findViewById(R.id.btnQueryCamara)
        btnQueryForm = findViewById(R.id.btnQueryForm)
        tvUserName = findViewById(R.id.tvUserName)
        btnLogout = findViewById(R.id.btnLogout)

        sessionManager = SessionManager(this)
        val user = sessionManager.getUserSession()
        tvUserName.text = user?.name
    }

    private fun initClickListener(){
        btnQueryCamara.setOnClickListener(this)
        btnQueryForm.setOnClickListener(this)
        btnLogout.setOnClickListener(this)
    }

    private fun setupScanner() {
        scanQrResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

        }
    }

    private fun goToReadQr() {
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
        options.setPrompt("Acerca la camara al código QR")
        options.setCameraId(0) // Use a specific camera of the device
        options.setBeepEnabled(false)
        options.setBarcodeImageEnabled(true)
        options.captureActivity = CameraQrActivity::class.java
        scanQrResultLauncher.launch(
            ScanContract().createIntent(baseContext, options )
        )
    }

    private fun goToFormSearch(){
        val intentFormulario = Intent(
            applicationContext,
            SearchForCodeActivity::class.java
        ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intentFormulario)
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnQueryCamara -> {
                goToReadQr()
            }

            R.id.btnQueryForm -> {
                goToFormSearch()
            }
            R.id.btnLogout -> {
                sessionManager.clearSession()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun showLoading() {
        if (viewLoading != null) {
            viewLoading!!.visibility = View.VISIBLE
        }
    }

    override fun hideLoading() {
        if (viewLoading != null) {
            viewLoading!!.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}