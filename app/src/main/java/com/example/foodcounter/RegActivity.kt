package com.example.foodcounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_reg.*

class RegActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)

        btn_finishReg.setOnClickListener {

            when {
                TextUtils.isEmpty(etxt_regName.toString()) -> Toast.makeText(this, "Введите ваше имя", Toast.LENGTH_SHORT).show()
                TextUtils.isEmpty(etxt_regMail.toString()) -> Toast.makeText(this, "Введите почту", Toast.LENGTH_SHORT).show()
                TextUtils.isEmpty(etxt_regPassword.toString()) -> Toast.makeText(this, "Придумайте пароль", Toast.LENGTH_SHORT).show()
            else->{}
            }
        }
    }
}