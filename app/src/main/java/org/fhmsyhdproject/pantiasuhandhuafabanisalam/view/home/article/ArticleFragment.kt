package org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.home.article

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
import kotlinx.android.synthetic.main.item_article.view.*
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.R
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.data.Article
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.databinding.FragmentArticleBinding
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.utils.AdapterUtil
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.home.detail.DetailContentActivity


class ArticleFragment : Fragment() {

    private lateinit var binding: FragmentArticleBinding
    private lateinit var database: DatabaseReference
    lateinit var adapterArticle: AdapterUtil<Article>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = FirebaseDatabase.getInstance().reference

        val listArticle: MutableList<Article> = arrayListOf()

        database.child("article").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.childrenCount > 0) {
                    for (item: DataSnapshot in snapshot.children) {
                        item.getValue(Article::class.java)?.apply {
                            listArticle.add(this)
                        }
                    }
                    adapterArticle = AdapterUtil(
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
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Could not read from database", Toast.LENGTH_LONG).show()
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

}