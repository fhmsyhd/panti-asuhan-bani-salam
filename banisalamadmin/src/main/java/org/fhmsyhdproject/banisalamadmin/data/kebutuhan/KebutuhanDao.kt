package org.fhmsyhdproject.banisalamadmin.data.kebutuhan

import androidx.lifecycle.LiveData

interface KebutuhanDao {

    fun insertData(kebutuhan: Kebutuhan)

    fun getData(): LiveData<List<Kebutuhan>>
}