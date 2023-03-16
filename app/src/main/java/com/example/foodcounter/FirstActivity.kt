package com.example.foodcounter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
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

        btn_restart.setOnClickListener {
            val restart = Intent(this, FirstActivity::class.java)
            startActivity(restart)
            finish()
       }
        btn_exit.setOnClickListener {
           Firebase.auth.signOut()
            val intentStart= Intent(this, MainActivity::class.java)
            if (currentUser != null) {
                infotext.text = "До свидания!"
                android.os.Handler().postDelayed({ startActivity(intentStart)}, 1500)
                //startActivity(intentStart)
            }
            else {}
        }

        showName.setOnClickListener {
            if(nameChanger.text.toString().isNullOrEmpty())
                nameChanger.setText(showName.text)
            else{}
            if(nameHider.isVisible) {
                nameHider.isVisible = false
                showName.setTextColor(resources.getColor(R.color.blue,null))
            }
            else{
                nameHider.isVisible = true
                showName.setTextColor(resources.getColor(R.color.red_app,null))
                }
        }

        btn_update.setOnClickListener {
            if (nameChanger.text.toString().isNotEmpty() && nameChanger.text.toString() != showName.text.toString()) {
                val userUpdater = userProfileChangeRequest {
                    displayName = nameChanger.text.toString()
                    //photoUri=
                }
                currentUser!!.updateProfile(userUpdater).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        onStart()
                        nameHider.isVisible = false
                    } else {
                        infotext.text = "Произошла ошибка"
                    }
                }
            }
            else{
                infotext.text = "Поле имя пустое или равно текущему"
            }
        }
    }


    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        val avatar = currentUser!!.photoUrl

        if (currentUser != null) {
            infotext.text = "Добро пожаловать!\n ${currentUser.displayName}"
            avatarView.setImageURI(avatar)
            showName.text = currentUser.displayName
            nameChanger.setText(showName.text)
            showEmail.text = currentUser.email
            showUID.text = currentUser.uid
            showVerified.text = currentUser.isEmailVerified.toString()
            nameHider.isVisible = false
            showName.setTextColor(resources.getColor(R.color.blue,null))
        }
        else { }
    }
}
