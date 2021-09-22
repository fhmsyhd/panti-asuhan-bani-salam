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
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.item_article.view.*
import kotlinx.android.synthetic.main.item_norek.view.*
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.R
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.data.Article
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.data.Bank
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.databinding.FragmentTransferBankBinding
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.utils.AdapterCallback
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.utils.AdapterUtil
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.utils.ReusableAdapter
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.about.AboutViewModel
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.home.detail.DetailContentActivity


class TransferBankFragment : Fragment() {

    private lateinit var binding: FragmentTransferBankBinding
    private lateinit var viewModel: DonationViewModel

    // adapter
    private lateinit var bankAdapter: ReusableAdapter<Bank>

    // utils
    private lateinit var user: FirebaseAuth
    private lateinit var banks: MutableList<Bank>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTransferBankBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(DonationViewModel::class.java)

        // utils init
        banks = mutableListOf()
        user = FirebaseAuth.getInstance()

        // init adapter
        bankAdapter = ReusableAdapter(requireContext())

        setupBankAdapter(binding.rvBank)

        initUI()

        return binding.root
    }

    private fun initUI(){
        // init bank
        viewModel.fetchBank()
        viewModel.banks.observe(viewLifecycleOwner, Observer {
            banks = it.toMutableList()

            bankAdapter.addData(it)

            // null data check
            if (it.isEmpty()){
            } else {
            }
        })

        viewModel.bankRealtimeUpdate()
        viewModel.bank.observe(viewLifecycleOwner, Observer {

            // update bank
//            if (!banks.contains(it)) {
//                banks.add(it)
//            } else {
//                val index = banks.indexOf(it)
//                banks[index] = it
//            }

            // realtime
            //bankAdapter.addData(banks)

        })
    }

    private fun setupBankAdapter(recyclerView: RecyclerView){
        bankAdapter.adapterCallback(bankAdapterCallback)
            .setLayout(R.layout.item_norek)
            .isVerticalView()
            .build(recyclerView)
    }

    private val bankAdapterCallback = object: AdapterCallback<Bank> {
        override fun initComponent(itemView: View, data: Bank, itemIndex: Int) {
            // set utils
            itemView.tv_no_rek.text = data.number
            itemView.tv_nama_rek.text = data.name
            itemView.btn_copy_rek.setOnClickListener {
                copyText(data.number!!)
            }

            // set gambar activity
            Glide.with(requireContext())
                .load(data.image)
                .into(itemView.img_bank)
        }

        override fun onItemClicked(itemView: View, data: Bank, itemIndex: Int) {
//            val intent = Intent(requireContext(), DetailContentActivity::class.java)
//            intent.putExtra("detailbank", data)
//            startActivity(intent)
        }
    }

    fun copyText(text:String){
        val myClipboard = context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val myClip: ClipData = ClipData.newPlainText("Label", text)
        myClipboard.setPrimaryClip(myClip)
        Toast.makeText(requireContext(), "Number copied : " + text, Toast.LENGTH_SHORT).show()
    }

}

fun copyTextTest(text:String): String{
    return text
}