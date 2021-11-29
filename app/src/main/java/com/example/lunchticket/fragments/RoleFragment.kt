package com.example.lunchticket.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.example.lunchticket.LoginActivity
import com.example.lunchticket.R
import com.example.lunchticket.databinding.FragmentRoleBinding

class RoleFragment : Fragment() {

    // Fragmento para seleccionar rol del usuario

    private var _binding: FragmentRoleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRoleBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        binding.studentRoleBtn.setOnClickListener {
            (activity as LoginActivity).supportFragmentManager
                .beginTransaction().replace(
                    R.id.loginFragmentContainer, (activity as LoginActivity).loginFragment)
                .addToBackStack("login").commit()

            //showFragment((activity as LoginActivity).loginFragment)
        }
        return view
    }

    companion object {
        fun newInstance() = RoleFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}