package org.fhmsyhdproject.pantiasuhandhuafabanisalam.data

import android.os.Parcelable
import com.google.firebase.database.Exclude
import kotlinx.android.parcel.Parcelize

@Parcelize
data class JumlahAnak(
    var totalAnak: Int? = null,
    var totalYatim: Int? = null,
    var totalYatimPiatu: Int? = null,
    var totalDhuafa: Int? = null,
    var totalSD: Int? = null,
    var totalSMP: Int? = null,
    var totalSMA: Int? = null,
    @get:Exclude
    var timestamp: Long? = System.currentTimeMillis()
) : Parcelable