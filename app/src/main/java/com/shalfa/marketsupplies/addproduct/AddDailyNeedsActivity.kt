package com.shalfa.marketsupplies.addproduct

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.shalfa.marketsupplies.R
import com.shalfa.marketsupplies.entity.KebutuhanEntity
import com.shalfa.marketsupplies.view_model.DailyNeedsViewModel


class AddDailyNeedsActivity : AppCompatActivity() {

    private val viewModel: DailyNeedsViewModel by viewModels()
    private var dailyNeedsId: Int? = null  // Untuk menyimpan ID makanan (jika dalam mode edit)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_dailyneeds)

        val etNamaKebutuhan = findViewById<EditText>(R.id.etNamaKebutuhan)
        val etBeratKebutuhan = findViewById<EditText>(R.id.etBeratKebutuhan)
        val etJumlahStokKebutuhan = findViewById<EditText>(R.id.etJumlahStokKebutuhan)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)

        // Cek apakah intent membawa data (untuk mode edit)
        val intent = intent
        dailyNeedsId = intent.getIntExtra("dailyneeds_id", -1)
        val dailyNeedsName = intent.getStringExtra("dailyneeds_name")
        val dailyNeedsWeight = intent.getIntExtra("dailyneeds_weight", 0)
        val dailyNeedsStock = intent.getIntExtra("dailyneeds_stock", 0)

        // Jika foodId valid, berarti kita dalam mode edit
        if (dailyNeedsId != -1) {
            etNamaKebutuhan.setText(dailyNeedsName)
            etBeratKebutuhan.setText(dailyNeedsWeight.toString())
            etJumlahStokKebutuhan.setText(dailyNeedsStock.toString())
        }

        btnSimpan.setOnClickListener {
            val namaKebutuhan = etNamaKebutuhan.text.toString()
            val beratKebutuhan = etBeratKebutuhan.text.toString().toIntOrNull()
            val jumlahStok = etJumlahStokKebutuhan.text.toString().toIntOrNull()

            if (namaKebutuhan.isNotBlank() && beratKebutuhan != null && jumlahStok != null) {
                if (dailyNeedsId != -1) {
                    // Mode edit: perbarui data makanan yang ada
                    val updatedKebutuhan = KebutuhanEntity(
                        id = dailyNeedsId!!,
                        namaKebutuhan = namaKebutuhan,
                        beratKebutuhan = beratKebutuhan,
                        jumlahStok = jumlahStok
                    )
                    viewModel.updateKebutuhan(updatedKebutuhan)
                    Toast.makeText(this, "Data Kebutuhan Diperbarui", Toast.LENGTH_SHORT).show()
                } else {
                    // Mode tambah: tambah data makanan baru
                    val newKebutuhan = KebutuhanEntity(
                        namaKebutuhan = namaKebutuhan,
                        beratKebutuhan = beratKebutuhan,
                        jumlahStok = jumlahStok
                    )
                    viewModel.insertKebutuhan(newKebutuhan)
                    Toast.makeText(this, "Data kebutuhan Ditambahkan", Toast.LENGTH_SHORT).show()
                }
                finish() // Kembali ke FoodActivity
            } else {
                Toast.makeText(this, "Silakan lengkapi semua field", Toast.LENGTH_SHORT).show()
            }
        }
    }
}