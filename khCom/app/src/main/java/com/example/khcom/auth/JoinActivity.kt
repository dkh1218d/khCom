package com.example.khcom.auth

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.example.khcom.MainActivity
import com.example.khcom.R
import com.example.khcom.databinding.ActivityJoinBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class JoinActivity : AppCompatActivity() {

    private lateinit var join_binding : ActivityJoinBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        join_binding = DataBindingUtil.setContentView(this, R.layout.activity_join)
        auth = Firebase.auth

        var idchk = false
        var pwdchk = false

        val pa = android.util.Patterns.EMAIL_ADDRESS
        join_binding.signupId.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(pa.matcher(join_binding.signupId.text).matches()) {
                    join_binding.idLo.error = null
                    idchk = true
                }else{
                    join_binding.idLo.error = "올바른 이메일 형식으로 입력해 주세요"
                    idchk = false
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })


        join_binding.signupPwd.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(s!!.length < 6){
                    join_binding.pwdLo.error = "비밀번호는 6자리 이상의 문자로 해 주세요"
                    pwdchk = false
                }else{
                    if(join_binding.signupPwdchk.text.toString().equals(s!!.toString())){
                        join_binding.pwdLo.error = null
                        pwdchk = true
                    }else{
                        join_binding.pwdLo.error = "비밀번호가 일치하지 않습니다"
                        pwdchk = false
                    }
                }
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })

        join_binding.signupPwdchk.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(join_binding.signupPwd.text.toString().equals(s!!.toString())){
                    if(s!!.length < 6){
                        join_binding.pwdLo.error = "비밀번호는 6자리 이상의 문자로 해 주세요"
                        pwdchk = false
                    }else{
                        join_binding.pwdLo.error = null
                        pwdchk = true
                    }
                }else{
                    join_binding.pwdLo.error = "비밀번호가 일치하지 않습니다"
                    pwdchk = false
                }
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })

        // Enter 입력 시 키보드 제거
        join_binding.signupPwdchk.setOnEditorActionListener { v, actionId, event ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(join_binding.signupPwdchk.windowToken, 0)
            false
        }

        join_binding.signupNext.setOnClickListener {
            if(!idchk){join_binding.signupId.setText("");join_binding.signupId.requestFocus();return@setOnClickListener}
            if(!pwdchk){join_binding.signupPwd.setText("");join_binding.signupPwdchk.setText("");join_binding.signupPwd.requestFocus();return@setOnClickListener}

            auth.createUserWithEmailAndPassword(join_binding.signupId.text.toString(), join_binding.signupPwd.text.toString())
                .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "성공적으로 가입되셨습니다", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    /* ActivityCompat.finishAffinity(this) */

                    startActivity(intent)
                } else {
                    Toast.makeText(this, "회원 가입에 실패했습니다", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}