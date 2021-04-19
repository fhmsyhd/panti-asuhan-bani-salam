package org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.about.member

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_administrator.view.*
//import kotlinx.android.synthetic.main.item_article.view.*
//import kotlinx.android.synthetic.main.item_galeri.view.*
import kotlinx.android.synthetic.main.item_pengurus.view.*
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.R
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.data.Pengurus
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.databinding.ActivityAdministratorBinding
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.databinding.ItemPengurusBinding
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.utils.AdapterUtil
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.home.detail.DetailContentActivity

class AdministratorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdministratorBinding
    private lateinit var database: DatabaseReference
    lateinit var adapterPengurus: AdapterUtil<Pengurus>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdministratorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.apply {
            elevation = 0f
            title = "Pengurus Panti"
            setDisplayHomeAsUpEnabled(true)
        }

        database = FirebaseDatabase.getInstance().reference

        val listPengurus: MutableList<Pengurus> = arrayListOf()

        database.child("pengurus").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.childrenCount > 0) {
                    for (item: DataSnapshot in snapshot.children) {
                        item.getValue(Pengurus::class.java)?.apply {
                            listPengurus.add(this)
                        }
                    }
                    adapterPengurus = AdapterUtil(
                            R.layout.item_pengurus,
                            listPengurus,
                            { position, itemView, item ->
                                itemView.tv_nama.text = item.name
                                itemView.tv_jabatan.text = item.jabatan
                                Glide.with(this@AdministratorActivity).load(item.image)
                                        .into(itemView.img_poster_pengurus)
                            },
                            { position, item ->
                                val intent = Intent(this@AdministratorActivity, DetailContentActivity::class.java)
                                intent.putExtra("detail", item)
                                startActivity(intent)
                            }
                    )
                    binding.rvPengurus.layoutManager =
                            LinearLayoutManager(this@AdministratorActivity, LinearLayoutManager.VERTICAL, false)
                    binding.rvPengurus.adapter = adapterPengurus
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@AdministratorActivity, "Could not read from database", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
