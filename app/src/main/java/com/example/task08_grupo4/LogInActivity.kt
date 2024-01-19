package com.example.task08_grupo4

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LogInActivity : AppCompatActivity() {
    private lateinit var bienvenido:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Declaración, inicialización y obtención de valores de variable
        val email = intent.getStringExtra("Usuario")
        // Asignación a elemento XML
        bienvenido= findViewById(R.id.Bienvenido)
        // Modificación valor de texto
        bienvenido.text = "Bienvenido, $email"
    }
}