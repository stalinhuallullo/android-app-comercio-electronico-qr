package pe.gob.msi.presentation.common

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.util.AttributeSet
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.ViewGroup
import java.io.IOException
class CameraSourcePreview(private val mContext: Context, attrs: AttributeSet?) :
    ViewGroup(mContext, attrs) {
    /* private val mSurfaceView = SurfaceView(mContext)
    private var mStartRequested = false
    private var mSurfaceAvailable = false
    private var mCameraSource: CameraSource? = null


    @Throws(IOException::class)
    fun start(cameraSource: CameraSource?) {
        if (cameraSource == null) {
            stop()
        }

        mCameraSource = cameraSource

        if (mCameraSource != null) {
            mStartRequested = true
            startIfReady()
        }
    }

    fun stop() {
        if (mCameraSource != null) {
            mCameraSource.stop()
        }
    }

    fun release() {
        if (mCameraSource != null) {
            mCameraSource.release()
            mCameraSource = null
        }
    }

    private val requestCode = 101

    init {
        mSurfaceView.holder.addCallback(SurfaceCallback())
        addView(mSurfaceView)
    }

    @Throws(IOException::class)
    private fun startIfReady() {
        if (mStartRequested && mSurfaceAvailable) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.CAMERA
                ) !== PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    context as Activity,
                    arrayOf<String>(Manifest.permission.CAMERA),
                    requestCode
                )
                return
            }
            mCameraSource.start(mSurfaceView.holder)
            VisionApiCameraFix.cameraFocus(
                mCameraSource,
                Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE
            )
            mStartRequested = false
        }
    }

    private inner class SurfaceCallback : SurfaceHolder.Callback {
        override fun surfaceCreated(surface: SurfaceHolder) {
            mSurfaceAvailable = true
            try {
                startIfReady()
            } catch (e: IOException) {
                Log.e(TAG, "Could not start camera source.", e)
            }
        }

        override fun surfaceDestroyed(surface: SurfaceHolder) {
            mSurfaceAvailable = false
        }

        override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        var width = 320
        var height = 240

        if (mCameraSource != null) {
            val size: Size = mCameraSource.getPreviewSize()
            if (size != null) {
                width = size.getWidth()
                height = size.getHeight()
            }
        }

        // Swap width and height sizes when in portrait, since it will be rotated 90 degrees
        if (isPortraitMode) {
            val tmp = width
            width = height
            height = tmp
        }

        val layoutWidth = right - left
        val layoutHeight = bottom - top

        // Computes height and width for potentially doing fit width.
        var childWidth = layoutWidth
        var childHeight = ((layoutWidth.toFloat() / width.toFloat()) * height).toInt()

        // If height is too tall using fit width, does fit height instead.
        if (childHeight > layoutHeight) {
            childHeight = layoutHeight
            childWidth = ((layoutHeight.toFloat() / height.toFloat()) * width).toInt()
        }

        for (i in 0 until childCount) {
            getChildAt(i).layout(0, 0, childWidth, childHeight)
            //getChildAt(i).layout(0, 0, width    , height);
        }

        try {
            startIfReady()
        } catch (e: IOException) {
            Log.e(TAG, "Could not start camera source.", e)
        }
    }

    private val isPortraitMode: Boolean
        get() {
            val orientation = mContext.resources.configuration.orientation
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                return false
            }
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                return true
            }

            Log.d(TAG, "isPortraitMode returning false by default")
            return false
        }

    companion object {
        private const val TAG = "CameraSourcePreview"
    }*/
    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        TODO("Not yet implemented")
    }
}
