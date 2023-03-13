package com.example.foodcounter


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_reg.*

class RegActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)
        auth = Firebase.auth


        btn_finishReg.setOnClickListener {
            progressbar.isVisible=true
            val name = etxt_regName.text.toString()
            val email = etxt_regMail.text.toString()
            val password = etxt_regPassword.text.toString()
            val currentUser = auth.currentUser
            var intentStart = Intent(this, MainActivity::class.java)
            if (etxt_regName.text.toString().isNotEmpty() && etxt_regMail.text.toString()
                    .isNotEmpty() && etxt_regPassword.text.toString().isNotEmpty())
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(){ task ->
                        progressbar.isVisible=false
                        if (task.isSuccessful) {
                            val userUpdater = userProfileChangeRequest {
                                displayName = name
                            }
                            currentUser!!.updateProfile(userUpdater)
                            startActivity(intentStart)
                            finish()
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(this, "Ошибка, попробуйте снова!", Toast.LENGTH_SHORT).show()
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