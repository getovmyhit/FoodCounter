package com.example.foodcounter


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_reg.*

class RegActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)
        auth = Firebase.auth
        val currentUser = auth.currentUser
        if (currentUser != null) {
            //startActivity(profile)
        }

        etxt_regPassword.doOnTextChanged { text, start, before, count ->
            if (etxt_regPassword.text.length < 8) {
                txterror.isVisible = true
                txterror.text = "Пароль должен содержать не менее 8 символов"
            }
            else {txterror.isVisible = false}
        }

        btn_finishReg.setOnClickListener {
            progressbar.isVisible=true
            val name = etxt_regName.text.toString()
            val email = etxt_regMail.text.toString()
            val password = etxt_regPassword.text.toString()
            if (etxt_regName.text.toString().isNotEmpty() && etxt_regMail.text.toString()
                    .isNotEmpty() && etxt_regPassword.text.toString().isNotEmpty())
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(){ task ->
                        progressbar.isVisible=false
                        if (task.isSuccessful) {
                            var startscreen = Intent(this, MainActivity::class.java)
                            startscreen.putExtra("true",true)
                            startActivity(startscreen)
                            finish()
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(this, "Ошибка, попробуйте снова!", Toast.LENGTH_SHORT).show()
                            onRestart()
                        }
                    }
            else {
                when {
                    etxt_regName.text.toString().isEmpty() -> {
                        Toast.makeText(this, "Введите ваше имя", Toast.LENGTH_SHORT).show()
                        progressbar.isVisible = false
                    }
                    etxt_regMail.text.toString().isEmpty() -> {
                        Toast.makeText(this, "Введите почту", Toast.LENGTH_SHORT).show()
                        progressbar.isVisible = false
                    }
                    etxt_regPassword.text.toString().isEmpty() -> {
                        Toast.makeText(this, "Придумайте пароль", Toast.LENGTH_SHORT).show()
                        progressbar.isVisible = false
                    }
                    else -> {}
                }
            }
        }
    }
}