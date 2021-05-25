package org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.about.member

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.item_anak.view.*
import kotlinx.android.synthetic.main.item_pengurus.view.*
import kotlinx.android.synthetic.main.item_pengurus.view.tv_nama
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.R
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.data.Anak
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.databinding.ActivityAdministratorBinding
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.databinding.ActivityChildrenBinding
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.utils.AdapterUtil
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.home.detail.DetailContentActivity

class ChildrenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChildrenBinding
    private lateinit var database: DatabaseReference
    lateinit var adapterAnak: AdapterUtil<Anak>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChildrenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.apply {
            elevation = 0f
            title = "Anak Panti"
            setDisplayHomeAsUpEnabled(true)
        }

        database = FirebaseDatabase.getInstance().reference

        val listAnak: MutableList<Anak> = arrayListOf()

        database.child("anak").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.childrenCount > 0) {
                    for (item: DataSnapshot in snapshot.children) {
                        item.getValue(Anak::class.java)?.apply {
                            listAnak.add(this)
                        }
                    }
                    adapterAnak = AdapterUtil(
                            R.layout.item_anak,
                            listAnak,
                            { position, itemView, item ->
                                itemView.tv_nama.text = item.name
                                itemView.tv_nomor.text = item.nomor
                                itemView.tv_tanggal_lahir.text = item.kelas
                                itemView.tv_umur.text = item.status
//                                itemView.tv_asal.text = item.asal
                                Glide.with(this@ChildrenActivity).load(item.image)
                                        .into(itemView.img_anak)
                            },
                            { position, item ->
//                                val intent = Intent(this@ChildrenActivity, DetailContentActivity::class.java)
//                                intent.putExtra("detail", item)
//                                startActivity(intent)
                            }
                    )
                    binding.rvAnak.layoutManager =
                            LinearLayoutManager(this@ChildrenActivity, LinearLayoutManager.VERTICAL, false)
                    binding.rvAnak.adapter = adapterAnak
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ChildrenActivity, "Could not read from database", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}