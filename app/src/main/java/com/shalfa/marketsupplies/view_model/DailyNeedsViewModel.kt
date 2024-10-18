package com.shalfa.marketsupplies.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.shalfa.marketsupplies.database.AppDatabase
import com.shalfa.marketsupplies.entity.KebutuhanEntity
import kotlinx.coroutines.launch

class DailyNeedsViewModel(application: Application) : AndroidViewModel(application) {
    private val kebutuhanDao = AppDatabase.getDatabase(application).kebutuhanDao()
    val allDailyNeeds: LiveData<List<KebutuhanEntity>> = kebutuhanDao.getAllKebutuhan()

    fun insertKebutuhan(kebutuhan: KebutuhanEntity) = viewModelScope.launch {
        kebutuhanDao.insertKebutuhan(kebutuhan)
    }

    fun updateKebutuhan(kebutuhan: KebutuhanEntity) = viewModelScope.launch {
        kebutuhanDao.updateKebutuhan(kebutuhan)
    }

    fun deleteKebutuhan(kebutuhan: KebutuhanEntity) = viewModelScope.launch {
        kebutuhanDao.deleteKebutuhan(kebutuhan)
    }
}