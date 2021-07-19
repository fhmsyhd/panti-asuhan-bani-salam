package org.fhmsyhdproject.banisalamadmin.data

import androidx.lifecycle.LiveData
import com.google.firebase.database.FirebaseDatabase

class ActivityDb private constructor(): ActivityDao{

    companion object {
        private const val PATH = "activity"

        @Volatile
        private var INSTANCE: ActivityDb? = null
        fun getInstance(): ActivityDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = ActivityDb()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

    private val database = FirebaseDatabase.getInstance().getReference(PATH)
    
    override fun insertData(activity: Activity) {
        database.push().setValue(activity)
    }

    override fun getData(): LiveData<List<Activity>> {
        TODO("Not yet implemented")
    }
}