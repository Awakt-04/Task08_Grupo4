package com.example.task08_grupo4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var registerButton: Button
    private lateinit var loginButton: Button
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Binding views
        registerButton = findViewById(R.id.registerButton)
        loginButton = findViewById(R.id.logInButton)
        emailEditText = findViewById(R.id.editTextEmail)
        passwordEditText = findViewById(R.id.editTextTextPassword)

        registerButton.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // Basic validation
            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "El mail o la contraseña no pueden estar vacíos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Sign in the user
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(this, "Inicio de sesión exitoso!", Toast.LENGTH_SHORT).show()

                        // Navigate to LogInActivity
                        val intent = Intent(this, LogInActivity::class.java)
                        intent.putExtra("Usuario",email.substring(0,email.indexOf("@")))
                        startActivity(intent)
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(this, "Error de autentificación: E-mail/Contraseña NO VALIDA", Toast.LENGTH_SHORT).show()
                    }
                }

        }
    }
}
