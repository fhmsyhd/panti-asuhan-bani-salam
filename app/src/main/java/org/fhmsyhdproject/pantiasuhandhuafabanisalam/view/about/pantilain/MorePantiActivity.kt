package org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.about.pantilain

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.item_article.view.*
import kotlinx.android.synthetic.main.item_panti_lain.view.*
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.R
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.data.Activity
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.data.Orphanage
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.databinding.ActivityMorePantiBinding
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.utils.AdapterCallback
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.utils.ReusableAdapter
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.about.AboutViewModel
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.home.detail.DetailContentActivity

class MorePantiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMorePantiBinding
    private lateinit var viewModel: AboutViewModel

    // adapter
    private lateinit var orphanAdapter: ReusableAdapter<Orphanage>

    // utils
    private lateinit var user: FirebaseAuth
    private lateinit var orphanages: MutableList<Orphanage>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMorePantiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.apply {
            elevation = 0f
            title = "Panti Lainnya"
            setDisplayHomeAsUpEnabled(true)
        }

        viewModel = ViewModelProvider(this).get(AboutViewModel::class.java)

        // utils init
        orphanages = mutableListOf()
        user = FirebaseAuth.getInstance()

        // init adapter
        orphanAdapter = ReusableAdapter(this)

        setupOrphanageAdapter(binding.rvPantiLain)

        initUI()

    }

    private fun initUI(){
        // init orphanage
        viewModel.fetchOrphanage()
        viewModel.orphanages.observe(this, Observer {
            orphanages = it.toMutableList()

            orphanAdapter.addData(it)

            // null data check
            if (it.isEmpty()){
            } else {
            }
        })

        viewModel.orphanageRealtimeUpdate()
        viewModel.orphanage.observe(this, Observer {

            // update orphanage
//            if (!orphanages.contains(it)) {
//                orphanages.add(it)
//            } else {
//                val index = orphanages.indexOf(it)
//                orphanages[index] = it
//            }

            // realtime
            //orphanageAdapter.addData(orphanages)

        })
    }

    private fun setupOrphanageAdapter(recyclerView: RecyclerView){
        orphanAdapter.adapterCallback(orphanageAdapterCallback)
            .setLayout(R.layout.item_panti_lain)
            .isVerticalView()
            .build(recyclerView)
    }

    private val orphanageAdapterCallback = object: AdapterCallback<Orphanage> {
        override fun initComponent(itemView: View, data: Orphanage, itemIndex: Int) {
            // set utils
            itemView.tv_nama_panti.text = data.nama
            itemView.tv_alamat_panti.text = data.alamat

            // set gambar orphanage
            Glide.with(this@MorePantiActivity)
                .load(data.gambar)
                .into(itemView.img_panti)
        }

        override fun onItemClicked(itemView: View, data: Orphanage, itemIndex: Int) {
            val intent = Intent(this@MorePantiActivity, DetailOrphangeActivity::class.java)
            intent.putExtra("detailOrphanage", data)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}