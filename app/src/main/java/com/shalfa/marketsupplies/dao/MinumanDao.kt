package com.shalfa.marketsupplies.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.shalfa.marketsupplies.entity.MinumanEntity

@Dao
interface MinumanDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMinuman(minuman: MinumanEntity)

    @Update
    suspend fun updateMinuman(minuman: MinumanEntity)

    @Delete
    suspend fun deleteMinuman(minuman: MinumanEntity)

    @Query("SELECT * FROM minuman_table")
    fun getAllMinuman(): LiveData<List<MinumanEntity>>
}