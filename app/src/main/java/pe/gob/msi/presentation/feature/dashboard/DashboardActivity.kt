package pe.gob.msi.presentation.feature.dashboard

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import pe.gob.msi.R
import pe.gob.msi.presentation.feature.SearchForCodeActivity
import pe.gob.msi.presentation.feature.camera.CameraQrActivity
import pe.gob.msi.presentation.utils.Tools

class DashboardActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var toolbar: Toolbar



    private lateinit var btnQueryCamara: CardView
    private lateinit var btnQueryForm: CardView

    //var nav_view: NavigationView? = null
    //var drawer: DrawerLayout? = null

    private val sharedPref: SharedPreferences? = null
    var STRING_PREFERENCES: String = "licencias_sesion"

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

        supportActionBar!!.title = "Dashboard"
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

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnQueryCamara -> {
                val intentCamara = Intent(
                    applicationContext,
                    CameraQrActivity::class.java
                ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                startActivity(intentCamara)
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