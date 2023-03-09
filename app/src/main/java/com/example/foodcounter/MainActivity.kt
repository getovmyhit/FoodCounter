package com.example.foodcounter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.edTxt_adress
import kotlinx.android.synthetic.main.activity_main.edTxt_password
import kotlinx.android.synthetic.main.activity_main.progressbar
import kotlinx.android.synthetic.main.activity_main.textInputLayout
import kotlinx.android.synthetic.main.activity_main.textInputLayout2
import kotlinx.android.synthetic.main.activity_reg.*

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth

        btn_signup.setOnClickListener {
            val intentReg = Intent (this, RegActivity::class.java)
            startActivity(intentReg)
        }
        //вход в систему с проверкой почты и пароля
        btn_signin.setOnClickListener {
            progressbar.isVisible=true
            val email = edTxt_adress.text.toString()
            val password = edTxt_password.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty())
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener() { task ->
                    android.os.Handler().postDelayed({ progressbar.isVisible = false }, 1000)
                    if (task.isSuccessful) {
                        val intentFirst = Intent(this, FirstActivity::class.java)
                        startActivity(intentFirst)
                        finish()
                    } else {
                        Toast.makeText(
                            this, "Ошибка входа, попробуйте снова!",
                            Toast.LENGTH_SHORT
                        ).show()
                        textInputLayout.helperText = getString(R.string.error_mail)
                        textInputLayout2.helperText = getString(R.string.errorLog_password)
                    }
                }
            else {
                when {
                    email.isEmpty() -> {
                        Toast.makeText(this, "Введите почту", Toast.LENGTH_SHORT).show()
                        android.os.Handler().postDelayed({ progressbar.isVisible = false}, 1000)
                    }
                    password.isEmpty() -> {
                        Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show()
                        android.os.Handler().postDelayed({ progressbar.isVisible = false}, 1000)

                    }
                    else -> {}
                }
            }
        }

        val authDB = Firebase
        edTxt_adress.doOnTextChanged { text, start, before, count ->
            when {
                //edTxt_adress.text != auth

            }
        }
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