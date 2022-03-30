package com.example.lunchticket.fragments

import HTTPSWebUtilDomi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.lunchticket.*
import com.example.lunchticket.databinding.FragmentLoginBinding
import com.example.lunchticket.util.Constants
import com.google.gson.Gson
import io.jsonwebtoken.Jwt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import io.jsonwebtoken.JwtException

import io.jsonwebtoken.Jwts
import java.util.*
import kotlin.collections.HashMap


class LoginFragment : Fragment() {

    // Fragmento para pantalla de login, luego de haber seleccionado un perfil

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        binding.backBtn.setOnClickListener {
            (activity as LoginActivity).supportFragmentManager.popBackStack()
        }
        binding.userIdentificationET.setText("401401")
        binding.userPassET.setText("15394725772654685941")
        binding.loginBtn.setOnClickListener {

            val user = binding.userIdentificationET.text.toString()
            val pass = binding.userPassET.text.toString()

            val queue = Volley.newRequestQueue(context)
            val url = Constants.AUTH_URL

            val hm = HashMap<String, String>()
            hm.put("username", user)
            hm.put("password", pass)

            lifecycleScope.launch(Dispatchers.IO) {
                val http = HTTPSWebUtilDomi()
                val response = http.POSTRequest(url, Gson().toJson(hm))
                Log.e(">>>", response)
                val person = Gson().fromJson(response, Person::class.java)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Log.e(">>>>", token(person.accessToken))
                }

                lateinit var intent: Intent
                when ("") {
                    "student" -> {
                        intent = Intent(context, ProfilePictureActivity::class.java)
                    }
                    "restaurant" -> {
                        Constants.code = "123.456.789-0"
                        intent = Intent(context, RestaurantMainActivity::class.java)
                    }
                    "financial" -> {
                        intent = Intent(context, FinancialMainActivity::class.java)
                    }
                }
                startActivity(intent)
                activity?.finish()
            }

        }
        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun token(token: String) : String {
        val token1 = "eyJhbGciOiJSUzI1NiJ9.eyJwZXJzSWQiOiI1NDU0OSIsInJvbGUiOiJST0xFX1VwZGF0ZS1uZXh1cyxST0xFX1Nob3ctZGFzaGJvYXJkLFJPTEVfU2hvdy1ob21lLFJPTEVfUXVlcnktbmVhci1jb250YWN0cyxST0xFX1VwZGF0ZS1zeW1wdG9tcyxST0xFX015LWNvbW11bml0aWVzLFJPTEVfQWRtaW4tbmVhci1jb250YWN0cyxST0xFX0dldC1wYXNzcG9ydCxST0xFX015LWRldmljZXMsUk9MRV9Tb2NpYWwtQ29udGFjcyxST0xFX015LUZvbGxvd3VwcyIsInBlcnNvbnMiOlt7InBlcnNJZCI6NTQ1NDksInBlcnNOYW1lIjoiRE9NSUNJQU5PICIsInBlcnNMYXN0bmFtZSI6IlJJTkNPTiBOSU5PIn1dLCJpbnN0aXR1dGlvbiI6MSwidXNlcm5hbWUiOiIxMTQzODQ4OTIyIiwiaWF0IjoxNjQ2MjU0MzkyLCJleHAiOjE2NDcxMTgzOTJ9.tlZzixXSP_wFDfTsaTHtcP8Yt6-IbfHHAgQT96xe6zQHPK-_IS_U8ItTmW3h7CsFNhszVZNLtf3Y02rtnGqyYj_AhbsOydTgrtvKk4phGWj-ycaZW4FlolN_cYL6eK18qnh-BU4qQlL9oT3zdgiBNc4H1dTyjWbUdnfGz4Oooab07D128vszwtRJBu9uaSa9-w6-yagy-mL_hFzdl-Tf2Zdxqmwv4VDYz1JCAp5yTG8VRf8xU6VUbFGk3d3k9zpoKHxJhZSKOd2FlTldR3qO9Ac3t9DzXjMkKsuE9B8kiffq9QTrWsmcKwiB5Z69CHoIy3LEKPf_8saUmyeW6pFqvAci00aIaSfX72EzYjVLfPU5qy8ZwuHNqpRnO6tLBrIm6HWiZhPxlE3RW40hqv-8WYL6UeIGCtqCdtKeE5cnT6NupbRwwhdr-hPCLdFhXiTDCx5XPPYJm81a240sPGoM8xLfkxx6kFp3RHT4xyayE6JceREVz5qPFAHL0NoRIZtsp6nRzvygeI42b64JLgmRJu7Cz5zTA0A2yliTFFFNFteD1LqcN1sjaTgzOIGjicNyPyVa_L0HufU7DGgoLOrMJVtJEgr9rBgZsc1J9owv7faH95EGoTVBx70nlhqV6lgxCWvgBsRePMEWY_MO5kOl1Zgm3LTn6Wjm8a7UQhAqFQE";
        val parts:List<String> = token.split(".")

        val decoder: Base64.Decoder = Base64.getUrlDecoder()

        val header = String(decoder.decode(parts[0]))
        val payload = String(decoder.decode(parts[1]))

        return payload

    }

    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    data class Person(var accessToken: String)

}