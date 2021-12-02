package com.example.khcom.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.khcom.MainActivity
import com.example.khcom.R
import com.example.khcom.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var login_binding : ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        login_binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        auth = Firebase.auth

        login_binding.loginSuccess.setOnClickListener {
            val user_id = login_binding.loginId.text.toString()
            val user_pwd = login_binding.loginPwd.text.toString()

            auth.signInWithEmailAndPassword(user_id, user_pwd)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        login_binding.loginSign.setOnClickListener {
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
        }
    }
}