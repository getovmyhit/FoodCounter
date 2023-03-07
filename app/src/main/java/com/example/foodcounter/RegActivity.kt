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

        val user = "String"
        val email= "Sabaka@sk.com"
        val password = "12345"
        btn_finishReg.setOnClickListener {
            if(etxt_regName.text.toString() == "$user" && etxt_regPassword.text.toString() == "$password") {
                Toast.makeText(this, "Вы вошли в систему", Toast.LENGTH_SHORT).show()
                txtProfile.text = "Добро пожаловать в FoodCounter,\nДорогой друг $user"
            }
            else {
                Toast.makeText(this, "Ошибка входа", Toast.LENGTH_SHORT).show()
                txtProfile.text = "Ошибка доступа\nПопробуйте снова!"
            }
        }

    }
}