package org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_article.view.*
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.R
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.data.Article
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.databinding.FragmentHomeBinding
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.utils.AdapterMaxUtil
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.home.activity.ActivityActivity
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.home.article.ArticleActivity
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.home.detail.DetailContentActivity

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var database: DatabaseReference
    lateinit var adapterArticle: AdapterMaxUtil<Article>
    lateinit var adapterActivity: AdapterMaxUtil<Article>

    private var sampleImages = intArrayOf(
        R.drawable.panti,
        R.drawable.pengajian,
        R.drawable.kumpul
    )

    private var caption = arrayOf(
        "Rumah Panti",
        "Mengaji Bersama Anak Yatim",
        "Anak-anak Panti Asuhan"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = FirebaseDatabase.getInstance().reference

        val listArticle: MutableList<Article> = arrayListOf()
        val listActivity: MutableList<Article> = arrayListOf()

        database.child("article").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.childrenCount > 0) {
                    for (item: DataSnapshot in snapshot.children) {
                        item.getValue(Article::class.java)?.apply {
                            listArticle.add(this)
                        }
                    }
                    adapterArticle = AdapterMaxUtil(
                            R.layout.item_article,
                            listArticle,
                            { position, itemView, item ->
                                itemView.tv_judul_artikel.text = item.title
                                itemView.tv_isi_artikel.text = item.content
                                Glide.with(requireContext()).load(item.image)
                                        .into(itemView.img_poster)
                            },
                            { position, item ->
                                val intent = Intent(requireContext(), DetailContentActivity::class.java)
                                intent.putExtra("detail", item)
                                startActivity(intent)
                            }
                    )
                    binding.rvArticle.layoutManager =
                            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                    binding.rvArticle.adapter = adapterArticle

                    shimmerFrameLayout.stopShimmer()
                    shimmerFrameLayout.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Could not read from database", Toast.LENGTH_LONG).show()
                shimmerFrameLayout.visibility = View.GONE
            }

        })

        database.child("activity").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.childrenCount > 0) {
                    for (item: DataSnapshot in snapshot.children) {
                        item.getValue(Article::class.java)?.apply {
                            listActivity.add(this)
                        }
                    }
                    adapterActivity = AdapterMaxUtil(
                        R.layout.item_article,
                        listActivity,
                        { position, itemView, item ->
                            itemView.tv_judul_artikel.text = item.title
                            itemView.tv_isi_artikel.text = item.content
                            Glide.with(requireContext()).load(item.image)
                                .into(itemView.img_poster)
                        },
                        { position, item ->
                            val intent = Intent(requireContext(), DetailContentActivity::class.java)
                            intent.putExtra("detail", item)
                            startActivity(intent)
                        }
                    )
                    binding.rvActivity.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                    binding.rvActivity.adapter = adapterActivity

                    binding.layoutTitleArticle.visibility = View.VISIBLE
                    shimmerFrameLayout2.stopShimmer()
                    shimmerFrameLayout2.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Could not read from database", Toast.LENGTH_LONG).show()
                shimmerFrameLayout2.visibility = View.GONE
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container,  false)

        binding.imageSliderHome.pageCount = caption.size
        with(binding.imageSliderHome){
            pageCount = caption.size

            setImageListener{ position, imageView ->
                imageView.setImageResource(sampleImages[position])
            }

            setImageClickListener{ position ->
                Toast.makeText(context, caption[position], Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvArtikelLainnya.setOnClickListener {
            val intent = Intent(requireContext(), ArticleActivity::class.java)
            startActivity(intent)
        }

        binding.tvKegiatanLainnya.setOnClickListener {
            val intent = Intent(requireContext(), ActivityActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        shimmerFrameLayout.startShimmer()
        shimmerFrameLayout2.startShimmer()
    }

    override fun onPause() {
        super.onPause()
        shimmerFrameLayout.stopShimmer()
        shimmerFrameLayout2.stopShimmer()
    }

}