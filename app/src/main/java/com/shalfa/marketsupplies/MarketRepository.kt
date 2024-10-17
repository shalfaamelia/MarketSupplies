package com.shalfa.marketsupplies

import androidx.lifecycle.LiveData
import com.shalfa.marketsupplies.dao.KebutuhanDao
import com.shalfa.marketsupplies.dao.MakananDao
import com.shalfa.marketsupplies.dao.MinumanDao
import com.shalfa.marketsupplies.entity.KebutuhanEntity
import com.shalfa.marketsupplies.entity.MakananEntity
import com.shalfa.marketsupplies.entity.MinumanEntity

class MarketRepository(
    private val makananDao: MakananDao,
    private val minumanDao: MinumanDao,
    private val kebutuhanDao: KebutuhanDao
) {
    val allMakanan: LiveData<List<MakananEntity>> = makananDao.getAllMakanan()
    val allMinuman: LiveData<List<MinumanEntity>> = minumanDao.getAllMinuman()
    val allKebutuhan: LiveData<List<KebutuhanEntity>> = kebutuhanDao.getAllKebutuhan()

    suspend fun insertMakanan(makanan: MakananEntity) {
        makananDao.insertMakanan(makanan)
    }

    suspend fun updateMakanan(makanan: MakananEntity) {
        makananDao.updateMakanan(makanan)
    }

    suspend fun deleteMakanan(makanan: MakananEntity) {
        makananDao.deleteMakanan(makanan)
    }

    suspend fun insertMinuman(minuman: MinumanEntity) {
        minumanDao.insertMinuman(minuman)
    }

    suspend fun updateMinuman(minuman: MinumanEntity) {
        minumanDao.updateMinuman(minuman)
    }

    suspend fun deleteMinuman(minuman: MinumanEntity) {
        minumanDao.deleteMinuman(minuman)
    }

    suspend fun insertKebutuhan(kebutuhan: KebutuhanEntity) {
        kebutuhanDao.insertKebutuhan(kebutuhan)
    }

    suspend fun updateKebutuhan(kebutuhan: KebutuhanEntity) {
        kebutuhanDao.updateKebutuhan(kebutuhan)
    }

    suspend fun deleteKebutuhan(kebutuhan: KebutuhanEntity) {
        kebutuhanDao.deleteKebutuhan(kebutuhan)
    }
}