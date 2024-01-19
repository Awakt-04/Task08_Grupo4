package com.example.task08_grupo4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {


    // Declaración de variables
    private lateinit var registerButton: Button
    private lateinit var loginButton: Button
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    // Declaración de Instancia de autenticación de Firebase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    // Inicialización de Instancia de autenticación de Firebase
        auth = FirebaseAuth.getInstance()

    // Asignación de elementos del XML a las variables
        registerButton = findViewById(R.id.registerButton)
        loginButton = findViewById(R.id.logInButton)
        emailEditText = findViewById(R.id.editTextEmail)
        passwordEditText = findViewById(R.id.editTextTextPassword)

    // Configuración de colores de botones
        registerButton.backgroundTintList = resources.getColorStateList(R.color.grisClaro);
        loginButton.backgroundTintList = resources.getColorStateList(R.color.verdeMenta);


    // Evento de escucha de pulsado de botón de registro
        registerButton.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }

    // Evento de escucha de pulsado de botón de inicio de sesión
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

    // Comprobación campos en blanco
            if (email.isBlank() || password.isBlank()) {
                showToast("El mail o la contraseña no pueden estar vacíos.")
                return@setOnClickListener
            }

    // Inicio de sesión de Firebase
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        showToast("Inicio de sesión exitoso!")
    // Redirección actividad de inicio de sesión
                        val intent = Intent(this, LogInActivity::class.java)
                        intent.putExtra("Usuario",email.substring(0,email.indexOf("@")))
                        startActivity(intent)
                        finish()
                    } else {
                        showToast("Error de autentificación: E-mail/Contraseña NO VALIDA")
                    }
                }

        }
    }

    // Función de mostrado de alertas
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
