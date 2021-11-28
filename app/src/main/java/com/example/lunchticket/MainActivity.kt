package com.example.lunchticket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.lunchticket.databinding.ActivityMainBinding
import com.example.lunchticket.fragments.HomeFragment
import com.example.lunchticket.fragments.RequestLunchFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var homeFragment: HomeFragment
    lateinit var requestLunchFragment: RequestLunchFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        homeFragment = HomeFragment.newInstance()
        requestLunchFragment = RequestLunchFragment.newInstance()
        showFragment(homeFragment)
    }

    private fun showFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mainFragmentContainer, fragment)
        transaction.commit()
    }

    private fun nextFragment(fragment: Fragment, tag: String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.loginFragmentContainer, fragment).addToBackStack(tag)
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