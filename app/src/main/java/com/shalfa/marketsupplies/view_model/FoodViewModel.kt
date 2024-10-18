package com.shalfa.marketsupplies.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.shalfa.marketsupplies.database.AppDatabase
import com.shalfa.marketsupplies.entity.MakananEntity
import kotlinx.coroutines.launch

class FoodViewModel(application: Application) : AndroidViewModel(application) {
    private val makananDao = AppDatabase.getDatabase(application).makananDao()
    val allFood: LiveData<List<MakananEntity>> = makananDao.getAllMakanan()

    fun insertMakanan(makanan: MakananEntity) = viewModelScope.launch {
        makananDao.insertMakanan(makanan)
    }

    fun updateMakanan(makanan: MakananEntity) = viewModelScope.launch {
        makananDao.updateMakanan(makanan)
    }

    fun deleteMakanan(makanan: MakananEntity) = viewModelScope.launch {
        makananDao.deleteMakanan(makanan)
    }
}