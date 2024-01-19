package com.example.task08_grupo4

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInActivity : AppCompatActivity() {


    // Declaración Instancia autenticación Firebase
    private lateinit var auth: FirebaseAuth

    // Declaración variables
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var backButton: Button
    private lateinit var signInButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

    // Inicialización Instancia autenticación Firebase
        auth = Firebase.auth

    // Inicialización variables con elementos del XML
        backButton = findViewById(R.id.backButton)
        signInButton = findViewById(R.id.registerButton1)
        emailEditText = findViewById(R.id.mailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)

    // Configuración fondo botones
        backButton.backgroundTintList = resources.getColorStateList(R.color.grisClaro);
        signInButton.backgroundTintList = resources.getColorStateList(R.color.verdeMenta);


    // Escucha de botón cancelar
        backButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    // Escucha de botón registrar
        signInButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

    // Comprobación campos en blanco
            if (email.isBlank() || password.isBlank()) {
                showToast("El mail o la contraseña no puede estar vacío.")
                return@setOnClickListener
            }

    // comprobación de existencia de usuario, en caso de no existir lo crea, en caso de existir muestra alerta
    // si hubiese algún fallo, muestra alerta
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        showToast("Registro Correcto!")

                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        if (password.length<6){ showToast("Error de autentificación: Contraseña Demasiado Corta ") }
                        else{ showToast("Error de autentificación: El E-mail ya esta en Uso ") }
                    }
                }
        }
    }
    // Función de mostrado de alertas
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}


