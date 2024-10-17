package com.shalfa.marketsupplies

import androidx.lifecycle.LiveData

class FoodRepository(private val foodDao: FoodDao) {

    val allFoods: LiveData<List<FoodEntity>> = foodDao.getAllFoods()

    suspend fun insert(food: FoodEntity) {
        foodDao.insert(food)
    }
}