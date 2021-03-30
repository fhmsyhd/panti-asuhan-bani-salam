package org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.donation

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.item_article.view.*
import kotlinx.android.synthetic.main.item_norek.view.*
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.R
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.data.Article
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.data.Bank
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.databinding.FragmentTransferBankBinding
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.utils.AdapterUtil
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.home.detail.DetailContentActivity


class TransferBankFragment : Fragment() {

    private lateinit var binding: FragmentTransferBankBinding
    private lateinit var database: DatabaseReference
    lateinit var adapterBank: AdapterUtil<Bank>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = FirebaseDatabase.getInstance().reference

        val listBank: MutableList<Bank> = arrayListOf()

        database.child("bank").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.childrenCount > 0) {
                    for (item: DataSnapshot in snapshot.children) {
                        item.getValue(Bank::class.java)?.apply {
                            listBank.add(this)
                        }
                    }
                    adapterBank = AdapterUtil(
                        R.layout.item_norek,
                        listBank,
                        { position, itemView, item ->
                            itemView.tv_no_rek.text = item.number
                            itemView.tv_nama_rek.text = item.name
                            itemView.btn_copy_rek.setOnClickListener {
                                copyText(item.number)
                            }
                            Glide.with(requireContext()).load(item.image)
                                .into(itemView.img_bank)
                        },
                        { position, item ->
                            Log.d("bank", "onClick")
                        }
                    )
                    binding.rvBank.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                    binding.rvBank.adapter = adapterBank
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
        binding = FragmentTransferBankBinding.inflate(inflater, container, false)

        return binding.root
    }

    fun copyText(text:String){
        val myClipboard = context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val myClip: ClipData = ClipData.newPlainText("Label", text)
        myClipboard.setPrimaryClip(myClip)
        Toast.makeText(requireContext(), "Text copied : " + text, Toast.LENGTH_SHORT).show()
    }

}