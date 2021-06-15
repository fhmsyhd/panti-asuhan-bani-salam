package org.fhmsyhdproject.pantiasuhandhuafabanisalam.data

import com.google.firebase.database.Exclude
import java.io.Serializable

data class Category (
    @get:Exclude
    var id: String? = null,
    var logoKategori: String? = null,
    var namaKategori: String? = null,
) : Serializable