package org.fhmsyhdproject.banisalamadmin.viewArtikel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.fhmsyhdproject.banisalamadmin.data.artikel.ArticleDao

class ArtikelViewModelFactory (private val dataSource: ArticleDao) :
    ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArtikelViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ArtikelViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unable to construct ViewModel")
    }
}