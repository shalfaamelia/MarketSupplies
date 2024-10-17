package com.shalfa.marketsupplies.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.shalfa.marketsupplies.entity.KebutuhanEntity

@Dao
interface KebutuhanDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKebutuhan(kebutuhan: KebutuhanEntity)

    @Update
    suspend fun updateKebutuhan(kebutuhan: KebutuhanEntity)

    @Delete
    suspend fun deleteKebutuhan(kebutuhan: KebutuhanEntity)

    @Query("SELECT * FROM kebutuhan_table")
    fun getAllKebutuhan(): LiveData<List<KebutuhanEntity>>
}