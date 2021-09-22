package org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.home.activity

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
import kotlinx.android.synthetic.main.item_article.view.*
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.R
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.data.Activity
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.data.Article
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.databinding.ActivityActivityBinding
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.utils.AdapterCallback
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.utils.AdapterUtil
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.utils.ReusableAdapter
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.home.HomeViewModel
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.home.detail.DetailContentActivity

class ActivityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityActivityBinding
    private lateinit var homeViewModel: HomeViewModel

    // adapter
    private lateinit var activityAdapter: ReusableAdapter<Activity>

    // utils
    private var rowIndex = -1
    private lateinit var user: FirebaseAuth
    private lateinit var activitys: MutableList<Activity>

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

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        // utils init
        activitys = mutableListOf()
        user = FirebaseAuth.getInstance()

        activityAdapter = ReusableAdapter(this)

        setupActivityAdapter(binding.rvActivity)

        initUI()

    }

    private fun initUI(){
        // init activity
        homeViewModel.fetchActivity()
        homeViewModel.activitys.observe(this, Observer {
            activitys = it.toMutableList()

            activityAdapter.addData(it)

            // null data check
            if (it.isEmpty()){
            } else {
            }
        })

        homeViewModel.activityRealtimeUpdate()
        homeViewModel.activity.observe(this, Observer {

            // update activity
//            if (!activitys.contains(it)) {
//                activitys.add(it)
//            } else {
//                val index = activitys.indexOf(it)
//                activitys[index] = it
//            }

            // realtime
            //activityAdapter.addData(activitys)

        })

    }

    private fun setupActivityAdapter(recyclerView: RecyclerView){
        activityAdapter.adapterCallback(activityAdapterCallback)
            .setLayout(R.layout.item_article)
            .isVerticalView()
            .build(recyclerView)
    }

    private val activityAdapterCallback = object: AdapterCallback<Activity> {
        override fun initComponent(itemView: View, data: Activity, itemIndex: Int) {
            // set utils
            itemView.tv_judul_artikel.text = data.title
            itemView.tv_isi_artikel.text = data.content

            // set gambar activity
            Glide.with(this@ActivityActivity)
                .load(data.image)
                .into(itemView.img_poster)
        }

        override fun onItemClicked(itemView: View, data: Activity, itemIndex: Int) {
            val intent = Intent(this@ActivityActivity, DetailContentActivity::class.java)
            intent.putExtra("detailActivity", data)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}