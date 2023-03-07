package com.example.foodcounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_reg.*

class RegActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)

        val user = String
        val email: String
        val password = String
        btn_finishReg.setOnClickListener {
            if(etxt_regName.text.toString() == "$user" && etxt_regPassword.text.toString() == "$password") {
                Toast.makeText(this, "Login is successed", Toast.LENGTH_SHORT).show()
                txtProfile.text = "Welcome to FoodCounter Dir, \n$user"
            }
            else {
                Toast.makeText(this, "Login is failed", Toast.LENGTH_SHORT).show()
                txtProfile.text = "Access error\nTry again!"
            }
        }

    }
}