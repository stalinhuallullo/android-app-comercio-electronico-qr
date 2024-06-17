package pe.gob.msi.presentation.feature.searchResult

import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import pe.gob.msi.R
import pe.gob.msi.data.model.HttpResponseLicense
import pe.gob.msi.data.net.viewmodel.LicenseViewModel
import pe.gob.msi.presentation.feature.noPage.EmptyResultActivity
import pe.gob.msi.presentation.utils.Tools

class SearchResultActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var tvTradeName: TextView
    private lateinit var tvDistrict: TextView
    private lateinit var tvProvince: TextView
    private lateinit var tvDepartment: TextView
    private lateinit var tvSituatedIn: TextView
    private lateinit var tvRequestedBy: TextView
    private lateinit var tvMaximumBuildingCapacity: TextView
    private lateinit var tvTwistOrActivity: TextView
    private lateinit var tvOccupiedArea: TextView
    private lateinit var tvCoveredArea: TextView
    private lateinit var tvFileNo: TextView
    private lateinit var tvResolutionNo: TextView
    private lateinit var tvValidity: TextView
    private lateinit var tvExpeditionDate: TextView
    private lateinit var tvDateOfExpiry: TextView
    private lateinit var tvRenewalRequestDate: TextView

    private lateinit var viewModel: LicenseViewModel
    private var licensesHttp: HttpResponseLicense? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        initToolbar()
        initComponent()
        initClickListener()
        getData()
    }



    private fun initToolbar() {
        toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        //toolbar.setNavigationIcon(R.drawable.ic_notes)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.navigationIcon?.colorFilter = PorterDuffColorFilter(resources.getColor(R.color.grey_60, theme), PorterDuff.Mode.SRC_ATOP)
        toolbar.setBackgroundColor(resources.getColor(R.color.white, theme))
        toolbar.setTitleTextColor(resources.getColor(R.color.black, theme))
        toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
        setSupportActionBar(toolbar)

        supportActionBar!!.title = "Resultado"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        Tools.setSystemBarColor(this, R.color.white)
        Tools.setSystemBarLight(this)
    }

    private fun initComponent() {
        tvTradeName = findViewById(R.id.tvTradeName)
        tvDistrict = findViewById(R.id.tvDistrict)
        tvProvince = findViewById(R.id.tvProvince)
        tvDepartment = findViewById(R.id.tvDepartment)
        tvSituatedIn = findViewById(R.id.tvSituatedIn)
        tvRequestedBy = findViewById(R.id.tvRequestedBy)
        tvMaximumBuildingCapacity = findViewById(R.id.tvMaximumBuildingCapacity)
        tvTwistOrActivity = findViewById(R.id.tvTwistOrActivity)
        tvOccupiedArea = findViewById(R.id.tvOccupiedArea)
        tvCoveredArea = findViewById(R.id.tvCoveredArea)
        tvFileNo = findViewById(R.id.tvFileNo)
        tvResolutionNo = findViewById(R.id.tvResolutionNo)
        tvValidity = findViewById(R.id.tvValidity)

        tvExpeditionDate = findViewById(R.id.tvExpeditionDate)
        tvDateOfExpiry = findViewById(R.id.tvDateOfExpiry)
        tvRenewalRequestDate = findViewById(R.id.tvRenewalRequestDate)

    }

    private fun initClickListener(){
    }

    private fun showDataLicense() {
        if(licensesHttp != null){
            val license = licensesHttp?.Datos?.get(0)
            tvTradeName.text = license!!.TXTNOMBRECOMERCIAL
            tvDistrict.text = "--"
            tvProvince.text = "--"
            tvDepartment.text = "--"
            tvSituatedIn.text = "--"
            tvRequestedBy.text = "--"
            tvMaximumBuildingCapacity.text = "--"
            tvTwistOrActivity.text = license!!.TXTGIRO
            tvOccupiedArea.text = license!!.NUMAREA
            tvCoveredArea.text = license!!.NUMAREATECHADA
            tvFileNo.text = license!!.TXTEXPEDIENTE
            tvResolutionNo.text = license!!.TXTRESOLUCION
            tvValidity.text = "--"
            tvExpeditionDate.text = "--"
            tvDateOfExpiry.text = license!!.FECCADUCIDAD
            tvRenewalRequestDate.text = license!!.FECRENOVACION
        }
    }
    private fun getData(){
        println("011111111111")
        viewModel = ViewModelProvider(this)[LicenseViewModel::class.java]
        println("022222222222")
        viewModel.licenseLiveData.observe(this) { response ->
            response?.let {
                if (it.CodigoRespuesta == "01" && it.TXTRESPUESTA == "Exito") {
                    licensesHttp = it
                    showDataLicense()
                } else {
                    // Error, handle the error message
                    //Toast.makeText(this, "ERROR ===> ${it.Respuesta}", Toast.LENGTH_SHORT).show()
                    goToResultEmpty()
                }
            }
        }
        println("033333333333")
        // Call your API
        viewModel.findByCodeLicense("2020-000004")
    }
    fun goToResultEmpty() {
        val intent = Intent(this, EmptyResultActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }

}