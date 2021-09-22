package org.fhmsyhdproject.banisalamadmin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.fhmsyhdproject.banisalamadmin.data.Activity
import org.fhmsyhdproject.banisalamadmin.data.ActivityDao

class ActivityViewModel(private val db: ActivityDao) : ViewModel() {

    //val data = db.getData()

    fun insertData(activity: Activity) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insertData(activity)
            }
        }
    }
}