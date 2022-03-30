package com.example.lunchticket.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.lunchticket.*
import com.example.lunchticket.databinding.FragmentRequestLunchBinding
import com.example.lunchticket.util.Constants
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
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
        runCounter()

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
            lowerLimit.set(Calendar.HOUR_OF_DAY, 5)
            lowerLimit.set(Calendar.MINUTE, 0)
            val upperLimit = Calendar.getInstance(TimeZone.getTimeZone("GMT-5"))
            upperLimit.set(Calendar.HOUR_OF_DAY, 24)
            upperLimit.set(Calendar.MINUTE, 0)

            lifecycleScope.launch {
                var currentTime = Calendar.getInstance(TimeZone.getTimeZone("GMT-5"))
                while(currentTime in lowerLimit..upperLimit) {

                    val queue = Volley.newRequestQueue(context)
                    val url = "${Constants.BASE_URL}/qrcode?user=${Constants.code}"

                    val stringRequest = StringRequest(
                        Request.Method.GET, url,
                        { response ->
                            createQRCode(response)
                        },
                        { error -> Toast.makeText(context,error.localizedMessage, Toast.LENGTH_SHORT).show() })
                    queue.add(stringRequest)

                    var counterNotFinished = true
                    var second = 30
                    while(counterNotFinished) {
                        delay(50)
                        val seconds = System.currentTimeMillis()/1000
                        second = (30 - ((seconds)%30)).toInt()
                        binding.qrTimerTV.text = "${second}s"
                        if (second == 30) {
                            counterNotFinished = false
                        }
                    }
                    currentTime = Calendar.getInstance(TimeZone.getTimeZone("GMT-5"))
                }
            }

        }
    }

    private fun createQRCode(msg : String) {
        val size = 512
        val bitMatrix = QRCodeWriter().encode(
            msg,
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