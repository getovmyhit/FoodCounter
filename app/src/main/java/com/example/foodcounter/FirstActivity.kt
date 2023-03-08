package com.example.foodcounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_first.*
import kotlinx.android.synthetic.main.activity_main.*


private lateinit var auth: FirebaseAuth
class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        auth = Firebase.auth
        val currentUser = auth.currentUser

        if (currentUser == null) {
            txtname.text = "$currentUser"
        }
        else {}

        btn_exit.setOnClickListener {
           Firebase.auth.signOut()
            if (currentUser != null) {
                txtname.text = "$currentUser"
            }
            else {txtname.text = "До свидания!"}
        }
    }
}
