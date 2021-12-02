package com.example.lunchticket.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.Manifest
import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.graphics.ImageFormat
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.lunchticket.R
import com.example.lunchticket.databinding.FragmentLunchRegisterBinding
import android.graphics.ImageFormat.YUV_420_888
import android.graphics.ImageFormat.YUV_422_888
import android.graphics.ImageFormat.YUV_444_888

import com.google.zxing.multi.qrcode.QRCodeMultiReader

import com.google.zxing.common.HybridBinarizer

import android.graphics.ImageFormat.YUV_444_888

import android.graphics.ImageFormat.YUV_422_888

import android.graphics.ImageFormat.YUV_420_888
import com.google.zxing.*
import java.util.concurrent.Executor


// Fragmento para mostrar el lector QR del perfil de restaurante

class LunchRegisterFragment : Fragment() {

    private var _binding: FragmentLunchRegisterBinding? = null
    private val binding get() = _binding!!
    private var dialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLunchRegisterBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        if (allPermissionsGranted()) {
            val dialogBuilder: AlertDialog.Builder? = activity?.let {
                AlertDialog.Builder(it)
            }
            dialogBuilder?.setMessage("")
                ?.setTitle("QR encontrado")

            dialog = dialogBuilder?.create()

            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this.requireActivity(), REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }

        return view
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener(Runnable {
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.qrReaderView.surfaceProvider)
                }

            val imageAnalyzer = ImageAnalysis.Builder()
                .build()
                .also {
                    it.setAnalyzer(ContextCompat.getMainExecutor(requireContext()), QRCodeImageAnalyzer(object : QRCodeFoundListener {
                        override fun onQRCodeFound(qrCode: String?){
                            dialog?.setMessage(qrCode)
                            dialog?.show()
                        }
                        override fun qrCodeNotFound(){
                            dialog?.hide()
                        }
                    }))
                }

            // Select back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageAnalyzer)

            } catch(exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        fun newInstance() = LunchRegisterFragment()
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private class QRCodeImageAnalyzer (private val listener: QRCodeFoundListener) : ImageAnalysis.Analyzer {


        override fun analyze(image: ImageProxy) {

            if (image.format == YUV_420_888 || image.format == YUV_422_888 || image.format == YUV_444_888) {
                val byteBuffer = image.planes[0].buffer
                val imageData = ByteArray(byteBuffer.capacity())
                byteBuffer.get(imageData)

                val source = PlanarYUVLuminanceSource(
                    imageData,
                    image.width, image.height,
                    0, 0,
                    image.width, image.height,
                    false
                )
                val binaryBitmap = BinaryBitmap(HybridBinarizer(source))
                try {
                    val result = QRCodeMultiReader().decode(binaryBitmap)
                    listener.onQRCodeFound(result.getText())
                } catch (e: FormatException) {
                    listener.qrCodeNotFound()
                } catch (e: ChecksumException) {
                    listener.qrCodeNotFound()                } catch (e: NotFoundException) {
                    listener.qrCodeNotFound()
                }
            }

            image.close()
        }
    }

    interface QRCodeFoundListener {
        fun onQRCodeFound(qrCode: String?)
        fun qrCodeNotFound()
    }
}
