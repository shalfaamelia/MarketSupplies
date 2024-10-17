package com.shalfa.marketsupplies.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "minuman_table")
data class MinumanEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nama: String,
    val berat: Int,
    val stok: Int
)

