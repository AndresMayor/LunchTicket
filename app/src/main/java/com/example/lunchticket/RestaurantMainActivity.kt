package com.example.lunchticket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.lunchticket.databinding.ActivityRestaurantMainBinding
import com.example.lunchticket.fragments.*

class RestaurantMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRestaurantMainBinding
    private lateinit var restaurantHomeFragment: RestaurantHomeFragment
    private lateinit var lunchRegisterFragment: LunchRegisterFragment
    private lateinit var restaurantHistoryFragment: RestaurantHistoryFragment
    private lateinit var restaurantProfileFragment: RestaurantProfileFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        restaurantHomeFragment = RestaurantHomeFragment.newInstance()
        lunchRegisterFragment = LunchRegisterFragment.newInstance()
        restaurantHistoryFragment = RestaurantHistoryFragment.newInstance()
        restaurantProfileFragment = RestaurantProfileFragment.newInstance()
        val checkStudentFragment = CheckStudentFragment.newInstance()

        binding.restaurantNavigationBar.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.restaurantHomeItem -> {
                    showFragment(restaurantHomeFragment)
                }
                R.id.lunchRegisterItem -> {
                    showFragment(lunchRegisterFragment)
                }
                R.id.restaurantHistoryItem -> {
                    showFragment(restaurantHistoryFragment)
                }
                R.id.restaurantProfileItem -> {
                    showFragment(restaurantProfileFragment)
                }
            }
            true
        }
        lunchRegisterFragment.checkStudentFragment = checkStudentFragment
        lunchRegisterFragment.restaurantMainActivity = this

        showFragment(restaurantHomeFragment)
    }

    fun showFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.restaurantFragmentContainer, fragment)
        transaction.commit()
    }

    private fun nextFragment(fragment: Fragment, tag: String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.restaurantFragmentContainer, fragment).addToBackStack(tag)
        transaction.commit()
    }
}