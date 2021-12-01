package com.example.lunchticket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.lunchticket.databinding.ActivityLoginBinding
import com.example.lunchticket.fragments.LoginFragment
import com.example.lunchticket.fragments.RoleFragment

class LoginActivity : AppCompatActivity() {

    // Actividad que abarca la seleccion del rol y el login

    lateinit var roleFragment: RoleFragment
    lateinit var loginFragment: LoginFragment
    lateinit var binding: ActivityLoginBinding
    var selectedRole: String = "student"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        roleFragment = RoleFragment.newInstance()
        loginFragment = LoginFragment.newInstance()
        showFragment(roleFragment)
    }

    private fun showFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.loginFragmentContainer, fragment)
        transaction.commit()
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if(count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}