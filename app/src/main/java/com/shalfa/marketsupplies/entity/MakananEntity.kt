package com.shalfa.marketsupplies.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "makanan_table")
data class MakananEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val namaMakanan: String = "",
    val beratMakanan: Int = 0,
    val jumlahStok: Int = 0
){
    constructor() : this(0, "", 0, 0)
}