package com.example.medsnepal.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.medsnepal.R
import com.example.medsnepal.api.ServiceBuilder
import com.example.medsnepal.entity.Signin
import com.example.medsnepal.entity.User
import com.example.medsnepal.repository.UserRepository
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.scottyab.aescrypt.AESCrypt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Call
import java.lang.Exception
import java.security.GeneralSecurityException

class LoginActivity : AppCompatActivity() {

    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnLogin: Button
    private lateinit var btnNewRegister : Button
    private lateinit var linearLayout: LinearLayout
    var loginHashPass:String?=null
    private val TAG = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnNewRegister = findViewById(R.id.btnNewRegister)
        linearLayout = findViewById(R.id.linearLayout)

        btnNewRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (email.isNullOrEmpty()) {
            Snackbar.make(linearLayout, "Email is required", Snackbar.LENGTH_LONG).show()
           } else if (!isValidEmail(email)) {
                Snackbar.make(linearLayout, "Enter Valid Email", Snackbar.LENGTH_LONG).show()
            }else if (password.isNullOrEmpty()) {
                Snackbar.make(linearLayout, "Password is required", Snackbar.LENGTH_LONG).show()
            } else {
                login()
            }
        }
    }


    private fun login(){
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()

        val user = Signin(email = email, password = password)
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val repository = UserRepository()
                val response = repository.checkuser(user)
                Log.d(TAG, "login: email and password")
                if(response.success == false){
                    Log.d(TAG, "login: success")
                    ServiceBuilder.token = "Bearer"+response.token
                    Snackbar.make(linearLayout,""+response.message,Snackbar.LENGTH_LONG).show()
                } else {
                    withContext(Dispatchers.Main){
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        intent.putExtra("value", response.name)
                        startActivity(intent)
                       // startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    }
                }
            }catch (e: Exception){
                withContext(Dispatchers.Main){
                    Snackbar.make(linearLayout,"Invalid Email or Password",Snackbar.LENGTH_LONG).show()
                    Log.d(TAG, "login: Exception " + e.toString())
                }
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}