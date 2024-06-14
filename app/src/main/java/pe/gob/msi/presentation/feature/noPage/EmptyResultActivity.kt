package pe.gob.msi.presentation.feature.noPage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import pe.gob.msi.R
import pe.gob.msi.presentation.feature.dashboard.DashboardActivity
import pe.gob.msi.presentation.utils.Tools

class EmptyResultActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnGoToHome: AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty_result)

        initComponent()
        initClickListener()
        Tools.setSystemBarColor(this, android.R.color.white)
        Tools.setSystemBarLight(this)
    }

    private fun initComponent() {
        btnGoToHome = findViewById(R.id.btnGoToHome)
    }

    private fun initClickListener(){
        btnGoToHome.setOnClickListener(this)
    }

    private fun goToHome() {
        val intent = Intent(this, DashboardActivity::class.java)
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