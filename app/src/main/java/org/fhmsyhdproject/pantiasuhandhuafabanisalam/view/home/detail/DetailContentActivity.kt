package org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.home.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_content.*
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.R
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.data.Article
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.databinding.ActivityDetailContentBinding

class DetailContentActivity : AppCompatActivity() {

    private lateinit var itemContent: Article
    private lateinit var binding: ActivityDetailContentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        itemContent = intent.getSerializableExtra("detail") as Article

        initUI(itemContent)
        setActionBar()
    }

    private fun setActionBar(){
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.apply {
            elevation = 0f
            title = "Artikel"
            setDisplayHomeAsUpEnabled(true)
        }
    }

    fun initUI(item: Article) {
        tv_judul_detail.text = item.title
        tv_tanggal_artikel.text = item.date
        tv_isi_detail.text = item.content
        Glide.with(this).load(item.image)
            .into(header_detail)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}