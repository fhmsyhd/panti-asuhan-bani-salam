package org.fhmsyhdproject.pantiasuhandhuafabanisalam.data.pray

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DataSholatDao {
    @Query("SELECT * FROM data_sholat")
    fun getDataSholat(): LiveData<List<DataSholatDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(sholat: List<DataSholatDB>)
}