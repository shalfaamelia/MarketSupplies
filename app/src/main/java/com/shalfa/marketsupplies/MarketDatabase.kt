package com.shalfa.marketsupplies

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shalfa.marketsupplies.dao.KebutuhanDao
import com.shalfa.marketsupplies.dao.MakananDao
import com.shalfa.marketsupplies.dao.MinumanDao
import com.shalfa.marketsupplies.entity.KebutuhanEntity
import com.shalfa.marketsupplies.entity.MakananEntity
import com.shalfa.marketsupplies.entity.MinumanEntity

@Database(entities = [MakananEntity::class, MinumanEntity::class, KebutuhanEntity::class], version = 1, exportSchema = false)
abstract class MarketDatabase : RoomDatabase() {
    abstract fun makananDao(): MakananDao
    abstract fun minumanDao(): MinumanDao
    abstract fun kebutuhanDao(): KebutuhanDao

    companion object {
        @Volatile
        private var INSTANCE: MarketDatabase? = null

        fun getDatabase(context: Context): MarketDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MarketDatabase::class.java,
                    "market_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}