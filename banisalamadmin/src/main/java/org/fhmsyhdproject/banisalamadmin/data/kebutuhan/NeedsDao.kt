package org.fhmsyhdproject.banisalamadmin.data.kebutuhan

import androidx.lifecycle.LiveData

interface NeedsDao {

    fun insertData(needs: Needs)

    fun getData(): LiveData<List<Needs>>
}