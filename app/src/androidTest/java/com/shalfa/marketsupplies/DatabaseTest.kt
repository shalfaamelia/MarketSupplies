package com.shalfa.marketsupplies

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.shalfa.marketsupplies.dao.MakananDao
import com.shalfa.marketsupplies.database.AppDatabase
import com.shalfa.marketsupplies.entity.MakananEntity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    private lateinit var makananDao: MakananDao
    private lateinit var db: AppDatabase

    private val mie = MakananEntity(1,"Mie",500,65)

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        makananDao =  db.makananDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() = db.close()

    @Test
    @Throws(Exception::class)
    fun insertAndRetrieveMakanan(){
        makananDao.insertMakanan(mie)
        val result = makananDao.getAll()
        assert(result.size == 1)
    }
}