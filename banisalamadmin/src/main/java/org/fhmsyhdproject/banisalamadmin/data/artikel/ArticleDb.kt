package org.fhmsyhdproject.banisalamadmin.data.artikel

import androidx.lifecycle.LiveData
import com.google.firebase.database.FirebaseDatabase

class ArticleDb private constructor(): ArticleDao {
    companion object{
        private const val PATH = "article"

        @Volatile
        private var INSTANCE: ArticleDb?=null
        fun getInstance(): ArticleDb {
            synchronized(this){
                var instance = INSTANCE
                if (instance == null) {
                    instance = ArticleDb()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
    private val database = FirebaseDatabase.getInstance().getReference(PATH)

    override fun insertData(article: Article){
        database.push().setValue(article)
    }
    override fun getData(): LiveData<List<Article>> {
        TODO("Not yet Implemented")
    }
}