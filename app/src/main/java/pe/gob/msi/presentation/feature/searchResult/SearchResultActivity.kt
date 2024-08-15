package pe.gob.msi.presentation.feature.searchResult

import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import pe.gob.msi.R
import pe.gob.msi.data.model.HttpResponseLicense
import pe.gob.msi.presentation.feature.dashboard.DashboardActivity
import pe.gob.msi.presentation.utils.Constants
import pe.gob.msi.presentation.utils.Tools

class SearchResultActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var tvTradeName: TextView
    private lateinit var tvLicense: TextView
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

    private var licensesHttp: HttpResponseLicense? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        initToolbar()
        initComponent()
        initClickListener()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            licensesHttp = intent.getParcelableExtra(Constants.SEARCH_KEY, HttpResponseLicense::class.java)
        } else {
            licensesHttp = intent.getParcelableExtra(Constants.SEARCH_KEY)
        }

        if(licensesHttp != null) showDataLicense()
    }



    private fun initToolbar() {
        toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.navigationIcon?.colorFilter = PorterDuffColorFilter(resources.getColor(R.color.grey_60, theme), PorterDuff.Mode.SRC_ATOP)
        toolbar.setBackgroundColor(resources.getColor(R.color.white, theme))
        toolbar.setTitleTextColor(resources.getColor(R.color.black, theme))
        setSupportActionBar(toolbar)

        supportActionBar!!.title = "Licencia"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        Tools.setSystemBarColor(this, R.color.white)
        Tools.setSystemBarLight(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                goToHome()
                finish()
                //toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
            }
            else -> {
                Toast.makeText(applicationContext, item.title, Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initComponent() {
        tvTradeName = findViewById(R.id.tvTradeName)
        tvLicense = findViewById(R.id.tvLicense)
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
            //supportActionBar!!.title = "Licencia ${license!!.TXTCERTIFICADODC}"

            tvTradeName.text = license!!.TXTNOMBRECOMERCIAL
            tvLicense.text = license!!.TXTGLOSACERTIFICADO

            //tvDistrict.text = "--"
            //tvProvince.text = "--"
            //tvDepartment.text = "--"
            tvSituatedIn.text = license!!.TXTDOMICILIO
            tvRequestedBy.text = license!!.TXTRAZONSOCIAL
            tvMaximumBuildingCapacity.text = license!!.TXTAFORO
            tvTwistOrActivity.text = license!!.TXTGIRO
            tvOccupiedArea.text = license!!.NUMAREA
            tvCoveredArea.text = license!!.NUMAREATECHADA
            tvFileNo.text = license!!.TXTEXPEDIENTE
            tvResolutionNo.text = license!!.TXTRESOLUCION
            //tvValidity.text = "--"
            tvExpeditionDate.text = license!!.FECEMISION
            tvDateOfExpiry.text = license!!.FECCADUCIDAD
            tvRenewalRequestDate.text = license!!.FECRENOVACION
        }
    }

    fun goToHome() {
        val intent = Intent( applicationContext, DashboardActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }

}