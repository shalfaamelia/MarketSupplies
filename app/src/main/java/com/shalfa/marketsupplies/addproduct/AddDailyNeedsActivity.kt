package com.shalfa.marketsupplies.addproduct

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.shalfa.marketsupplies.databinding.ActivityAddDailyneedsBinding
import com.shalfa.marketsupplies.entity.KebutuhanEntity
import com.shalfa.marketsupplies.view_model.DailyNeedsViewModel

class AddDailyNeedsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddDailyneedsBinding
    private val viewModel: DailyNeedsViewModel by viewModels()
    private var dailyNeedsId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddDailyneedsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        dailyNeedsId = intent.getIntExtra("dailyneeds_id", -1)
        val dailyNeedsName = intent.getStringExtra("dailyneeds_name")
        val dailyNeedsWeight = intent.getIntExtra("dailyneeds_weight", 0)
        val dailyNeedsStock = intent.getIntExtra("dailyneeds_stock", 0)

        if (dailyNeedsId != -1) {
            binding.etNamaKebutuhan.setText(dailyNeedsName)
            binding.etBeratKebutuhan.setText(dailyNeedsWeight.toString())
            binding.etJumlahStokKebutuhan.setText(dailyNeedsStock.toString())
        }

        binding.btnSimpan.setOnClickListener {
            val namaKebutuhan = binding.etNamaKebutuhan.text.toString()
            val beratKebutuhan = binding.etBeratKebutuhan.text.toString().toIntOrNull()
            val jumlahStok = binding.etJumlahStokKebutuhan.text.toString().toIntOrNull()

            if (namaKebutuhan.isNotBlank() && beratKebutuhan != null && jumlahStok != null) {
                if (dailyNeedsId != -1) {
                    val updatedKebutuhan = KebutuhanEntity(
                        id = dailyNeedsId!!,
                        namaKebutuhan = namaKebutuhan,
                        beratKebutuhan = beratKebutuhan,
                        jumlahStok = jumlahStok
                    )
                    viewModel.updateKebutuhan(updatedKebutuhan)
                    viewModel.updateToFirebase(updatedKebutuhan)
                    Toast.makeText(this, "Data Kebutuhan Diperbarui", Toast.LENGTH_SHORT).show()
                } else {
                    val newId = (System.currentTimeMillis() / 1000).toInt()
                    val newKebutuhan = KebutuhanEntity(
                        id = newId,
                        namaKebutuhan = namaKebutuhan,
                        beratKebutuhan = beratKebutuhan,
                        jumlahStok = jumlahStok
                    )
                    viewModel.insertKebutuhan(newKebutuhan)
                    viewModel.insertToFirebase(newKebutuhan)
                    Toast.makeText(this, "Data Kebutuhan Ditambahkan", Toast.LENGTH_SHORT).show()
                }
                finish()
            } else {
                Toast.makeText(this, "Silakan lengkapi semua field", Toast.LENGTH_SHORT).show()
            }
        }
    }
}