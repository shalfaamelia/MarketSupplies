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
import com.shalfa.marketsupplies.entity.KebutuhanEntity
import kotlinx.coroutines.launch

class DailyNeedsViewModel(application: Application) : AndroidViewModel(application) {
    private val kebutuhanDao = AppDatabase.getDatabase(application).kebutuhanDao()
    private val database = FirebaseDatabase.getInstance().getReference("kebutuhan")
    val allDailyNeeds = MutableLiveData<List<KebutuhanEntity>>()

    fun insertKebutuhan(kebutuhan: KebutuhanEntity) = viewModelScope.launch {
        kebutuhanDao.insertKebutuhan(kebutuhan)
    }

    fun updateKebutuhan(kebutuhan: KebutuhanEntity) = viewModelScope.launch {
        kebutuhanDao.updateKebutuhan(kebutuhan)
    }

    fun deleteKebutuhan(kebutuhan: KebutuhanEntity) = viewModelScope.launch {
        kebutuhanDao.deleteKebutuhan(kebutuhan)
    }

    fun insertToFirebase(kebutuhan: KebutuhanEntity) {
        database.child(kebutuhan.id.toString()).setValue(kebutuhan)
    }

    fun updateToFirebase(kebutuhan: KebutuhanEntity) {
        database.child(kebutuhan.id.toString()).setValue(kebutuhan)
    }

    fun deleteFromFirebase(id: Int) {
        database.child(id.toString()).removeValue()
    }

    fun fetchFromFirebase() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val kebutuhanList = mutableListOf<KebutuhanEntity>()
                for (data in snapshot.children) {
                    val kebutuhan = data.getValue(KebutuhanEntity::class.java)
                    kebutuhan?.let { kebutuhanList.add(it) }
                }
                allDailyNeeds.postValue(kebutuhanList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Gagal mengambil data", error.toException())
            }
        })
    }
}