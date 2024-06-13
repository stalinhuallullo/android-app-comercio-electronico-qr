package pe.gob.msi.presentation.feature.prueba

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.zxing.client.android.Intents
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import pe.gob.msi.R


class ZxingActivity : AppCompatActivity(), View.OnClickListener {

    private val REQUEST_CAMERA_PERMISSION = 1
    private lateinit var scanBtnZXing : Button
    private lateinit var messageText : TextView
    private lateinit var messageFormat : TextView
    private lateinit var scanQrResultLauncher : ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_zxing)

        // referencing and initializing
        // the button and textviews
        scanBtnZXing = findViewById(R.id.scanBtnZXing)
        messageText = findViewById(R.id.textContent)
        messageFormat = findViewById(R.id.textFormat)

        // adding listener to the button for ZXing
        scanBtnZXing.setOnClickListener(this)


        validatePermisse()

    }

    private fun validatePermisse() {
        // Verificar permisos de cámara
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA_PERMISSION)
        } else {
            setupScanner()
        }
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
                    messageText.text = result.contents
                    messageFormat.text = result.formatName
                }
            }
        }
    }




    override fun onClick(v: View) {
        when(v.id){
            R.id.scanBtnZXing -> {

                val options = ScanOptions()
                options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
                options.setPrompt("Acerca la imagen del código QR a una distancia razonable para poder escanearla.")
                options.setCameraId(0) // Use a specific camera of the device
                options.setBeepEnabled(false)
                options.setBarcodeImageEnabled(true)
                options.captureActivity = CustomCaptureActivity::class.java
                //barcodeLauncher.launch(options);
                scanQrResultLauncher.launch(
                    ScanContract().createIntent(baseContext, options )
                )
            }

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                setupScanner()
            } else {
                // Permiso denegado, maneja esto según sea necesario
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        // if the intentResult is null then
        // toast a message as "cancelled"
        if (intentResult != null){
            if (intentResult.contents.isNullOrEmpty()){
                Toast.makeText(baseContext, "Cancelled", Toast.LENGTH_SHORT).show()
            }
            else{
                // if the intentResult is not null we'll set
                // the content and format of scan message
                messageText.text = intentResult.contents
                messageFormat.text = intentResult.formatName
            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}