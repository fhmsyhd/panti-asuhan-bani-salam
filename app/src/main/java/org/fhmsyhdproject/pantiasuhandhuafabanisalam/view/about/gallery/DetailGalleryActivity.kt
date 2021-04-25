package org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.about.gallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_gallery.*
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.R
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.data.Galeri
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.databinding.ActivityDetailGalleryBinding

class DetailGalleryActivity : AppCompatActivity() {
    private lateinit var itemGaleri: Galeri
    private lateinit var binding: ActivityDetailGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        itemGaleri = intent.getSerializableExtra("galeri") as Galeri

        initUI(itemGaleri)
        setActionBar()
    }

    private fun setActionBar(){
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.apply {
            elevation = 0f
            title = "Detail Galeri"
            setDisplayHomeAsUpEnabled(true)
        }
    }

    fun initUI(item: Galeri) {
        Glide.with(this).load(item.image)
            .into(galeri_detail)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}