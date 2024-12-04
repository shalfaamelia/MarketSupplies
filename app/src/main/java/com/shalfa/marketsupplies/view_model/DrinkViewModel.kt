package com.shalfa.marketsupplies.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.shalfa.marketsupplies.database.AppDatabase
import com.shalfa.marketsupplies.entity.MinumanEntity
import kotlinx.coroutines.launch

class DrinkViewModel(application: Application) : AndroidViewModel(application) {
    private val minumanDao = AppDatabase.getDatabase(application).minumanDao()
    private val database = FirebaseDatabase.getInstance().getReference("minuman")
    val allDrink = MutableLiveData<List<MinumanEntity>>()

    fun insertMinuman(minuman: MinumanEntity) = viewModelScope.launch {
        minumanDao.insertMinuman(minuman)
    }

    fun updateMinuman(minuman: MinumanEntity) = viewModelScope.launch {
        minumanDao.updateMinuman(minuman)
    }

    fun deleteMinuman(minuman: MinumanEntity) = viewModelScope.launch {
        minumanDao.deleteMinuman(minuman)
    }

    fun insertToFirebase(minuman: MinumanEntity) {
        database.child(minuman.id.toString()).setValue(minuman)
    }

    fun updateToFirebase(minuman: MinumanEntity) {
        database.child(minuman.id.toString()).setValue(minuman)
    }

    fun deleteFromFirebase(id: Int) {
        database.child(id.toString()).removeValue()
    }

    fun fetchFromFirebase() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val minumanList = mutableListOf<MinumanEntity>()
                for (data in snapshot.children) {
                    val minuman = data.getValue(MinumanEntity::class.java)
                    minuman?.let { minumanList.add(it) }
                }
                allDrink.postValue(minumanList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Gagal mengambil data", error.toException())
            }
        })
    }
}