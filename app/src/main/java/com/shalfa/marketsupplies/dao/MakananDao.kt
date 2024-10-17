package com.shalfa.marketsupplies.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.shalfa.marketsupplies.entity.MakananEntity

@Dao
interface MakananDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMakanan(makanan: MakananEntity)

    @Update
    suspend fun updateMakanan(makanan: MakananEntity)

    @Delete
    suspend fun deleteMakanan(makanan: MakananEntity)

    @Query("SELECT * FROM makanan_table")
    fun getAllMakanan(): LiveData<List<MakananEntity>>
}