package com.shalfa.marketsupplies.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "minuman_table")
data class MinumanEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val namaMinuman: String = "",
    val beratMinuman: Int = 0,
    val jumlahStok: Int = 0
){
    constructor() : this(0,"",0,0)
}

