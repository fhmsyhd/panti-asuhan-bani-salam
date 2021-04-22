package org.fhmsyhdproject.pantiasuhandhuafabanisalam.data

import com.google.firebase.database.Exclude
import java.io.Serializable

data class Anak (
        @get:Exclude
        var id: String = "",
        var image: String = "",
        var name: String = "",
        var tglahir : String = "",
        var umur : String = " ",
        var asal : String = " "
) : Serializable