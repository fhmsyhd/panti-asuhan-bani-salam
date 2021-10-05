package org.fhmsyhdproject.pantiasuhandhuafabanisalam.data.pray

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.utils.Constant.DATA_KOTA
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.utils.Constant.DATA_SHOLAT

@Entity(tableName = DATA_SHOLAT)
data class DataSholatDB constructor (
    @PrimaryKey
    val id: String,
    val lokasi: String,
    val daerah: String,
    val lat: String,
    val lon: String,
    val tanggal: String,
    val imsak: String,
    val subuh: String,
    val terbit: String,
    val dhuha: String,
    val dzuhur: String,
    val ashar: String,
    val maghrib: String,
    val isya: String
)

@Entity(tableName = DATA_KOTA)
data class DataKotaDB constructor (
    @PrimaryKey
    val id: String,
    val lokasi: String
)