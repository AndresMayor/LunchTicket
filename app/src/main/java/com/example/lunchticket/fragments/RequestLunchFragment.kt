package com.example.lunchticket.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.lunchticket.PostListActivity
import com.example.lunchticket.ShowMapActivity
import com.example.lunchticket.databinding.FragmentRequestLunchBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class RequestLunchFragment : Fragment() {

    // Fragmento para generar el codigo QR del perfil del estudiante

    private var _binding: FragmentRequestLunchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRequestLunchBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        //runCounter()

        binding.viewRestaurantsBtn.setOnClickListener(){
            val intent = Intent(context, ShowMapActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    companion object {
        fun newInstance() = RequestLunchFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun runCounter() {
        lifecycleScope.launch {
            // First lower and upper limits are defined.
            /*
             * Timezone is directly given as a user may be using a VPN and mistakenly consider they
             * have access to service when in reality they do not.
            */
            val lowerLimit = Calendar.getInstance(TimeZone.getTimeZone("GMT-5"))
            lowerLimit.set(Calendar.HOUR_OF_DAY, 11)
            lowerLimit.set(Calendar.MINUTE, 0)
            val upperLimit = Calendar.getInstance(TimeZone.getTimeZone("GMT-5"))
            upperLimit.set(Calendar.HOUR_OF_DAY, 24)
            upperLimit.set(Calendar.MINUTE, 0)

            lifecycleScope.launch {
                var currentTime = Calendar.getInstance(TimeZone.getTimeZone("GMT-5"))
                while(currentTime in lowerLimit..upperLimit) {
                    createQRCode(currentTime)
                    var counterNotFinished = true
                    var second = 30
                    while(counterNotFinished) {
                        binding.qrTimerTV.text = "${second}s"
                        delay(1000)
                        second--
                        if (second == 0) {
                            counterNotFinished = false
                        }
                    }
                    currentTime = Calendar.getInstance(TimeZone.getTimeZone("GMT-5"))
                }
            }

        }
    }

    private fun createQRCode(date: Calendar) {
        val size = 512
        val bitMatrix = QRCodeWriter().encode(
            String(
                date.toString().toByteArray()
            ),
            BarcodeFormat.QR_CODE, size, size
        )
        val bitmap = Bitmap.createBitmap(512, size, Bitmap.Config.ARGB_8888).also {
            for (x in 0 until 512) {
                for (y in 0 until size) {
                    it.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        }
        binding.qrImage.setImageBitmap(bitmap)
    }



}