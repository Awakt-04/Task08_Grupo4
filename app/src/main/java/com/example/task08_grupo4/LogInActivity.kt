package com.example.task08_grupo4

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LogInActivity : AppCompatActivity() {
    private lateinit var bienvenido:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = intent.getStringExtra("Usuario")
        bienvenido= findViewById(R.id.Bienvenido)
        bienvenido.text = "Bienvenido, $email"
    }
}