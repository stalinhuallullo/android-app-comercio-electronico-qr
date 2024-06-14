package pe.gob.msi.presentation.feature.camera

import android.os.Bundle
import android.view.View
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.CaptureActivity
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.journeyapps.barcodescanner.DefaultDecoderFactory
import pe.gob.msi.R
import pe.gob.msi.presentation.base.LoadingView

class CameraQrActivity : CaptureActivity() {
    private lateinit var barcodeView: DecoratedBarcodeView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_qr)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        barcodeView = findViewById(R.id.zxing_barcode_scanner)
        barcodeView.barcodeView.decoderFactory = DefaultDecoderFactory()
        barcodeView.initializeFromIntent(intent)
        barcodeView.decodeContinuous(callback)
    }



    private val callback = object : BarcodeCallback {
        override fun barcodeResult(result: BarcodeResult) {
            // Aquí manejar el resultado del escaneo
            if (result.text != null) {
                // Haz algo con el resultado del escaneo, por ejemplo, mostrarlo en un TextView
                // Puedes enviar el resultado de vuelta a la actividad principal
                val intent = intent
                intent.putExtra("SCAN_RESULT", result.text)
                setResult(RESULT_OK, intent)
                finish()
            }
        }

        override fun possibleResultPoints(resultPoints: List<com.google.zxing.ResultPoint>) {
            // Puedes manejar puntos de resultado posibles aquí si es necesario
        }
    }

    override fun onResume() {
        super.onResume()
        barcodeView.resume()
    }

    override fun onPause() {
        super.onPause()
        barcodeView.pause()
    }

}