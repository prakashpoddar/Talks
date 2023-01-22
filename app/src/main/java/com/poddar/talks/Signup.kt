package com.poddar.talks

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Signup : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var signup: Button
    private lateinit var logo: ImageView
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)


        mAuth = FirebaseAuth.getInstance()
        email = findViewById(R.id.signupemail)
        password = findViewById(R.id.Password)
        signup = findViewById(R.id.signupbtn)
        name = findViewById(R.id.name)
        logo = findViewById(R.id.logo)





        signup.setOnClickListener {
            val name = name.text.toString()
            val Email = email.text.toString()
            val Password = password.text.toString()


            signup(name, Email, Password);

        }
    }


    private fun signup(name: String, Email: String, Password: String) {


        mAuth.createUserWithEmailAndPassword(Email, Password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    addUserToDatabase(name, Email, mAuth.currentUser?.uid!!)

                    val intent = Intent(this@Signup, MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    Toast.makeText(this@Signup, "Error occurred", Toast.LENGTH_SHORT).show()


                }
            }

    }

    private fun addUserToDatabase(name: String, Email: String, uid: String) {

        mDbRef = FirebaseDatabase.getInstance().getReference()
        mDbRef.child("user").child(uid).setValue(User(name, Email, uid))


    }
}

