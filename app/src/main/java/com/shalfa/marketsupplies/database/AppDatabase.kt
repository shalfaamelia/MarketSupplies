package com.shalfa.marketsupplies.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shalfa.marketsupplies.dao.MakananDao
import com.shalfa.marketsupplies.entity.MakananEntity

@Database(
    entities = [MakananEntity::class], version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    // Definisikan DAO untuk masing-masing entitas
    abstract fun makananDao(): MakananDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"  // Ubah nama database menjadi lebih umum
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}