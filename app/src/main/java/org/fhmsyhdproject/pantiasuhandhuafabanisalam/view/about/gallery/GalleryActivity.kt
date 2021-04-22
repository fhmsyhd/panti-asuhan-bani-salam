package org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.about.gallery

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.database.*
//import kotlinx.android.synthetic.main.item_article.view.*
import kotlinx.android.synthetic.main.item_galeri.view.*
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.R
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.data.Galeri
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.databinding.ActivityGalleryBinding
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.utils.AdapterUtil
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.home.detail.DetailContentActivity

class GalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGalleryBinding
    private lateinit var database: DatabaseReference
    lateinit var adapterGaleri: AdapterUtil<Galeri>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.apply {
            elevation = 0f
            title = "Gallery"
            setDisplayHomeAsUpEnabled(true)
        }

        database = FirebaseDatabase.getInstance().reference

        val listGaleri: MutableList<Galeri> = arrayListOf()

        database.child("galeri").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.childrenCount > 0) {
                    for (item: DataSnapshot in snapshot.children) {
                        item.getValue(Galeri::class.java)?.apply {
                            listGaleri.add(this)
                        }
                    }
                    adapterGaleri = AdapterUtil(
                            R.layout.item_galeri,
                            listGaleri,
                            { position, itemView, item ->
                                Glide.with(this@GalleryActivity).load(item.image)
                                        .into(itemView.imGambar)
                            },
                            { position, item ->
//                                val intent = Intent(this@GalleryActivity, DetailContentActivity::class.java)
//                                intent.putExtra("detail", item)
//                                startActivity(intent)
                            }
                    )
                    binding.rvGaleri.layoutManager =
                            GridLayoutManager(this@GalleryActivity, 3)
//                            LinearLayoutManager(this@GalleryActivity, LinearLayoutManager.VERTICAL, false)
                    binding.rvGaleri.adapter = adapterGaleri
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@GalleryActivity, "Could not read from database", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
//    savedInstanceState: Bundle?
//    ): View? {
//        binding = ActivityGalleryBinding.inflate(inflater, container, false)
//
//        return binding.root
//    }
//}


