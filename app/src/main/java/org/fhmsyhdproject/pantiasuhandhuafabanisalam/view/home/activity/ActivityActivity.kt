package org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.home.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.item_article.view.*
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.R
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.data.Article
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.databinding.ActivityActivityBinding
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.utils.AdapterUtil
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.home.detail.DetailContentActivity

class ActivityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityActivityBinding
    private lateinit var database: DatabaseReference
    lateinit var adapterArticle: AdapterUtil<Article>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.apply {
            elevation = 0f
            title = "Kegiatan Panti"
            setDisplayHomeAsUpEnabled(true)
        }

        database = FirebaseDatabase.getInstance().reference

        val listActivity: MutableList<Article> = arrayListOf()

        database.child("article").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.childrenCount > 0) {
                    for (item: DataSnapshot in snapshot.children) {
                        item.getValue(Article::class.java)?.apply {
                            listActivity.add(this)
                        }
                    }
                    adapterArticle = AdapterUtil(
                        R.layout.item_article,
                        listActivity,
                        { position, itemView, item ->
                            itemView.tv_judul_artikel.text = item.title
                            itemView.tv_isi_artikel.text = item.content
                            Glide.with(this@ActivityActivity).load(item.image)
                                .into(itemView.img_poster)
                        },
                        { position, item ->
                            val intent = Intent(this@ActivityActivity, DetailContentActivity::class.java)
                            intent.putExtra("detail", item)
                            startActivity(intent)
                        }
                    )
                    binding.rvActivity.layoutManager =
                        LinearLayoutManager(this@ActivityActivity, LinearLayoutManager.VERTICAL, false)
                    binding.rvActivity.adapter = adapterArticle
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ActivityActivity, "Could not read from database", Toast.LENGTH_LONG).show()
            }

        })
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}