package pe.gob.msi.presentation.feature.dashboard

import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import pe.gob.msi.R
import pe.gob.msi.presentation.feature.searchResult.SearchResultActivity
import pe.gob.msi.presentation.feature.SearchForCodeActivity
import pe.gob.msi.presentation.feature.prueba.CustomCaptureActivity
import pe.gob.msi.presentation.utils.Tools

class DashboardActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var toolbar: Toolbar
    private lateinit var btnQueryCamara: CardView
    private lateinit var btnQueryForm: CardView
    private lateinit var scanQrResultLauncher : ActivityResultLauncher<Intent>

    //var nav_view: NavigationView? = null
    //var drawer: DrawerLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        initToolbar()
        initComponent()
        initClickListener()
        setupScanner()
    }

    private fun initToolbar() {
        toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        //toolbar.setNavigationIcon(R.drawable.ic_notes)
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
    }
    fun initClickListener(){
        btnQueryCamara.setOnClickListener(this)
        btnQueryForm.setOnClickListener(this)
    }

    private fun setupScanner() {
        scanQrResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode === RESULT_OK) {
                val result = ScanIntentResult.parseActivityResult(
                    result.resultCode,
                    result.data
                )

                //this will be qr activity result
                if (result.contents == null) {
                    Toast.makeText(baseContext, "Cancelado", Toast.LENGTH_LONG).show()
                } else {
                    goToResult()
                    //messageText.text = result.contents
                    //messageFormat.text = result.formatName
                }
            }
        }
    }

    fun goToResult() {
        val intent = Intent(this, SearchResultActivity::class.java)
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnQueryCamara -> {
                val options = ScanOptions()
                options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
                options.setPrompt("Acerca la imagen del código QR a una distancia razonable para poder escanearla.")
                options.setCameraId(0) // Use a specific camera of the device
                options.setBeepEnabled(false)
                options.setBarcodeImageEnabled(true)
                options.captureActivity = CustomCaptureActivity::class.java
                scanQrResultLauncher.launch(
                    ScanContract().createIntent(baseContext, options )
                )
            }

            R.id.btnQueryForm -> {
                val intentFormulario = Intent(
                    applicationContext,
                    SearchForCodeActivity::class.java
                ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                startActivity(intentFormulario)
            }
        }
    }

}