package com.poddar.talks


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class Login : AppCompatActivity() {


    private lateinit var password : EditText
    private lateinit var login: Button
    private lateinit var signup: Button
    private lateinit var logo: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var email: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()
        login = findViewById(R.id.loginbtn)
        signup = findViewById(R.id.signupbtn1)
        logo = findViewById(R.id.logo)
        password =findViewById(R.id.Password)
        email = findViewById(R.id.emaillgn)

        signup.setOnClickListener {
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }

        login.setOnClickListener {

            val Password = password.text.toString()
            val Email = email.text.toString()

            login(Email,Password);



        }

    }

    private fun login (Email: String, Password: String) {
        mAuth.signInWithEmailAndPassword(Email, Password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val intent = Intent(this@Login , MainActivity::class.java)
                    finish()
                    startActivity(intent)


                } else {

                    Toast.makeText(this@Login, "User does not exist", Toast.LENGTH_SHORT).show()
                }
            }
    }
}