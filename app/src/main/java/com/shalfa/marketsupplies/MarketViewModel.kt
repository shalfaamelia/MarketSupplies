package com.shalfa.marketsupplies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.shalfa.marketsupplies.entity.KebutuhanEntity
import com.shalfa.marketsupplies.entity.MakananEntity
import com.shalfa.marketsupplies.entity.MinumanEntity
import kotlinx.coroutines.launch

class MarketViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MarketRepository

    val allMakanan: LiveData<List<MakananEntity>>
    val allMinuman: LiveData<List<MinumanEntity>>
    val allKebutuhan: LiveData<List<KebutuhanEntity>>

    init {
        val makananDao = MarketDatabase.getDatabase(application).makananDao()
        val minumanDao = MarketDatabase.getDatabase(application).minumanDao()
        val kebutuhanDao = MarketDatabase.getDatabase(application).kebutuhanDao()
        repository = MarketRepository(makananDao, minumanDao, kebutuhanDao)

        allMakanan = repository.allMakanan
        allMinuman = repository.allMinuman
        allKebutuhan = repository.allKebutuhan
    }

    fun insertMakanan(makanan: MakananEntity) {
        viewModelScope.launch {
            repository.insertMakanan(makanan)
        }
    }

    fun updateMakanan(makanan: MakananEntity) {
        viewModelScope.launch {
            repository.updateMakanan(makanan)
        }
    }

    fun deleteMakanan(makanan: MakananEntity) {
        viewModelScope.launch {
            repository.deleteMakanan(makanan)
        }
    }

    fun insertMinuman(minuman: MinumanEntity) {
        viewModelScope.launch {
            repository.insertMinuman(minuman)
        }
    }

    fun updateMinuman(minuman: MinumanEntity) {
        viewModelScope.launch {
            repository.updateMinuman(minuman)
        }
    }

    fun deleteMinuman(minuman: MinumanEntity) {
        viewModelScope.launch {
            repository.deleteMinuman(minuman)
        }
    }

    fun insertKebutuhan(kebutuhan: KebutuhanEntity) {
        viewModelScope.launch {
            repository.insertKebutuhan(kebutuhan)
        }
    }

    fun updateKebutuhan(kebutuhan: KebutuhanEntity) {
        viewModelScope.launch {
            repository.updateKebutuhan(kebutuhan)
        }
    }

    fun deleteKebutuhan(kebutuhan: KebutuhanEntity) {
        viewModelScope.launch {
            repository.deleteKebutuhan(kebutuhan)
        }
    }
}