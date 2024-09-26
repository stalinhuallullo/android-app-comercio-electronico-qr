package pe.gob.msi.presentation.feature.dashboard

import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import pe.gob.msi.R
import pe.gob.msi.databinding.ActivityDashboardBinding
import pe.gob.msi.presentation.base.mvp.LoadingView
import pe.gob.msi.presentation.feature.about.AboutActivity
import pe.gob.msi.presentation.feature.auth.login.LoginActivity
import pe.gob.msi.presentation.feature.camera.CameraQrActivity
import pe.gob.msi.presentation.feature.form.SearchForCodeActivity
import pe.gob.msi.presentation.utils.SessionManager
import pe.gob.msi.presentation.utils.Tools

class DashboardActivity : AppCompatActivity(), View.OnClickListener , LoadingView,
    NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityDashboardBinding

    private lateinit var toolbar: Toolbar
    private lateinit var btnQueryCamara: CardView
    private lateinit var btnQueryForm: CardView
    private lateinit var tvUserName: TextView
    private lateinit var tvArea: TextView
    //private lateinit var btnLogout: AppCompatButton
    private lateinit var scanQrResultLauncher : ActivityResultLauncher<Intent>
    private lateinit var viewLoading: View
    private lateinit var drawer: DrawerLayout
    private lateinit var navView: NavigationView


    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setContentView(R.layout.activity_dashboard)

        binding.includeDashboardMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab).show()
        }
        initToolbar()
        initComponent()

        initClickListener()
        showDataSession()
        setupScanner()
    }

    private fun initToolbar() {
        toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.setNavigationIcon(R.drawable.ic_menu_notes)
        toolbar.navigationIcon?.colorFilter = PorterDuffColorFilter(resources.getColor(R.color.grey_60, theme), PorterDuff.Mode.SRC_ATOP)
        toolbar.setBackgroundColor(resources.getColor(R.color.white, theme))
        toolbar.setTitleTextColor(resources.getColor(R.color.black, theme))
        //toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
        setSupportActionBar(toolbar)

        supportActionBar!!.title = "Inicio"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        Tools.setSystemBarColor(this, R.color.white)
        Tools.setSystemBarLight(this)
    }

    private fun initComponent() {
        drawer = findViewById(R.id.drawerLayout)
        navView = findViewById(R.id.navView)

        btnQueryCamara = findViewById(R.id.btnQueryCamara)
        btnQueryForm = findViewById(R.id.btnQueryForm)
        tvUserName = findViewById(R.id.tvUserName)
        tvArea = findViewById(R.id.tvArea)
        //btnLogout = findViewById(R.id.btnLogout)


    }

    private fun showDataSession(){
        sessionManager = SessionManager(this)
        val user = sessionManager.getUserSession()
        tvUserName.text = user?.NOMBRE
        tvArea.text = user?.AREANOMBRECORTO


        val headerView = navView.getHeaderView(0)
        val headerTitle = headerView.findViewById<TextView>(R.id.headerTitle)
        val headerTitleArea = headerView.findViewById<TextView>(R.id.headerTitleArea)
        headerTitle.text = user?.NOMBRE
        headerTitleArea.text = user?.AREA
    }

    private fun initClickListener(){
        btnQueryCamara.setOnClickListener(this)
        btnQueryForm.setOnClickListener(this)
        //btnLogout.setOnClickListener(this)

        navView.setNavigationItemSelectedListener(this);
    }

    private fun setupScanner() {
        scanQrResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

        }
    }

    private fun goToRenovacionComercioViaPublica() {
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

    private fun goToLicenciaDeFuncionamiento() {
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

    private fun goToCertificadoDeInspeccionTecnica() {
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

    private fun goToFormSearch() {
        val intentFormulario = Intent(
            applicationContext,
            SearchForCodeActivity::class.java
        ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intentFormulario)
    }

    private fun goToAbout() {
        val intent = Intent(
            applicationContext,
            AboutActivity::class.java
        ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
    }

    private fun logout(){
        sessionManager.clearSession()
        val intent = Intent(
            applicationContext,
            LoginActivity::class.java
        ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
        finish()
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnQueryCamara -> {
                goToReadQr()
            }

            R.id.btnQueryForm -> {
                goToFormSearch()
            }
            /*R.id.btnLogout -> {
                sessionManager.clearSession()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }*/
        }
    }

    override fun showLoading() {
        viewLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        viewLoading.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_navigation_drawer, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                drawer.openDrawer(GravityCompat.START)
                //return true
            }
        }
        return super.onOptionsItemSelected(item)
    }



   override fun onNavigationItemSelected(menu: MenuItem): Boolean {
       //drawer.closeDrawer(GravityCompat.START);

       return when (menu.itemId) {
           R.id.nav_renovacion -> {
               //goToRenovacionComercioViaPublica()
               binding.includeDashboardMain.includeMain.txtCategory.text = "Renovacion"
               drawer.closeDrawer(GravityCompat.START);
               true
           }
           R.id.nav_licencia -> {
               //goToLicenciaDeFuncionamiento()
               binding.includeDashboardMain.includeMain.txtCategory.text = "Licencia"
               drawer.closeDrawer(GravityCompat.START);
               true
           }
           R.id.nav_certificado -> {
               //goToCertificadoDeInspeccionTecnica()
               binding.includeDashboardMain.includeMain.txtCategory.text = "Certificado"
               drawer.closeDrawer(GravityCompat.START);
               true
           }
           /*R.id.navLecturaQR -> {
               goToReadQr()
               //drawer.closeDrawer(GravityCompat.START);
           }
           R.id.navConsulta -> {
               goToFormSearch()
               //drawer.closeDrawer(GravityCompat.START);
           }*/
           R.id.navAbout -> {
               goToAbout()
               true
           }
           R.id.navLogout -> {
               logout()
               true
           }
           else -> super.onOptionsItemSelected(menu)
       }

       return false
   }

}