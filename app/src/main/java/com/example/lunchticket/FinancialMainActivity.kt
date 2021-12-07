package com.example.lunchticket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.lunchticket.databinding.ActivityFinancialMainBinding
import com.example.lunchticket.fragments.FinancialHomeFragment
import com.example.lunchticket.fragments.FinancialProfileFragment
import com.example.lunchticket.fragments.FinancialRegisterFragment

class FinancialMainActivity : AppCompatActivity() {

    private lateinit var financialHomeFragment: FinancialHomeFragment
    private lateinit var financialRegisterFragment: FinancialRegisterFragment
    private lateinit var financialProfileFragment: FinancialProfileFragment
    private lateinit var binding: ActivityFinancialMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinancialMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        financialHomeFragment = FinancialHomeFragment.newInstance()
        financialRegisterFragment = FinancialRegisterFragment.newInstance()
        financialProfileFragment = FinancialProfileFragment.newInstance()

        binding.financialNavigationBar.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.financialHomeItem -> {
                    showFragment(financialHomeFragment)
                }
                R.id.financialRegisterItem -> {
                    showFragment(financialRegisterFragment)
                }
                R.id.financialProfileItem -> {
                    showFragment(financialProfileFragment)
                }
            }
            true
        }

        showFragment(financialHomeFragment)
    }

    private fun showFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.financialFragmentContainer, fragment)
        transaction.commit()
    }

    private fun nextFragment(fragment: Fragment, tag: String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.financialFragmentContainer, fragment).addToBackStack(tag)
        transaction.commit()
    }
}