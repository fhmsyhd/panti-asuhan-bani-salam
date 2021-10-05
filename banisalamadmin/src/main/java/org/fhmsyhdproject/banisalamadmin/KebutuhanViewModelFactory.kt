package org.fhmsyhdproject.banisalamadmin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.fhmsyhdproject.banisalamadmin.data.kebutuhan.NeedsDao

class KebutuhanViewModelFactory (private val dataSource: NeedsDao) :
    ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KebutuhanViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return KebutuhanViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unable to construct ViewModel")
    }
}