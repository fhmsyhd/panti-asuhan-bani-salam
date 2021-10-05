package org.fhmsyhdproject.banisalamadmin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.fhmsyhdproject.banisalamadmin.data.kebutuhan.Needs
import org.fhmsyhdproject.banisalamadmin.data.kebutuhan.NeedsDao

class KebutuhanViewModel (private val db: NeedsDao) : ViewModel(){
    fun insertData(needs: Needs){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                db.insertData(needs)
            }
        }
    }
}

