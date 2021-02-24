package com.example.medsnepal.ui

import android.R.id.message
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.medsnepal.R
import com.example.medsnepal.api.ServiceBuilder
import com.example.medsnepal.entity.User
import com.example.medsnepal.repository.UserRepository
import com.google.android.material.snackbar.Snackbar
import com.scottyab.aescrypt.AESCrypt
import com.toxicbakery.bcrypt.Bcrypt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.security.GeneralSecurityException


class RegisterActivity : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var edtConfirmPassword: EditText
    private lateinit var bnRegister: Button
    private lateinit var bnLogin:Button
    private lateinit var linearLayout: LinearLayout
    private val TAG = "RegisterActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        linearLayout = findViewById(R.id.linearActivity)
        edtName =  findViewById(R.id.etName)
        edtEmail =  findViewById(R.id.etEmail)
        edtPassword =  findViewById(R.id.etPassword)
        edtConfirmPassword = findViewById(R.id.etCPassword)
        bnRegister = findViewById(R.id.btnRegister)
        bnLogin = findViewById(R.id.bnLogin);

        bnLogin.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }

        bnRegister.setOnClickListener({ v ->

            val name = edtName.text.toString()
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            val cpassword = edtConfirmPassword.text.toString()

            if (name.isNullOrEmpty()) {
                Snackbar.make(linearLayout, "Name is required", Snackbar.LENGTH_LONG).show()
            } else if (email.isNullOrEmpty() && name.isNotEmpty()) {
                Snackbar.make(linearLayout, "Email is required", Snackbar.LENGTH_LONG).show()
            } else if (!isValidEmail(email) && name.isNotEmpty()) {
                Snackbar.make(linearLayout, "Enter Valid Email", Snackbar.LENGTH_LONG).show()
            } else if (password.isNullOrEmpty() && email.isNotEmpty()) {
                Snackbar.make(linearLayout, "Password is required", Snackbar.LENGTH_LONG).show()
            } else if (cpassword.isNullOrEmpty() && password.isNotEmpty()) {
                Snackbar.make(linearLayout, "Confirm Password is required", Snackbar.LENGTH_LONG).show()
            } else if (password != cpassword) {
                Snackbar.make(linearLayout, "Password doesn't match", Snackbar.LENGTH_LONG).show()
            } else {
                val user = User(name = name, email = email, password = password)
                CoroutineScope(Dispatchers.IO).launch {
                    val repository = UserRepository()
                    val response = repository.registerUser(user)
                    if (response.success == false) {
                        ServiceBuilder.token = response.token
                        withContext(Main) {
                            Snackbar.make(linearLayout, "Some issues at server end", Snackbar.LENGTH_LONG).show()
                        }
                    } else {
                        withContext(Main) {
                            Snackbar.make(linearLayout, "" + response.message, Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
            }
        })
    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}