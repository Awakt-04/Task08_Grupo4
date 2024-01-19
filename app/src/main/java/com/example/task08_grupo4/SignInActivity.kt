package com.example.task08_grupo4

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

    private lateinit var auth: FirebaseAuth

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var backButton: Button
    private lateinit var signInButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        // Initialize Firebase Auth
        auth = Firebase.auth

        // Binding views
        backButton = findViewById(R.id.button)
        signInButton = findViewById(R.id.button2)
        emailEditText = findViewById(R.id.nombreEditText)
        passwordEditText = findViewById(R.id.passwordEditText)

        backButton.setOnClickListener {
            // Navigate back to the previous Activity
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        signInButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // Basic validation
            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "El mail o la contraseña no puede estar vacío.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Create a new user
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
                        Toast.makeText(this, "Registro Correcto!", Toast.LENGTH_SHORT).show()

                        // Navigate to the Main Activity
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        if (password.length<6){
                        Toast.makeText(this, "Error de autentificación: Contraseña Demasiado Corta ", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            Toast.makeText(this, "Error de autentificación: El E-mail ya esta en Uso ", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        }
    }
}


