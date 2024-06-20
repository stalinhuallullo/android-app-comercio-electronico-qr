package pe.gob.msi.presentation.feature.form

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.SnackbarLayout
import com.google.android.material.textfield.TextInputEditText
import pe.gob.msi.R
import pe.gob.msi.data.model.HttpResponseLicense
import pe.gob.msi.data.net.viewmodel.LicenseViewModel
import pe.gob.msi.presentation.common.utils.OnSingleClickListener
import pe.gob.msi.presentation.feature.noPage.EmptyResultActivity
import pe.gob.msi.presentation.feature.searchResult.SearchResultActivity
import pe.gob.msi.presentation.utils.Constants
import pe.gob.msi.presentation.utils.Tools

class SearchForCodeActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar


    private lateinit var etCode: TextInputEditText
    private lateinit var etAnio: TextInputEditText
    private lateinit var btnSearch: AppCompatButton
    private lateinit var viewLoading: View
    private var parentView: View? = null

    private lateinit var viewModel: LicenseViewModel
    private var licensesHttp: HttpResponseLicense? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_for_code)

        initToolbar()
        initComponent()
        initClickListener()
    }

    private fun initToolbar() {

        toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.navigationIcon?.colorFilter = PorterDuffColorFilter(resources.getColor(R.color.grey_60, theme), PorterDuff.Mode.SRC_ATOP)
        toolbar.setBackgroundColor(resources.getColor(R.color.white, theme))
        toolbar.setTitleTextColor(resources.getColor(R.color.black, theme))
        toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
        setSupportActionBar(toolbar)

        supportActionBar!!.title = "Buscar por código"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        Tools.setSystemBarColor(this, R.color.white)
        Tools.setSystemBarLight(this)
    }
    private fun initComponent() {
        parentView = findViewById(android.R.id.content)
        viewLoading = findViewById(R.id.viewLoading)
        etCode = findViewById(R.id.etCode)
        etAnio = findViewById(R.id.etAnio)
        btnSearch = findViewById(R.id.btnSearch)

        // Initialize the ViewModel
        viewModel = ViewModelProvider(this)[LicenseViewModel::class.java]

    }

    private fun initClickListener() {
        btnSearch.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(v: View) {
                validateData()
                /*Toast.makeText(this@SearchForCodeActivity, "ssssssss", Toast.LENGTH_LONG).show()
                showProgress()
                Handler(Looper.getMainLooper()).postDelayed({
                    Toast.makeText(this@SearchForCodeActivity, "delay 2000", Toast.LENGTH_LONG).show()

                    hiddenProgress()
                }, 2000)*/
            }
        })
    }

    private fun validateData() {
        val codigo: String = etCode.getText().toString()
        val anio = etAnio.text.toString()
        var msj = ""

        if (codigo.length <= 0) msj += "Ingresar un código valido.\r\n"
        if (anio.length <= 0) msj += "Ingresar un año valido."

        if (msj.length <= 0) getData(codigo, anio)
        else snackBarIconError(msj)
    }

    private fun getData(code: String, anio: String){
        showProgress()
        viewModel.licenseLiveData.observe(this) { response ->
            if (response.CodigoRespuesta == "01" && response.TXTRESPUESTA == "Exito" && response.Datos?.size!! > 0) {
                licensesHttp = response
                goToResult()
                finish()
            } else {
                //goToResultEmpty()
                snackBarIconInfo("No se encontro resultado. \r\nIntenta nuevamente")
            }

            hiddenProgress()

        }
        viewModel.findByCodeLicense("$anio-$code")
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

    private fun showProgress() {
        viewLoading.visibility = View.VISIBLE
    }

    private fun hiddenProgress() {
        viewLoading.visibility = View.GONE
    }

    @SuppressLint("RestrictedApi")
    private fun snackBarIconError(msj: String) {
        val snackbar = Snackbar.make(parentView!!, "", Snackbar.LENGTH_SHORT)
        //inflate view
        val custom_view: View = layoutInflater.inflate(R.layout.snackbar_icon_text, null)

        snackbar.view.setBackgroundColor(Color.TRANSPARENT)
        val snackBarView = snackbar.view as SnackbarLayout
        snackBarView.setPadding(0, 0, 0, 0)

        (custom_view.findViewById<View>(R.id.message) as TextView).text = msj
        (custom_view.findViewById<View>(R.id.icon) as ImageView).setImageResource(R.drawable.ic_close)
        custom_view.findViewById<View>(R.id.parent_view).setBackgroundColor(
            resources.getColor(R.color.red_600)
        )
        snackBarView.addView(custom_view, 0)
        snackbar.show()
    }

    @SuppressLint("RestrictedApi")
    private fun snackBarIconInfo(msj: String) {
        val snackbar = Snackbar.make(parentView!!, "", Snackbar.LENGTH_SHORT)
        //inflate view
        val custom_view = layoutInflater.inflate(R.layout.snackbar_icon_text, null)

        snackbar.view.setBackgroundColor(Color.TRANSPARENT)
        val snackBarView = snackbar.view as SnackbarLayout
        snackBarView.setPadding(0, 0, 0, 0)

        (custom_view.findViewById<View>(R.id.message) as TextView).text = msj
        (custom_view.findViewById<View>(R.id.icon) as ImageView).setImageResource(R.drawable.ic_error_outline)
        custom_view.findViewById<View>(R.id.parent_view).setBackgroundColor(
            resources.getColor(R.color.blue_500)
        )
        snackBarView.addView(custom_view, 0)
        snackbar.show()
    }

    /*override fun onClick(v: View) {
        when(v.id){
            R.id.btnSearch -> {

            }
        }
    }*/

}