package pe.gob.msi.presentation.feature.about


import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pe.gob.msi.R
import pe.gob.msi.databinding.ActivityAboutBinding
import pe.gob.msi.presentation.base.mvp.ActivityView
import pe.gob.msi.presentation.feature.dashboard.DashboardActivity
import pe.gob.msi.presentation.utils.Tools


class AboutActivity : AppCompatActivity(), ActivityView {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        //setContentView(R.layout.activity_about)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        initToolbar()
        initComponent()
        initClickListener()
    }

    override fun initToolbar() {
        var toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.navigationIcon?.colorFilter = PorterDuffColorFilter(resources.getColor(R.color.grey_60, theme), PorterDuff.Mode.SRC_ATOP)
        toolbar.setBackgroundColor(resources.getColor(R.color.white, theme))
        toolbar.setTitleTextColor(resources.getColor(R.color.black, theme))
        setSupportActionBar(toolbar)

        supportActionBar!!.title = getString(R.string.application_info)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        Tools.setSystemBarColor(this, R.color.white)
        Tools.setSystemBarLight(this)
    }

    override fun initComponent() {

        // Get the package manager instance
        val packageManager = packageManager
        val packageInfo = packageManager.getPackageInfo(
            packageName, 0
        )
        val versionName = packageInfo.versionName

        binding.tvVersion.text = versionName
        binding.tvDateUpdate.text =  "26 de Junio del 2024"
    }

    override fun initClickListener() {

    }

    private fun goToInicio() {
        val intent = Intent( applicationContext, DashboardActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                goToInicio()
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }


}