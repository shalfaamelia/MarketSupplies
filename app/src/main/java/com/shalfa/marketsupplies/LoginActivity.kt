package com.shalfa.marketsupplies

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.shalfa.marketsupplies.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val correctUsername = "admin"
        val correctPassword = "admin123"

        binding.buttonLogin.setOnClickListener {
            val enteredUsername = binding.editTextNama.text.toString()
            val enteredPassword = binding.editTextPassword.text.toString()

            if (enteredUsername == correctUsername && enteredPassword == correctPassword) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Nama atau Password salah", Toast.LENGTH_SHORT).show()
            }
        }
    }
}