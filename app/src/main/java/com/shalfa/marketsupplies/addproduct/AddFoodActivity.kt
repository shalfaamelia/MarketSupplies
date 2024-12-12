package com.shalfa.marketsupplies.addproduct

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.shalfa.marketsupplies.databinding.ActivityAddFoodBinding
import com.shalfa.marketsupplies.entity.MakananEntity
import com.shalfa.marketsupplies.view_model.FoodViewModel

class AddFoodActivity : AppCompatActivity() {
    private val viewModel: FoodViewModel by viewModels()
    private var foodId: Int? = null
    private lateinit var binding: ActivityAddFoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        foodId = intent.getIntExtra("food_id", -1)
        val foodName = intent.getStringExtra("food_name")
        val foodWeight = intent.getIntExtra("food_weight", 0)
        val foodStock = intent.getIntExtra("food_stock", 0)

        if (foodId != -1) {
            binding.etNamaMakanan.setText(foodName)
            binding.etBeratMakanan.setText(foodWeight.toString())
            binding.etJumlahStokMakanan.setText(foodStock.toString())
        }

        binding.btnSimpan.setOnClickListener {
            val namaMakanan = binding.etNamaMakanan.text.toString()
            val beratMakanan = binding.etBeratMakanan.text.toString().toIntOrNull()
            val jumlahStok = binding.etJumlahStokMakanan.text.toString().toIntOrNull()

            if (namaMakanan.isNotBlank() && beratMakanan != null && jumlahStok != null) {
                if (foodId != -1) {
                    val updatedMakanan = MakananEntity(
                        id = foodId!!,
                        namaMakanan = namaMakanan,
                        beratMakanan = beratMakanan,
                        jumlahStok = jumlahStok
                    )
                    viewModel.updateMakanan(updatedMakanan)
                    viewModel.updateToFirebase(updatedMakanan)
                    Toast.makeText(this, "Data Makanan Diperbarui", Toast.LENGTH_SHORT).show()
                } else {
                    val newId = (System.currentTimeMillis() / 1000).toInt()
                    val newMakanan = MakananEntity(
                        id = newId,
                        namaMakanan = namaMakanan,
                        beratMakanan = beratMakanan,
                        jumlahStok = jumlahStok
                    )
                    viewModel.insertMakanan(newMakanan)
                    viewModel.insertToFirebase(newMakanan)
                    Toast.makeText(this, "Data Makanan Ditambahkan", Toast.LENGTH_SHORT).show()
                }
                finish()
            } else {
                Toast.makeText(this, "Silakan lengkapi semua field", Toast.LENGTH_SHORT).show()
            }
        }
    }
}