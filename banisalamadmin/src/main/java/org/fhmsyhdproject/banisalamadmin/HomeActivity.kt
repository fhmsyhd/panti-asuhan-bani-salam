package org.fhmsyhdproject.banisalamadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.fhmsyhdproject.banisalamadmin.addimage.AddImageActivity
import org.fhmsyhdproject.banisalamadmin.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.apply {
            elevation = 0f
            title = "Bani Salam Admin"
        }

        initUI()
    }

    private fun initUI(){
        binding.btnAddActivity.setOnClickListener {
            val intent = Intent(this, AddActivityActivity::class.java)
            startActivity(intent)
        }
        binding.btnAddNeeds.setOnClickListener{
            val intent = Intent(this, AddKebutuhanActivity::class.java)
            startActivity(intent)
        }
        binding.btnAddArtikel.setOnClickListener{
            val intent = Intent(this, AddArtikelActivity::class.java)
            startActivity(intent)
        }
        binding.btnAddImage.setOnClickListener{
            val intent = Intent(this, AddImageActivity::class.java)
            startActivity(intent)
        }
    }
}