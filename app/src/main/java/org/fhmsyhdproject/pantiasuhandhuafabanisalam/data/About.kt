package org.fhmsyhdproject.pantiasuhandhuafabanisalam.data

import com.google.firebase.database.Exclude

data class About (
    @get:Exclude
    var id: String = "",
    var title: String = "",
    var content: String = ""
)