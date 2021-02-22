package com.example.medsnepal.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.medsnepal.R
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnLogin: Button
    private lateinit var btnNewRegister : Button
    private lateinit var chkRememberMe: CheckBox
    private lateinit var linearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnNewRegister = findViewById(R.id.btnNewRegister)
        chkRememberMe = findViewById(R.id.chkRememberMe)
        linearLayout = findViewById(R.id.linearActivity)
//        val sharedPref = getSharedPreferences("MyPref", MODE_PRIVATE)
//        if (sharedPref.getBoolean("logged", true)) {
//            startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
//        }

        btnNewRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
        btnLogin.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }
}