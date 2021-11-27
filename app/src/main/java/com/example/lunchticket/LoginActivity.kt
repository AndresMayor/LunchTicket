package com.example.lunchticket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.lunchticket.databinding.ActivityLoginBinding
import com.example.lunchticket.fragments.RoleFragment

class LoginActivity : AppCompatActivity() {

    private lateinit var roleFragment: RoleFragment
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        roleFragment = RoleFragment.newInstance()

        showFragment(roleFragment)
    }

    private fun showFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.loginFragmentContainer, fragment)
        transaction.commit()
    }
}