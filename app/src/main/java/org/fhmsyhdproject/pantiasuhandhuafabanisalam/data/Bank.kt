package org.fhmsyhdproject.pantiasuhandhuafabanisalam.data

import com.google.firebase.database.Exclude
import java.io.Serializable

data class Bank(
    @get:Exclude
    var id: String = "",
    var image: String = "",
    var name: String = "",
    var number: String = ""
) : Serializable