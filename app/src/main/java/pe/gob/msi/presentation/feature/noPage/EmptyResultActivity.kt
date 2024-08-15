package pe.gob.msi.presentation.feature.noPage

import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import pe.gob.msi.R
import pe.gob.msi.presentation.feature.auth.login.LoginActivity
import pe.gob.msi.presentation.feature.dashboard.DashboardActivity
import pe.gob.msi.presentation.utils.Tools

class EmptyResultActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnGoToHome: AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty_result)

        initToolbar()
        initComponent()
        initClickListener()

    }
    private fun initToolbar() {
        Tools.setSystemBarColor(this, R.color.white)
        Tools.setSystemBarLight(this)
    }

    private fun initComponent() {
        btnGoToHome = findViewById(R.id.btnGoToHome)
    }

    private fun initClickListener(){
        btnGoToHome.setOnClickListener(this)
    }

    private fun goToHome() {
        val intent = Intent( applicationContext, DashboardActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
    }


    override fun onClick(v: View) {
        when(v.id){
            R.id.btnGoToHome -> {
                goToHome()
                finish()
            }
        }
    }
}