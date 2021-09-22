package org.fhmsyhdproject.banisalamadmin.data

import androidx.lifecycle.LiveData

interface ActivityDao {
    fun insertData(activity: Activity)

    fun getData(): LiveData<List<Activity>>
}