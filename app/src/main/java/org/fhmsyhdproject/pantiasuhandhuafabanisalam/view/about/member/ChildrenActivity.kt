package org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.about.member

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.item_anak.view.*
import kotlinx.android.synthetic.main.item_pengurus.view.*
import kotlinx.android.synthetic.main.item_pengurus.view.tv_nama
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.R
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.data.Anak
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.databinding.ActivityAdministratorBinding
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.databinding.ActivityChildrenBinding
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.utils.AdapterCallback
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.utils.AdapterUtil
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.utils.ReusableAdapter
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.home.detail.DetailContentActivity

class ChildrenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChildrenBinding
    private lateinit var database: DatabaseReference
    lateinit var adapterAnak: AdapterUtil<Anak>
    lateinit var adapter: ReusableAdapter<Anak>

    val listAnak: MutableList<Anak> = arrayListOf()

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

        listAnakUI()

    }

    fun listAnakUI(){
        database.child("anak").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.childrenCount > 0) {
                    for (item: DataSnapshot in snapshot.children) {
                        item.getValue(Anak::class.java)?.apply {
                            listAnak.add(this)
                        }
                    }
                    setupAdapterAnak(binding.rvAnak, listAnak)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ChildrenActivity, "Could not read from database", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun getCategoryKelas(string: String){
        val anakList: MutableList<Anak> = arrayListOf()
        anakList.clear()
        for (i in listAnak) {
            if (i.kategori == string){
                anakList.add(i)
            }
        }
        if (anakList.isEmpty()){
            binding.imageEmpty.visibility = View.VISIBLE
            binding.tvEmpty.visibility = View.VISIBLE
        } else {
            binding.imageEmpty.visibility = View.GONE
            binding.tvEmpty.visibility = View.GONE
        }
        setupAdapterAnak(binding.rvAnak, anakList)
    }

    fun getCategoryStatus(string: String){
        val anakList: MutableList<Anak> = arrayListOf()
        anakList.clear()
        for (i in listAnak) {
            if (i.status == string){
                anakList.add(i)
            }
        }
        if (anakList.isEmpty()){
            binding.imageEmpty.visibility = View.VISIBLE
            binding.tvEmpty.visibility = View.VISIBLE
        } else {
            binding.imageEmpty.visibility = View.GONE
            binding.tvEmpty.visibility = View.GONE
        }
        setupAdapterAnak(binding.rvAnak, anakList)
    }

    fun setupAdapterAnak(recyclerView: RecyclerView, items: List<Anak>){
        adapterAnak = AdapterUtil(
            R.layout.item_anak,
            items,
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

            }
        )
        recyclerView.layoutManager =
                LinearLayoutManager(this@ChildrenActivity, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapterAnak
    }

    fun setupAdapter(recyclerView: RecyclerView, items: List<Anak>){
        adapter.adapterCallback(adapterCallback)
            .setLayout(R.layout.item_anak)
            .filterable()
            .addData(items)
            .isVerticalView()
            .build(recyclerView)
    }

    private val adapterCallback = object : AdapterCallback<Anak> {
        override fun initComponent(itemView: View, data: Anak, itemIndex: Int) {
            itemView.tv_nama.text = data.name
            itemView.tv_nomor.text = data.nomor
            itemView.tv_tanggal_lahir.text = data.kelas
            itemView.tv_umur.text = data.status
//                                itemView.tv_asal.text = item.asal
            Glide.with(this@ChildrenActivity).load(data.image)
                                        .into(itemView.img_anak)
        }

        override fun onItemClicked(itemView: View, data: Anak, itemIndex: Int) {
//            val intent = Intent(this@KataIsyaratActivity, DetailKataIsyaratActivity::class.java)
//            intent.putExtra(INTENT_DETAIL, data)
//            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        return super.onCreateOptionsMenu(menu)
        val inflater = menuInflater
        inflater.inflate(R.menu.category_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.cat_all -> {
                setupAdapterAnak(binding.rvAnak, listAnak)
                binding.imageEmpty.visibility = View.GONE
                binding.tvEmpty.visibility = View.GONE
                Toast.makeText(this@ChildrenActivity, "Kategori Default", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.cat_sd -> {
                getCategoryKelas("SD")
                Toast.makeText(this@ChildrenActivity, "Kategori SD", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.cat_smp -> {
                getCategoryKelas("SMP")
                Toast.makeText(this@ChildrenActivity, "Kategori SMP", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.cat_sma -> {
                getCategoryKelas("SMA")
                Toast.makeText(this@ChildrenActivity, "Kategori SMA", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.cat_yatim -> {
                getCategoryStatus("Yatim")
                Toast.makeText(this@ChildrenActivity, "Anak Yatim", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.cat_yatimpiatu -> {
                getCategoryStatus("Yatim Piatu")
                Toast.makeText(this@ChildrenActivity, "Anak Yatim Piatu", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}