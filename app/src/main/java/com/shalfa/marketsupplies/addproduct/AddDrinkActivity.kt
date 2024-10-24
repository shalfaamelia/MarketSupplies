package com.shalfa.marketsupplies.addproduct

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.shalfa.marketsupplies.R
import com.shalfa.marketsupplies.entity.MinumanEntity
import com.shalfa.marketsupplies.view_model.DrinkViewModel

class AddDrinkActivity : AppCompatActivity() {

    private val viewModel: DrinkViewModel by viewModels()
    private var drinkId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_drink)

        val etNamaMinuman = findViewById<EditText>(R.id.etNamaMinuman)
        val etBeratMinuman = findViewById<EditText>(R.id.etBeratMinuman)
        val etJumlahStokMinuman = findViewById<EditText>(R.id.etJumlahStokMinuman)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)

        val intent = intent
        drinkId = intent.getIntExtra("drink_id", -1)
        val drinkName = intent.getStringExtra("drink_name")
        val drinkWeight = intent.getIntExtra("drink_weight", 0)
        val drinkStock = intent.getIntExtra("drink_stock", 0)

        if (drinkId != -1) {
            etNamaMinuman.setText(drinkName)
            etBeratMinuman.setText(drinkWeight.toString())
            etJumlahStokMinuman.setText(drinkStock.toString())
        }

        btnSimpan.setOnClickListener {
            val namaMinuman = etNamaMinuman.text.toString()
            val beratMinuman = etBeratMinuman.text.toString().toIntOrNull()
            val jumlahStok = etJumlahStokMinuman.text.toString().toIntOrNull()

            if (namaMinuman.isNotBlank() && beratMinuman != null && jumlahStok != null) {
                if (drinkId != -1) {
                    val updatedMinuman = MinumanEntity(
                        id = drinkId!!,
                        namaMinuman = namaMinuman,
                        beratMinuman = beratMinuman,
                        jumlahStok = jumlahStok
                    )
                    viewModel.updateMinuman(updatedMinuman)
                    Toast.makeText(this, "Data Minuman Diperbarui", Toast.LENGTH_SHORT).show()
                } else {
                    val newMinuman = MinumanEntity(
                        namaMinuman = namaMinuman,
                        beratMinuman = beratMinuman,
                        jumlahStok = jumlahStok
                    )
                    viewModel.insertMinuman(newMinuman)
                    Toast.makeText(this, "Data Minuman Ditambahkan", Toast.LENGTH_SHORT).show()
                }
                finish()
            } else {
                Toast.makeText(this, "Silakan lengkapi semua field", Toast.LENGTH_SHORT).show()
            }
        }
    }
}