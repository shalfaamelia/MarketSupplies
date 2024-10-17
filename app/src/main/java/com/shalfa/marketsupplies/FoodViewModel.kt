package com.shalfa.marketsupplies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FoodViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: FoodRepository
    val allFoods: LiveData<List<FoodEntity>>

    init {
        val foodDao = FoodDatabase.getDatabase(application).foodDao()
        repository = FoodRepository(foodDao)
        allFoods = repository.allFoods
    }

    fun insert(food: FoodEntity) = viewModelScope.launch {
        repository.insert(food)
    }
}