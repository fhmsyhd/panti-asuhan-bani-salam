package org.fhmsyhdproject.pantiasuhandhuafabanisalam.data

import com.google.firebase.database.Exclude
import java.io.Serializable

data class Pengurus (
        @get:Exclude
        var id: String = "",
        var image: String = "",
        var name: String = "",
        var jabatan : String = ""
) : Serializable