package com.example.foodcounter

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_first.*
import kotlinx.android.synthetic.main.activity_first.view.*


private lateinit var auth: FirebaseAuth
class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        auth = Firebase.auth
        val currentUser = auth.currentUser

        btn_exit.setOnClickListener {
           Firebase.auth.signOut()
            val intentStart= Intent(this, MainActivity::class.java)
            if (currentUser != null) {
                infotext.text = "До свидания!"
                android.os.Handler().postDelayed({ startActivity(intentStart)}, 2000)
            }
            else {}
        }

            //showName.set

        val userUpdater = userProfileChangeRequest {
            displayName=showName.text.toString()
            //photoUri=
        }
        btn_update.setOnClickListener {
           currentUser!!.updateProfile(userUpdater).addOnCompleteListener {task ->
               if (task.isSuccessful)
               {
                   onStart()
               }
               else {
                   infotext.text = "Произошла ошибка"
               }
           }
        }


    }


    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser

        if (currentUser != null) {
            val avatar = currentUser.photoUrl
            infotext.text = "Добро пожаловать!\n"
            avatarView.setImageURI(avatar)
            showName.text = currentUser.displayName
            showEmail.text = currentUser.email
            showUID.text = currentUser.uid
            showVerified.text = currentUser.isEmailVerified.toString()
        }
        else {}
    }
}
