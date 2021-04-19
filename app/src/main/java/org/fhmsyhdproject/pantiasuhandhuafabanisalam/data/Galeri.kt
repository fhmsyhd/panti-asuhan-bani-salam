package org.fhmsyhdproject.pantiasuhandhuafabanisalam.data

import com.google.firebase.database.Exclude
import java.io.Serializable

class Galeri(
    @get:Exclude
    var id: String= "",
    var image: String = ""
): Serializable