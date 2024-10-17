package com.shalfa.marketsupplies

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val editTextNama = findViewById<EditText>(R.id.editTextNama)
        val editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)

        // Login hardcode, ganti dengan validasi nama dan password sesuai kebutuhan
        val correctUsername = "admin"
        val correctPassword = "admin123"

        buttonLogin.setOnClickListener {
            val enteredUsername = editTextNama.text.toString()
            val enteredPassword = editTextPassword.text.toString()

            if (enteredUsername == correctUsername && enteredPassword == correctPassword) {
                // Berhasil login, pindah ke halaman Produk
                val intent = Intent(this, ProductActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Gagal login
                Toast.makeText(this, "Nama atau Password salah", Toast.LENGTH_SHORT).show()
            }
        }
    }
}