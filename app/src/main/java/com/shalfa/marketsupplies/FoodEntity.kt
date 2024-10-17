package com.shalfa.marketsupplies

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_table")
data class FoodEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val namaMakanan: String,
    val beratMakanan: Int,
    val jumlahStokMakanan: Int
)