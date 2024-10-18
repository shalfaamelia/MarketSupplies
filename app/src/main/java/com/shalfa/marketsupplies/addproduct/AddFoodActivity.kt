package com.shalfa.marketsupplies.addproduct

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.shalfa.marketsupplies.R
import com.shalfa.marketsupplies.entity.MakananEntity
import com.shalfa.marketsupplies.view_model.FoodViewModel

class AddFoodActivity : AppCompatActivity() {

    private val viewModel: FoodViewModel by viewModels()
    private var foodId: Int? = null  // Untuk menyimpan ID makanan (jika dalam mode edit)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food)

        val etNamaMakanan = findViewById<EditText>(R.id.etNamaMakanan)
        val etBeratMakanan = findViewById<EditText>(R.id.etBeratMakanan)
        val etJumlahStokMakanan = findViewById<EditText>(R.id.etJumlahStokMakanan)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)

        // Cek apakah intent membawa data (untuk mode edit)
        val intent = intent
        foodId = intent.getIntExtra("food_id", -1)
        val foodName = intent.getStringExtra("food_name")
        val foodWeight = intent.getIntExtra("food_weight", 0)
        val foodStock = intent.getIntExtra("food_stock", 0)

        // Jika foodId valid, berarti kita dalam mode edit
        if (foodId != -1) {
            etNamaMakanan.setText(foodName)
            etBeratMakanan.setText(foodWeight.toString())
            etJumlahStokMakanan.setText(foodStock.toString())
        }

        btnSimpan.setOnClickListener {
            val namaMakanan = etNamaMakanan.text.toString()
            val beratMakanan = etBeratMakanan.text.toString().toIntOrNull()
            val jumlahStok = etJumlahStokMakanan.text.toString().toIntOrNull()

            if (namaMakanan.isNotBlank() && beratMakanan != null && jumlahStok != null) {
                if (foodId != -1) {
                    // Mode edit: perbarui data makanan yang ada
                    val updatedMakanan = MakananEntity(
                        id = foodId!!,
                        namaMakanan = namaMakanan,
                        beratMakanan = beratMakanan,
                        jumlahStok = jumlahStok
                    )
                    viewModel.updateMakanan(updatedMakanan)
                    Toast.makeText(this, "Data Makanan Diperbarui", Toast.LENGTH_SHORT).show()
                } else {
                    // Mode tambah: tambah data makanan baru
                    val newMakanan = MakananEntity(
                        namaMakanan = namaMakanan,
                        beratMakanan = beratMakanan,
                        jumlahStok = jumlahStok
                    )
                    viewModel.insertMakanan(newMakanan)
                    Toast.makeText(this, "Data Makanan Ditambahkan", Toast.LENGTH_SHORT).show()
                }
                finish() // Kembali ke FoodActivity
            } else {
                Toast.makeText(this, "Silakan lengkapi semua field", Toast.LENGTH_SHORT).show()
            }
        }
    }
}