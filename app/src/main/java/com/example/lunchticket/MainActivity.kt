package com.example.lunchticket

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.lunchticket.databinding.ActivityMainBinding
import com.example.lunchticket.fragments.HomeFragment
import com.example.lunchticket.fragments.RequestLunchFragment
import com.example.lunchticket.fragments.RestaurantHomeFragment
import com.example.lunchticket.fragments.StudentProfileFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var homeFragment: HomeFragment
    lateinit var requestLunchFragment: RequestLunchFragment
    lateinit var studentProfileFragment: StudentProfileFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        homeFragment = HomeFragment.newInstance()
        requestLunchFragment = RequestLunchFragment.newInstance()
        studentProfileFragment = StudentProfileFragment.newInstance()
        showFragment(homeFragment)

        binding.studentNavigationBar.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.studentHomeItem -> {
                    showFragment(homeFragment)
                }
                R.id.requestItem -> {
                    showFragment(requestLunchFragment)
                }
                R.id.studentProfileItem -> {
                    showFragment(studentProfileFragment)
                }
            }
            true
        }
    }

    private fun showFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mainFragmentContainer, fragment)
        transaction.commit()
    }

    private fun nextFragment(fragment: Fragment, tag: String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mainFragmentContainer, fragment).addToBackStack(tag)
        transaction.commit()
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }



}