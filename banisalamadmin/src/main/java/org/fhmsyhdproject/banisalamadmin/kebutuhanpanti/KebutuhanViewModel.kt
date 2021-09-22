package org.fhmsyhdproject.banisalamadmin.kebutuhanpanti

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.fhmsyhdproject.banisalamadmin.data.kebutuhan.Kebutuhan
import org.fhmsyhdproject.banisalamadmin.data.kebutuhan.KebutuhanDao

class KebutuhanViewModel (private val db: KebutuhanDao) : ViewModel(){
    fun insertData(kebutuhan: Kebutuhan){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                db.insertData(kebutuhan)
            }
        }
    }
}

