package com.example.foodcounter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*



class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = Firebase.auth

        btn_signIn2.setOnClickListener {
            lprogressbar.isVisible=true
            val email = edTxt_adress.text.toString()
            val password = edTxt_password.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty())
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(){ task ->
                        lprogressbar.isVisible=false
                        if (task.isSuccessful) {
                            val intentFirst = Intent(this, FirstActivity::class.java)
                            startActivity(intentFirst)
                            finish()
                        } else {
                            Toast.makeText(this, "Ошибка входа, попробуйте снова!",
                                Toast.LENGTH_SHORT).show()
                            textInputLayout.helperText = getString(R.string.error_mail)
                            textInputLayout2.helperText = getString(R.string.errorLog_password)

                        }
                    }
            else {
                when {
                    email.isEmpty() -> {
                        Toast.makeText(this, "Введите почту", Toast.LENGTH_SHORT).show()
                        lprogressbar.isVisible = false
                    }
                    password.isEmpty() -> {
                        Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show()
                        lprogressbar.isVisible = false
                    }
                    else -> {}
                }
            }
        }
    }
    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        val intentFirst = Intent(this, FirstActivity::class.java)
        if (currentUser != null) {
            startActivity(intentFirst)
          }
          else{ }
    }
}