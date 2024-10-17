package com.shalfa.marketsupplies.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "kebutuhan_table")
data class KebutuhanEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nama: String,
    val berat: Int,
    val stok: Int
)
