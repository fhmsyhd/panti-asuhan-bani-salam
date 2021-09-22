package org.fhmsyhdproject.banisalamadmin.data.kebutuhan

import androidx.lifecycle.LiveData
import com.google.firebase.database.FirebaseDatabase

class KebutuhanDb private constructor(): KebutuhanDao {
    companion object{
        private const val PATH = "kebutuhan"

        @Volatile
        private var INSTANCE: KebutuhanDb?=null
        fun getInstance():KebutuhanDb{
            synchronized(this){
                var instance = INSTANCE
                if (instance == null) {
                    instance = KebutuhanDb()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
    private val database = FirebaseDatabase.getInstance().getReference(PATH)

    override fun insertData(kebutuhan: Kebutuhan){
        database.push().setValue(kebutuhan)
    }
    override fun getData():LiveData<List<Kebutuhan>>{
        TODO("Not yet Implemented")
    }
}