package pe.gob.msi.presentation.feature.form

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.journeyapps.barcodescanner.DefaultDecoderFactory
import pe.gob.msi.R
import pe.gob.msi.data.net.viewmodel.LicenseViewModel
import pe.gob.msi.presentation.common.utils.OnSingleClickListener
import pe.gob.msi.presentation.utils.Tools

class SearchForCodeActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar


    private lateinit var etCode: TextInputEditText
    private lateinit var etAnio: TextInputEditText
    private lateinit var btnSearch: AppCompatButton
    private lateinit var viewLoading: View

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
        viewLoading = findViewById(R.id.viewLoading)
        etCode = findViewById(R.id.etCode)
        etAnio = findViewById(R.id.etAnio)
        btnSearch = findViewById(R.id.btnSearch)

    }

    private fun initClickListener() {
        btnSearch.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(v: View) {
                Toast.makeText(this@SearchForCodeActivity, "ssssssss", Toast.LENGTH_LONG).show()
                showProgress()
                Handler(Looper.getMainLooper()).postDelayed({
                    Toast.makeText(this@SearchForCodeActivity, "delay 2000", Toast.LENGTH_LONG).show()

                    hiddenProgress()
                }, 2000)
            }
        })
    }

    private fun showProgress() {
        viewLoading.visibility = View.VISIBLE
    }

    private fun hiddenProgress() {
        viewLoading.visibility = View.GONE
    }

    /*override fun onClick(v: View) {
        when(v.id){
            R.id.btnSearch -> {

            }
        }
    }*/

}