package com.shalfa.marketsupplies.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.shalfa.marketsupplies.database.AppDatabase
import com.shalfa.marketsupplies.entity.MinumanEntity
import kotlinx.coroutines.launch

class DrinkViewModel(application: Application) : AndroidViewModel(application) {
    private val minumanDao = AppDatabase.getDatabase(application).minumanDao()
    val allDrink: LiveData<List<MinumanEntity>> = minumanDao.getAllMinuman()

    fun insertMinuman(minuman: MinumanEntity) = viewModelScope.launch {
        minumanDao.insertMinuman(minuman)
    }

    fun updateMinuman(minuman: MinumanEntity) = viewModelScope.launch {
        minumanDao.updateMinuman(minuman)
    }

    fun deleteMinuman(minuman: MinumanEntity) = viewModelScope.launch {
        minumanDao.deleteMinuman(minuman)
    }
}