package com.example.foodcounter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth

        btn_reg.setOnClickListener {
            val intentReg = Intent (this, RegActivity::class.java)
            startActivity(intentReg)
        }
        btn_signin.setOnClickListener {
            val intentSignin = Intent (this, LoginActivity::class.java)
            startActivity(intentSignin)
        }

       /* var gratz = intent.getBooleanExtra("true",false)
        if (gratz)
        {
            val currentUser = auth.currentUser
            Toast.makeText(this, "Авторизовались как ${currentUser?.email.toString()}", Toast.LENGTH_SHORT).show()
        }
        else{ }*/

    }
    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        val intentFirst = Intent(this, FirstActivity::class.java)
        if (currentUser != null) {
            startActivity(intentFirst)
            Toast.makeText(this, "Авторизовались как ${currentUser?.email.toString()}", Toast.LENGTH_SHORT).show()
        }
        else{ }

    }
}