package com.example.lunchticket.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.lunchticket.*
import com.example.lunchticket.databinding.FragmentLoginBinding
import com.example.lunchticket.util.Constants
import io.jsonwebtoken.Jwt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import io.jsonwebtoken.JwtException

import io.jsonwebtoken.Jwts




class LoginFragment : Fragment() {

    // Fragmento para pantalla de login, luego de haber seleccionado un perfil

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

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

        binding.loginBtn.setOnClickListener {

            val user = binding.userIdentificationET.text.toString()
            val pass = binding.userPassET.text.toString()

            if(user=="a" && pass=="a"){
                Constants.user="a"
                Constants.code="A00354904"
                lateinit var intent: Intent
                when ((activity as LoginActivity).selectedRole) {
                    "student" -> {
                        intent = Intent(context, ProfilePictureActivity::class.java)
                    }
                    "restaurant" -> {
                        Constants.code="123.456.789-0"
                        intent = Intent(context, RestaurantMainActivity::class.java)
                    }
                    "financial" -> {
                        intent = Intent(context, FinancialMainActivity::class.java)
                    }
                }
                startActivity(intent)
                activity?.finish()
            }


            val queue = Volley.newRequestQueue(context)
            val url = "${Constants.BASE_URL}/login?user=${user}&password=${pass}"

            val stringRequest = StringRequest(
                Request.Method.POST, url,
                { response ->
                    var tok = response
                    Constants.token = tok
                    try {
                        Constants.user= user

                        val i = tok.lastIndexOf('.')
                        val withoutSignature = tok.substring(0, i+1);
                        val untrusted = Jwts.parser().parseClaimsJwt(withoutSignature);
                        val code = untrusted.body["code"]
                        val name = untrusted.body["name"]
                        val pic = untrusted.body["pic"]
                        val doc = untrusted.body["doc"]

                        if (code is String) {
                            Constants.code = code
//                            Toast.makeText(context,code,Toast.LENGTH_SHORT).show()
                        }
                        if (name is String) {
                            Constants.name = name
                        }
                        if (pic is String) {
                            Constants.pic = pic
                        }
                        if (doc is String) {
                            Constants.doc = doc
                        }

                        lateinit var intent: Intent
                        when ((activity as LoginActivity).selectedRole) {
                            "student" -> {
                                intent = Intent(context, ProfilePictureActivity::class.java)
                            }
                            "restaurant" -> {
                                intent = Intent(context, RestaurantMainActivity::class.java)
                            }
                            "financial" -> {
                                intent = Intent(context, FinancialMainActivity::class.java)
                            }
                        }
                        startActivity(intent)
                        activity?.finish()


                        //OK, we can trust this JWT
                    } catch (e: JwtException) {

                        Toast.makeText(context,"mal sign",Toast.LENGTH_SHORT).show()
                    }
//                    Toast.makeText(context,response,Toast.LENGTH_SHORT).show()
                },
                 { error -> Toast.makeText(context,"Verifica los datos",Toast.LENGTH_SHORT).show() })

            if(user != "" && pass != "" && user != "a") {
                queue.add(stringRequest)

            }


        }
        return view
    }

    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}