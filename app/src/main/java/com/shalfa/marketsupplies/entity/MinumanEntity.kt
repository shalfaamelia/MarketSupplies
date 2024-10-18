package com.shalfa.marketsupplies.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "minuman_table")
data class MinumanEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val namaMinuman: String,
    val beratMinuman: Int,
    val jumlahStok: Int
)

