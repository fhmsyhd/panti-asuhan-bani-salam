package org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.about.member

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.R
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.databinding.ActivityChildrenBinding

class ChildrenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChildrenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChildrenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setActionBar()
    }

    private fun setActionBar(){
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.apply {
            elevation = 0f
            title = "Data Anak"
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}