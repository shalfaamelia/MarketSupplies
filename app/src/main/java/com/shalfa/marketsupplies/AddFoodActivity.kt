package com.shalfa.marketsupplies

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddFoodActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food)

        val etId = findViewById<EditText>(R.id.etId)
        val etNamaMakanan = findViewById<EditText>(R.id.etNamaMakanan)
        val etBeratMakanan = findViewById<EditText>(R.id.etBeratMakanan)
        val etJumlahStokMakanan = findViewById<EditText>(R.id.etJumlahStokMakanan)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)

        btnSimpan.setOnClickListener {
            val id = etId.text.toString().toIntOrNull()
            val namaMakanan = etNamaMakanan.text.toString()
            val beratMakanan = etBeratMakanan.text.toString().toDoubleOrNull() ?: 0.0
            val jumlahStokMakanan = etJumlahStokMakanan.text.toString().toIntOrNull() ?: 0

            val resultIntent = intent
            resultIntent.putExtra("EXTRA_NAMA_MAKANAN", namaMakanan)
            resultIntent.putExtra("EXTRA_BERAT_MAKANAN", beratMakanan)
            resultIntent.putExtra("EXTRA_JUMLAH_STOK", jumlahStokMakanan)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}