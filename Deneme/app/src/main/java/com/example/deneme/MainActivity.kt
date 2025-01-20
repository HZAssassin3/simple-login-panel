package com.example.deneme

import DatabaseHelper
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.deneme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)

        binding.loginButton.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            if (dbHelper.getUser(username, password)) {
                Toast.makeText(applicationContext, "Giriş Yapıldı!", Toast.LENGTH_SHORT).show()

                // Giriş başarılı ise HomeActivity'ye geçiş yap
                val intent = Intent(this@MainActivity, HomeActivity::class.java)
                startActivity(intent)
                finish() // MainActivity'yi kapat
            } else {
                Toast.makeText(applicationContext, "Giriş Yapılamadı!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
