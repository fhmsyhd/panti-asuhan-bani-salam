package org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.donation.needs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.android.synthetic.main.item_article.*
import kotlinx.android.synthetic.main.item_article.view.*
import kotlinx.android.synthetic.main.item_panti_lain.view.*
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.R
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.data.Article
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.data.Needs
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.data.Orphanage
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.databinding.ActivityNeedsBinding
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.utils.AdapterCallback
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.utils.AdapterUtil
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.utils.ReusableAdapter
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.about.pantilain.DetailOrphangeActivity
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.donation.DonationViewModel
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.home.detail.DetailContentActivity

class NeedsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNeedsBinding
    private lateinit var database: DatabaseReference
    lateinit var adapterNeeds: AdapterUtil<Needs>

    private lateinit var viewModel: DonationViewModel

    // adapter
    private lateinit var needsAdapter: ReusableAdapter<Needs>

    // utils
    private lateinit var user: FirebaseAuth
    private lateinit var needies: MutableList<Needs>

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

        viewModel = ViewModelProvider(this).get(DonationViewModel::class.java)

        // utils init
        needies = mutableListOf()
        user = FirebaseAuth.getInstance()

        // init adapter
        needsAdapter = ReusableAdapter(this)

        setupNeedsAdapter(binding.rvNeeds)

        initUI()

//        database = FirebaseDatabase.getInstance().reference
//
//        val listNeeds: MutableList<Needs> = arrayListOf()
//
//        database.child("needs").addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot.childrenCount > 0) {
//                    for (item: DataSnapshot in snapshot.children) {
//                        item.getValue(Needs::class.java)?.apply {
//                            listNeeds.add(this)
//                        }
//                    }
//                    adapterNeeds = AdapterUtil(
//                        R.layout.item_article,
//                        listNeeds,
//                        { position, itemView, item ->
//                            itemView.tv_judul_artikel.text = item.title
//                            itemView.tv_isi_artikel.text = item.content
//                            Glide.with(this@NeedsActivity).load(item.image)
//                                .into(itemView.img_poster)
//                        },
//                        { position, item ->
//                            val intent = Intent(this@NeedsActivity, DetailContentActivity::class.java)
//                            intent.putExtra("detail", item)
//                            startActivity(intent)
//                        }
//                    )
//                    binding.rvNeeds.layoutManager =
//                        LinearLayoutManager(this@NeedsActivity, LinearLayoutManager.VERTICAL, false)
//                    binding.rvNeeds.adapter = adapterNeeds
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(this@NeedsActivity, "Could not read from database", Toast.LENGTH_LONG).show()
//            }
//
//        })

    }

    private fun initUI(){
        // init needs
        viewModel.fetchNeeds()
        viewModel.needies.observe(this, Observer {
            needies = it.toMutableList()

            needsAdapter.addData(it)

            // null data check
            if (it.isEmpty()){
            } else {
            }
        })

        viewModel.needsRealtimeUpdate()
        viewModel.needs.observe(this, Observer {

            // update needs
//            if (!needss.contains(it)) {
//                needss.add(it)
//            } else {
//                val index = needss.indexOf(it)
//                needss[index] = it
//            }

            // realtime
            //needsAdapter.addData(needss)

        })
    }

    private fun setupNeedsAdapter(recyclerView: RecyclerView){
        needsAdapter.adapterCallback(needsAdapterCallback)
            .setLayout(R.layout.item_article)
            .isVerticalView()
            .build(recyclerView)
    }

    private val needsAdapterCallback = object: AdapterCallback<Needs> {
        override fun initComponent(itemView: View, data: Needs, itemIndex: Int) {
            // set utils
            itemView.tv_judul_artikel.text = data.title
            itemView.tv_isi_artikel.text = data.content

            // set gambar activity
            Glide.with(this@NeedsActivity)
                .load(data.image)
                .into(itemView.img_poster)
        }

        override fun onItemClicked(itemView: View, data: Needs, itemIndex: Int) {
            val intent = Intent(this@NeedsActivity, DetailContentActivity::class.java)
            intent.putExtra("detailNeeds", data)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}