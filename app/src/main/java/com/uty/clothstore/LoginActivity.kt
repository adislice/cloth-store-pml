package com.uty.clothstore

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.uty.clothstore.API.APIRequestData
import com.uty.clothstore.API.RetrofitServer
import com.uty.clothstore.model.ResponseModel
import com.uty.clothstore.model.UserLoginModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var btnLogin : Button
    private lateinit var btnRegister: TextView
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var loadingLogin: View
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        sharedPref =  getSharedPreferences("app_data", Context.MODE_PRIVATE)
        if (sharedPref.contains("id_user")) {
            val id_user = sharedPref.getInt("id_user", 0)
            if (id_user != 0){
                MyApplication.id_user = id_user
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                finish()
            }
        }

        btnLogin = findViewById(R.id.login_btn_login)
        btnRegister = findViewById(R.id.login_btn_register)
        etUsername = findViewById(R.id.et_log_username)
        etPassword = findViewById(R.id.et_log_password)
        loadingLogin = findViewById(R.id.loading_login)
        btnLogin.setOnClickListener{
//          if
            login()

        }
        btnRegister.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
    private fun login(){
        loadingLogin.visibility = View.VISIBLE
        val ardData = RetrofitServer.getConnection()!!.create(APIRequestData::class.java)
        val registerData = ardData.user_login(etUsername.text.toString(), etPassword.text.toString())
        registerData.enqueue(object: Callback<ResponseModel<UserLoginModel>> {
            override fun onResponse(
                call: Call<ResponseModel<UserLoginModel>>,
                response: Response<ResponseModel<UserLoginModel>>
            ) {
                when(response.code()){
                    200 -> {
                        Toast.makeText(this@LoginActivity, response.body()!!.message, Toast.LENGTH_LONG).show()
                        val id_user =  response.body()!!.records!![0].id_user
                        MyApplication.id_user = id_user
                        with(sharedPref.edit()) {
                            putInt("id_user", id_user)
                            apply()
                        }
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        finishAffinity()
                        startActivity(intent)
                        finish()
                    }
                    404 -> {
                        Toast.makeText(this@LoginActivity, "Login gagal", Toast.LENGTH_LONG).show()
                    }
                }
                loadingLogin.visibility = View.GONE

            }

            override fun onFailure(call: Call<ResponseModel<UserLoginModel>>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Gagal menghubungkan ke server", Toast.LENGTH_LONG).show()
                loadingLogin.visibility = View.GONE
            }


        }
        )
    }
}