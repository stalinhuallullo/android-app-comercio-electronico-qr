package pe.gob.msi.presentation.feature.dashboard

import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import pe.gob.msi.presentation.base.mvp.LoadingView
import pe.gob.msi.presentation.feature.SearchForCodeActivity
import pe.gob.msi.presentation.feature.camera.CameraQrActivity
import pe.gob.msi.presentation.feature.noPage.EmptyResultActivity
import pe.gob.msi.presentation.feature.searchResult.SearchResultActivity
import pe.gob.msi.presentation.utils.Constants
import pe.gob.msi.presentation.utils.Tools

class DashboardActivity : AppCompatActivity(), View.OnClickListener , LoadingView {
    private lateinit var toolbar: Toolbar
    private lateinit var btnQueryCamara: CardView
    private lateinit var btnQueryForm: CardView
    private lateinit var scanQrResultLauncher : ActivityResultLauncher<Intent>
    private lateinit var viewLoading: View

    private lateinit var codeSearch: String

    //var nav_view: NavigationView? = null
    //var drawer: DrawerLayout? = null


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
        viewLoading = findViewById(R.id.viewLoading)
        btnQueryCamara = findViewById(R.id.btnQueryCamara)
        btnQueryForm = findViewById(R.id.btnQueryForm)

    }
    private fun initClickListener(){
        btnQueryCamara.setOnClickListener(this)
        btnQueryForm.setOnClickListener(this)
    }

    private fun setupScanner() {
        scanQrResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                showLoading()

                val results = ScanIntentResult.parseActivityResult(
                    result.resultCode,
                    result.data
                )

                if (results.contents == null) {
                    hideLoading()
                    Toast.makeText(baseContext, "Cancelado", Toast.LENGTH_LONG).show()
                } else {
                    /*showLoading()
                    Handler(Looper.getMainLooper()).postDelayed({
                        hideLoading()
                        goToResult()
                    }, 3000)*/
                    println("results ===> $results")
                    println("result.data ===> ${result.data}")
                    println("result.resultCode ===> ${result.resultCode}")
                    codeSearch = result.data.toString()
                        goToResult()
                    hideLoading()
                    //messageText.text = result.contents
                    //messageFormat.text = result.formatName
                }
            }
        }
    }

    private fun goToReadQr() {
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
        options.setPrompt("Acerca la imagen del código QR a una distancia razonable para poder escanearla.")
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


    private fun goToResult() {
        val intent = Intent(this, SearchResultActivity::class.java)
        intent.putExtra(Constants.SEARCH_KEY, codeSearch)
        startActivity(intent)
    }

    fun goToResultEmpty() {
        val intent = Intent(this, EmptyResultActivity::class.java)
        startActivity(intent)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnQueryCamara -> {
                goToReadQr()
            }

            R.id.btnQueryForm -> {
                goToFormSearch()
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