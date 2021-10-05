package org.fhmsyhdproject.pantiasuhandhuafabanisalam.data.pray

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DataKotaDao {
    @Query("SELECT * FROM data_kota")
    fun getDataKota(): LiveData<List<DataKotaDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(kota: List<DataKotaDB>)
}