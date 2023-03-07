package com.example.foodcounter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_reg.setOnClickListener {
            val intentReg = Intent (this, RegActivity::class.java)
            startActivity(intentReg)
        }
        btn_signin.setOnClickListener {
            val intentSignin = Intent (this, LoginActivity::class.java)
            startActivity(intentSignin)
        }


    }
}