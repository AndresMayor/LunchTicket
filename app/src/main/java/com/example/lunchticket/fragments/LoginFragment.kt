package com.example.lunchticket.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lunchticket.*
import com.example.lunchticket.databinding.FragmentLoginBinding

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