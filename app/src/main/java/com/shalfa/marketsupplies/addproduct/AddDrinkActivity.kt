package com.shalfa.marketsupplies.addproduct

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.shalfa.marketsupplies.databinding.ActivityAddDrinkBinding
import com.shalfa.marketsupplies.entity.MinumanEntity
import com.shalfa.marketsupplies.view_model.DrinkViewModel

class AddDrinkActivity : AppCompatActivity() {
    private val viewModel: DrinkViewModel by viewModels()
    private var drinkId: Int? = null
    private lateinit var binding: ActivityAddDrinkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDrinkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val intent = intent
        drinkId = intent.getIntExtra("drink_id", -1)
        val drinkName = intent.getStringExtra("drink_name")
        val drinkWeight = intent.getIntExtra("drink_weight", 0)
        val drinkStock = intent.getIntExtra("drink_stock", 0)

        if (drinkId != -1) {
            binding.etNamaMinuman.setText(drinkName)
            binding.etBeratMinuman.setText(drinkWeight.toString())
            binding.etJumlahStokMinuman.setText(drinkStock.toString())
        }

        binding.btnSimpan.setOnClickListener {
            val namaMinuman = binding.etNamaMinuman.text.toString()
            val beratMinuman = binding.etBeratMinuman.text.toString().toIntOrNull()
            val jumlahStok = binding.etJumlahStokMinuman.text.toString().toIntOrNull()

            if (namaMinuman.isNotBlank() && beratMinuman != null && jumlahStok != null) {
                if (drinkId != -1) {
                    val updatedMinuman = MinumanEntity(
                        id = drinkId!!,
                        namaMinuman = namaMinuman,
                        beratMinuman = beratMinuman,
                        jumlahStok = jumlahStok
                    )
                    viewModel.updateMinuman(updatedMinuman)
                    viewModel.updateToFirebase(updatedMinuman)
                    Toast.makeText(this, "Data Minuman Diperbarui", Toast.LENGTH_SHORT).show()
                } else {
                    val newId = (System.currentTimeMillis() / 1000).toInt()
                    val newMinuman = MinumanEntity(
                        id = newId,
                        namaMinuman = namaMinuman,
                        beratMinuman = beratMinuman,
                        jumlahStok = jumlahStok
                    )
                    viewModel.insertMinuman(newMinuman)
                    viewModel.insertToFirebase(newMinuman)
                    Toast.makeText(this, "Data Minuman Ditambahkan", Toast.LENGTH_SHORT).show()
                }
                finish()
            } else {
                Toast.makeText(this, "Silakan lengkapi semua field", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
