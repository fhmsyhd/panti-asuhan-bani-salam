package org.fhmsyhdproject.banisalamadmin.data.kebutuhan

import androidx.lifecycle.LiveData
import com.google.firebase.database.FirebaseDatabase

class NeedsDb private constructor(): NeedsDao {
    companion object{
        private const val PATH = "needs"

        @Volatile
        private var INSTANCE: NeedsDb?=null
        fun getInstance():NeedsDb{
            synchronized(this){
                var instance = INSTANCE
                if (instance == null) {
                    instance = NeedsDb()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
    private val database = FirebaseDatabase.getInstance().getReference(PATH)

    override fun insertData(needs: Needs){
        database.push().setValue(needs)
    }
    override fun getData():LiveData<List<Needs>>{
        TODO("Not yet Implemented")
    }
}