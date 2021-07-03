package org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.about.pantilain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_content.*
import kotlinx.android.synthetic.main.activity_detail_orphange.*
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.R
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.data.Article
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.data.Orphanage
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.databinding.ActivityDetailOrphangeBinding

class DetailOrphangeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailOrphangeBinding

    private var nomor = ""
    private var alamat = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailOrphangeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.apply {
            elevation = 0f
            title = "Panti Asuhan"
            setDisplayHomeAsUpEnabled(true)
        }

        val itemOrphanage = intent?.getParcelableExtra<Orphanage>("detailOrphanage")

        if (itemOrphanage != null) {
            initUI(itemOrphanage)
        }
    }

    private fun initUI(item: Orphanage){
        Glide.with(this).load(item.gambar)
            .into(img_panti)
        Glide.with(this).load(item.logo)
            .into(img_logo)
        tv_nama_panti.text = item.nama
        nomor = item.nomor.toString()
        alamat = item.alamat.toString()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}