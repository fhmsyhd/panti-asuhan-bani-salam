package org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.pray

import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.data.pray.DataKotaDB
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.data.pray.DataSholatDB

//class PrayAdapter: RecyclerView.Adapter<PrayAdapter.PrayViewHolder>(), Filterable {
//
//    // schedule list
//    private var scheduleList = mutableListOf<DataSholatDB>()
//
//    // current list untuk menampung data yang akan di filter
//    private var currentList = mutableListOf<DataKotaDB>()
//
//    inner class PrayViewHolder(
//        val itemSholatBinding: ItemSholatBinding
//    ) : RecyclerView.ViewHolder(itemSholatBinding.root)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrayAdapter.PrayViewHolder {
//        TODO("Not yet implemented")
//    }
//
//    override fun onBindViewHolder(holder: PrayAdapter.PrayViewHolder, position: Int) {
//        TODO("Not yet implemented")
//    }
//
//    override fun getItemCount() = currentList.size
//
//    override fun getFilter(): Filter {
//        TODO("Not yet implemented")
//    }
//}