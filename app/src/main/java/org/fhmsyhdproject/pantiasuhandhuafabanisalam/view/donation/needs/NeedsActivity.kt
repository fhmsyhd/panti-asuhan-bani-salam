package org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.donation.needs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.android.synthetic.main.item_article.*
import kotlinx.android.synthetic.main.item_article.view.*
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.R
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.data.Article
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.databinding.ActivityNeedsBinding
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.utils.AdapterUtil
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.home.detail.DetailContentActivity

class NeedsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNeedsBinding
    private lateinit var database: DatabaseReference
    lateinit var adapterArticle: AdapterUtil<Article>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNeedsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.apply {
            elevation = 0f
            title = "Kebutuhan Panti"
            setDisplayHomeAsUpEnabled(true)
        }

        database = FirebaseDatabase.getInstance().reference

        val listNeeds: MutableList<Article> = arrayListOf()

        database.child("needs").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.childrenCount > 0) {
                    for (item: DataSnapshot in snapshot.children) {
                        item.getValue(Article::class.java)?.apply {
                            listNeeds.add(this)
                        }
                    }
                    adapterArticle = AdapterUtil(
                        R.layout.item_article,
                        listNeeds,
                        { position, itemView, item ->
                            itemView.tv_judul_artikel.text = item.title
                            itemView.tv_isi_artikel.text = item.content
                            Glide.with(this@NeedsActivity).load(item.image)
                                .into(itemView.img_poster)
                        },
                        { position, item ->
                            val intent = Intent(this@NeedsActivity, DetailContentActivity::class.java)
                            intent.putExtra("detail", item)
                            startActivity(intent)
                        }
                    )
                    binding.rvNeeds.layoutManager =
                        LinearLayoutManager(this@NeedsActivity, LinearLayoutManager.VERTICAL, false)
                    binding.rvNeeds.adapter = adapterArticle
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@NeedsActivity, "Could not read from database", Toast.LENGTH_LONG).show()
            }

        })

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}