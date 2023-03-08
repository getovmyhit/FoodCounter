package com.example.foodcounter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_first.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers.Main


private lateinit var auth: FirebaseAuth
class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        auth = Firebase.auth
        val currentUser = auth.currentUser

        btn_exit.setOnClickListener {
           Firebase.auth.signOut()
            val intentStart= Intent(this, MainActivity::class.java)
            if (currentUser != null) {
                txtname.text = "До свидания!"
                android.os.Handler().postDelayed({ startActivity(intentStart)}, 2000)
            }
            else {}
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            txtname.text = "Добро пожаловать!\n"+currentUser.email.toString()
            textView1.text = currentUser.displayName
            textView2.text = currentUser.uid
        }
        else {}
    }
}
