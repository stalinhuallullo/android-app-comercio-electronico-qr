package pe.gob.msi.presentation.feature.camera

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.journeyapps.barcodescanner.DefaultDecoderFactory
import pe.gob.msi.R
import pe.gob.msi.data.model.HttpResponseLicense
import pe.gob.msi.data.net.viewmodel.LicenseViewModel
import pe.gob.msi.presentation.feature.dashboard.DashboardActivity
import pe.gob.msi.presentation.feature.noPage.EmptyResultActivity
import pe.gob.msi.presentation.feature.searchResult.SearchResultActivity
import pe.gob.msi.presentation.utils.Constants
import pe.gob.msi.presentation.utils.Tools


//class CameraQrActivity : CaptureActivity() {
class CameraQrActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var barcodeView: DecoratedBarcodeView
    private lateinit var viewLoading: View

    private lateinit var viewModel: LicenseViewModel
    private var licensesHttp: HttpResponseLicense? = null

    private val CAMERA_PERMISSION_CODE = 100


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_qr)

        // Inicializar componentes si los permisos ya están concedidos
        initToolbar()
        initComponent()
        initClickListener()

        // Solicitar permisos de cámara e internet si no están concedidos
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA, Manifest.permission.INTERNET), CAMERA_PERMISSION_CODE)
        } else {
            // Inicializar componentes si los permisos ya están concedidos
            initToolbar()
            initComponent()
            initClickListener()
        }
    }

    private fun initToolbar() {

        toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.navigationIcon?.colorFilter = PorterDuffColorFilter(resources.getColor(R.color.grey_60, theme), PorterDuff.Mode.SRC_ATOP)
        toolbar.setBackgroundColor(resources.getColor(R.color.white, theme))
        toolbar.setTitleTextColor(resources.getColor(R.color.black, theme))
        toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
        setSupportActionBar(toolbar)

        supportActionBar!!.title = "Escanear código QR"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        Tools.setSystemBarColor(this, R.color.white)
        Tools.setSystemBarLight(this)
    }
    private fun initComponent() {
        viewLoading = findViewById(R.id.viewLoading)
        barcodeView = findViewById(R.id.zxingBarcodeScanner)
        barcodeView.barcodeView.decoderFactory = DefaultDecoderFactory()
        barcodeView.initializeFromIntent(intent)
        barcodeView.decodeContinuous(callback)

        // Initialize the ViewModel
        viewModel = ViewModelProvider(this)[LicenseViewModel::class.java]
    }

    private fun initClickListener() {

    }

    private val callback = object : BarcodeCallback {
        override fun barcodeResult(result: BarcodeResult) {
            if (result.text != null) {
                /*val intent = intent
                intent.putExtra("SCAN_RESULT", result.text)
                setResult(RESULT_OK, intent)
                finish()*/
                barcodeView.pause()  // Pause the barcode scanner to stop further scans
                getData(result.text)
            }
        }

        override fun possibleResultPoints(resultPoints: List<com.google.zxing.ResultPoint>) {
            // Handle possible result points if necessary
            println("resultPoints ============> $resultPoints")
        }
    }

    private fun goToResultEmpty() {
        val intent = Intent(this, EmptyResultActivity::class.java)
        startActivity(intent)
    }

    private fun goToResult() {
        val intent = Intent(this, SearchResultActivity::class.java).apply {
            putExtra(Constants.SEARCH_KEY, licensesHttp)
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP // Ensure only one instance
        }
        startActivity(intent)
    }

    private fun getData(code: String) {
        showProgress()
        viewModel.licenseLiveData.observe(this) { response ->
            response?.let {
                if (it.CodigoRespuesta == "01" && it.TXTRESPUESTA == "Exito" && it.Datos?.size!! > 0) {
                    licensesHttp = it
                    goToResult()
                } else {
                    goToResultEmpty()
                }
            }

            hiddenProgress()
            finish()
        }
        viewModel.findByCodeLicense(code)
    }

    private fun showProgress() {
        viewLoading.visibility = View.VISIBLE
    }

    private fun hiddenProgress() {
        viewLoading.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        barcodeView.resume()
    }

    override fun onPause() {
        super.onPause()
        barcodeView.pause()
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permisos concedidos, inicializar componentes
                initToolbar()
                initComponent()
                initClickListener()
            } else {
                // Permisos denegados, redirigir a PermissionDeniedActivity
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
            }
            else -> {
                Toast.makeText(applicationContext, item.title, Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}