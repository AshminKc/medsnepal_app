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
import java.lang.Exception
import java.security.GeneralSecurityException

class LoginActivity : AppCompatActivity() {

    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnLogin: Button
    private lateinit var btnNewRegister : Button
    private lateinit var chkRememberMe: CheckBox
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
        chkRememberMe = findViewById(R.id.chkRememberMe)
        linearLayout = findViewById(R.id.linearLayout)

//        val sharedPref = getSharedPreferences("MyPref", MODE_PRIVATE)
//        if (sharedPref.getBoolean("logged", true)) {
//            startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
//        }

        btnNewRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
        btnLogin.setOnClickListener {
            //login()
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

    public fun login(){
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()

        /*try {
            loginHashPass = AESCrypt.encrypt(password1, "Password is hashed")
        } catch (e: GeneralSecurityException) {
            e.toString()
        }*/

        CoroutineScope(Dispatchers.IO).launch {
            try{
                val repository = UserRepository()
                val response = repository.checkuser(email,password)
                Log.d(TAG, "login: email and password")
                if(response.success == true){
                    Log.d(TAG, "login: success")
                    ServiceBuilder.token = "Bearer"+response.token
                    Snackbar.make(linearLayout,"True",Snackbar.LENGTH_LONG).show()
                } else {
                    withContext(Dispatchers.Main){
                        Snackbar.make(linearLayout,"fALSE",Snackbar.LENGTH_LONG).show()
                    }
                }
            }catch (e: Exception){
                withContext(Dispatchers.Main){
                    Snackbar.make(linearLayout,"Error In Login",Snackbar.LENGTH_LONG).show()
                    Log.d(TAG, "login: Exception " + e.toString())
                }
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}