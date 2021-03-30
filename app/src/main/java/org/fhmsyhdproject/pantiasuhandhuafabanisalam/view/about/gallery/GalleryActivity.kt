package org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.about.gallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.R
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.databinding.ActivityGalleryBinding

class GalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_gallery)

        setActionBar()
    }

    private fun setActionBar(){
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.apply {
            elevation = 0f
            title = "Galeri"
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}