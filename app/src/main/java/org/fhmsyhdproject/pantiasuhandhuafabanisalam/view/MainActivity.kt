package org.fhmsyhdproject.pantiasuhandhuafabanisalam.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.R
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.databinding.ActivityMainBinding
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.about.AboutFragment
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.donation.DonationFragment
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.home.HomeFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentId: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.setOnNavigationItemSelectedListener (mOnNavigationItemSelectedListener)

        val moveToDonation = intent.getBooleanExtra("moveToFragmentDonation", false)
        val moveToAbout = intent.getBooleanExtra("moveToFragmentAbout", false)

        when {
            moveToDonation -> {
                binding.bottomNav.menu.getItem(2).isChecked = true
                supportFragmentManager
                    .beginTransaction()
                    .replace(
                    R.id.home_frame,
                    DonationFragment()
                ).commit()
            }
            moveToAbout -> {
                binding.bottomNav.menu.getItem(3).isChecked = true
                supportFragmentManager
                    .beginTransaction()
                    .replace(
                    R.id.home_frame,
                    AboutFragment()
                ).commit()
            }
            else -> {
                supportFragmentManager.beginTransaction().replace(R.id.home_frame, HomeFragment()).commit()
            }
        }

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        val fragmentCheck = fun(fragmentId: Int, fragment: Fragment) {
            if (currentId != fragmentId) {
                supportFragmentManager.beginTransaction().replace(R.id.home_frame, fragment).commit()
            }
        }

        when(it.itemId) {
            R.id.page_home -> fragmentCheck(R.id.page_home, HomeFragment())
            R.id.page_donation -> fragmentCheck(R.id.page_donation, DonationFragment())
            R.id.page_about -> fragmentCheck(R.id.page_about, AboutFragment())
        }

        currentId =it.itemId
        true
    }
}