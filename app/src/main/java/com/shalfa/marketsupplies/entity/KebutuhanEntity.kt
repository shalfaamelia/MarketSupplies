package com.shalfa.marketsupplies.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kebutuhan_table")
data class KebutuhanEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val namaKebutuhan: String = "",
    val beratKebutuhan: Int = 0,
    val jumlahStok: Int = 0
){
    constructor() : this(0,"",0,0)
}