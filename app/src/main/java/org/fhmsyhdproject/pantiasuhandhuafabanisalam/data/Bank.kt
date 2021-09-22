package org.fhmsyhdproject.pantiasuhandhuafabanisalam.data

import android.os.Parcelable
import com.google.firebase.database.Exclude
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Bank (
    var id: String? = "",
    var name: String? = "",
    var number: String? = "",
    var image: String? = "",
    @get:Exclude
    var timestamp: Long? = System.currentTimeMillis()
) : Parcelable