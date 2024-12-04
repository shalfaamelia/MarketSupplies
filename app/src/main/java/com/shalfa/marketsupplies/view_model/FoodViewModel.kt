package com.shalfa.marketsupplies.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.shalfa.marketsupplies.database.AppDatabase
import com.shalfa.marketsupplies.entity.MakananEntity
import kotlinx.coroutines.launch

class FoodViewModel(application: Application) : AndroidViewModel(application) {
    private val makananDao = AppDatabase.getDatabase(application).makananDao()
    private val database = FirebaseDatabase.getInstance().getReference("makanan")
    val allFood = MutableLiveData<List<MakananEntity>>()

    fun insertMakanan(makanan: MakananEntity) = viewModelScope.launch {
        makananDao.insertMakanan(makanan)
    }

    fun updateMakanan(makanan: MakananEntity) = viewModelScope.launch {
        makananDao.updateMakanan(makanan)
    }

    fun deleteMakanan(makanan: MakananEntity) = viewModelScope.launch {
        makananDao.deleteMakanan(makanan)
    }

    fun insertToFirebase(makanan: MakananEntity) {
        database.child(makanan.id.toString()).setValue(makanan)
    }

    fun updateToFirebase(makanan: MakananEntity) {
        database.child(makanan.id.toString()).setValue(makanan)
    }

    fun deleteFromFirebase(id: Int) {
        database.child(id.toString()).removeValue()
    }

    fun fetchFromFirebase() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val makananList = mutableListOf<MakananEntity>()
                for (data in snapshot.children) {
                    val makanan = data.getValue(MakananEntity::class.java)
                    makanan?.let { makananList.add(it) }
                }
                allFood.postValue(makananList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Gagal mengambil data", error.toException())
            }
        })
    }
}