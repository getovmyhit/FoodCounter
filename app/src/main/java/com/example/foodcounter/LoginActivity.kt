package com.example.foodcounter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.common.FirstPartyScopes
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
        val currentUser = auth.currentUser

      /*  if (currentUser != null) {
            startActivity(mainscreen)
        }
        else{ }
*/
        btn_signIn2.setOnClickListener {
            //progressbar.isVisible=true
            val email = edTxt_adress.text.toString()
            val password = edTxt_password.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty())
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(){ task ->
                        //progressbar.isVisible=false
                        if (task.isSuccessful) {
                            var mainscreen = Intent(this, FirstActivity::class.java)
                            startActivity(mainscreen)
                            finish()
                        } else {
                            Toast.makeText(this, "Ошибка входа, попробуйте снова!",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            else {
                when {
                    email.isEmpty() -> {
                        Toast.makeText(this, "Введите почту", Toast.LENGTH_SHORT).show()
                        //progressbar.isVisible = false
                    }
                    password.isEmpty() -> {
                        Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show()
                        //progressbar.isVisible = false
                    }
                    else -> {}
                }
            }
        }
    }
}