package org.fhmsyhdproject.banisalamadmin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.fhmsyhdproject.banisalamadmin.data.ActivityDao

class ActivityViewModelFactory(private val dataSource: ActivityDao) :
    ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ActivityViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unable to construct ViewModel")
    }
}