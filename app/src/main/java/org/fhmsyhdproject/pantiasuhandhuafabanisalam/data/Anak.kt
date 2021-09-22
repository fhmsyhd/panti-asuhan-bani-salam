package org.fhmsyhdproject.pantiasuhandhuafabanisalam.data

import com.google.firebase.database.Exclude
import java.io.Serializable

data class Anak (
        @get:Exclude
        var id: String = "",
        var image: String = "",
        var nomor: String = "",
        var name: String = "",
        var kelas : String = "",
        var status : String = " ",
        var kategori : String = ""
) : Serializable