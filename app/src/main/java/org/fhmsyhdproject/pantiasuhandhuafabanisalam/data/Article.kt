package org.fhmsyhdproject.pantiasuhandhuafabanisalam.data

import com.google.firebase.database.Exclude
import java.io.Serializable

data class Article (
    @get:Exclude
    var id: String = "",
    var title: String = "",
    var content: String = "",
    var image: String = "",
    var date: String = "",
    var source: String = ""
) : Serializable