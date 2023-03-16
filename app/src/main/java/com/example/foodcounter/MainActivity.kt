package com.example.foodcounter


import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_first.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.edtxt_emailLogin
import kotlinx.android.synthetic.main.activity_main.edtxt_loginPass
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
            val email = edtxt_emailLogin.text.toString()
            val password = edtxt_loginPass.text.toString()
            progressbar1.isVisible=true
            if (email.isNotEmpty() && password.isNotEmpty())
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        //val intentFirst = Intent(this, FirstActivity::class.java)
                        onStart()
                        //startActivity(intentFirst)
                        //finish()
                    } else {
                        Toast.makeText(
                            this, "Ошибка входа, попробуйте снова!",
                            Toast.LENGTH_SHORT).show()
                        textInputLayout.helperText = getString(R.string.error_mail)
                        textInputLayout2.helperText = getString(R.string.errorLog_password)
                        progressbar1.isVisible = false
                    }
                }
            else {
                when {
                    email.isEmpty() -> {
                        Toast.makeText(this, "Введите почту", Toast.LENGTH_SHORT).show()
                        progressbar1.isVisible = false
                    }
                    password.isEmpty() -> {
                        Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show()
                        progressbar1.isVisible = false
                    }
                    else -> {}
                }
            }
        }

        var dialog = AlertDialog.Builder(this)
        dialog
            .setTitle("Восстановление пароля")
            .setMessage("В РАЗРАБОТКЕ!")
            .setIcon(android.R.drawable.ic_delete)
            .setNegativeButton("Закрыть",DialogInterface.OnClickListener { dialog, which ->
                onRestart() })
            .setPositiveButton("Отправить", DialogInterface.OnClickListener { dialog, which ->
                onRestart() })
            .create()
        btn_forget.setOnClickListener {
            dialog.show()
        }
    }
    override fun onStart() {
        super.onStart()
        val intentFirst = Intent(this, FirstActivity::class.java)
        val currentUser = auth.currentUser
        val name = intent.getStringExtra("name")
        if (currentUser != null && currentUser.displayName.isNullOrEmpty()) {
            val userUpdater = userProfileChangeRequest {
            displayName=name
            }
            currentUser.updateProfile(userUpdater)
            //intentFirst.putExtra("name",name)
            //startActivity(intentFirst)
            //Toast.makeText(this, "Зарегались как $name + ${currentUser.displayName.toString()}", Toast.LENGTH_SHORT).show()
        }
        if (currentUser != null)
        {
            txtnameApp.setOnClickListener {
                edtxt_emailLogin.setText(currentUser!!.displayName.toString())
            }
            progressbar1.visibility= View.VISIBLE
            edtxt_emailLogin.setText(currentUser.email.toString())
            android.os.Handler().postDelayed({ startActivity(intentFirst)}, 1000)
            android.os.Handler().postDelayed({Toast.makeText(this, "Авторизовались" +
                    " как ${currentUser.displayName.toString()}", Toast.LENGTH_SHORT).show()}, 1000)
            android.os.Handler().postDelayed({ progressbar1.isVisible=false}, 1000)
            finish()
        }
        else{ }

    }
}