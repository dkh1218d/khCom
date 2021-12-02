package com.example.khcom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.khcom.auth.IntroActivity
import com.example.khcom.databinding.ActivityMainBinding
import com.example.khcom.fragments.MainFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var main_binding : ActivityMainBinding
    private lateinit var auth : FirebaseAuth
    val main_con = MainFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main_binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        auth = Firebase.auth
        val currentUser = auth.currentUser

        val tran = supportFragmentManager.beginTransaction()
        tran.replace(R.id.main_container, main_con)
        tran.commit()
    }
}