package org.fhmsyhdproject.pantiasuhandhuafabanisalam.data

import android.os.Parcelable
import com.google.firebase.database.Exclude
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Orphanage (
    var id: String? = "",
    var nama: String? = "",
    var nomor: String? = "",
    var nomorwa: String? = "",
    var pemilik: String? = "",
    var alamat: String? = "",
    var gambar: String? = "",
    var logo: String? = "",
    var maps: String? = "",
    @get:Exclude
    var timestamp: Long? = System.currentTimeMillis()
) : Parcelable