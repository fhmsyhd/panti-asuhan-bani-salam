package org.fhmsyhdproject.banisalamadmin.viewArtikel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.fhmsyhdproject.banisalamadmin.data.artikel.Article
import org.fhmsyhdproject.banisalamadmin.data.artikel.ArticleDao

class ArtikelViewModel (private val db: ArticleDao) : ViewModel(){
    fun insertData(article: Article){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                db.insertData(article)
            }
        }
    }
}