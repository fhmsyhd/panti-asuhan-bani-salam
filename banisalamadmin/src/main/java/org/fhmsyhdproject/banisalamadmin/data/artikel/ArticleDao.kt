package org.fhmsyhdproject.banisalamadmin.data.artikel

import androidx.lifecycle.LiveData
import org.fhmsyhdproject.banisalamadmin.data.kebutuhan.Needs

interface ArticleDao {

    fun insertData(needs: Article)

    fun getData(): LiveData<List<Article>>
}