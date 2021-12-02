package com.example.khcom.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.khcom.MainActivity
import com.example.khcom.R
import com.example.khcom.databinding.ActivityIntroBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class IntroActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var intro_binding : ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intro_binding = DataBindingUtil.setContentView(this, R.layout.activity_intro)

        auth = Firebase.auth

        intro_binding.introLoginBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        intro_binding.introSignupBtn.setOnClickListener {
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
        }

        intro_binding.introGuestBtn.setOnClickListener {
            auth.signInAnonymously().addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}