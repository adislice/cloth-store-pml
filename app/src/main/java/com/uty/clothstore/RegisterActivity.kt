package com.uty.clothstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.uty.clothstore.API.APIRequestData
import com.uty.clothstore.API.RetrofitServer
import com.uty.clothstore.model.ResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var btnLogin: TextView
    private lateinit var btnRegister: Button
    private lateinit var etUsername : EditText
    private lateinit var etNama: EditText
    private lateinit var etEmail: EditText
    private lateinit var etAlamat: EditText
    private lateinit var etPassword: EditText
    private lateinit var etNoTelp: EditText
    private lateinit var loading: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.hide()

        btnLogin = findViewById(R.id.register_btn_login)
        btnRegister = findViewById(R.id.register_btn_daftar)
        etNama = findViewById(R.id.et_reg_nama)
        etUsername = findViewById(R.id.et_reg_username)
        etEmail = findViewById(R.id.et_reg_email)
        etAlamat = findViewById(R.id.et_reg_alamat)
        etPassword = findViewById(R.id.et_reg_password)
        etNoTelp = findViewById(R.id.et_reg_telp)
        loading = findViewById(R.id.loading_register)
        
        btnRegister.setOnClickListener{
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//            Toast.makeText(applicationContext, "Pendaftaran berhasil", Toast.LENGTH_SHORT).show()
            register()
        }
        btnLogin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    fun register(){
        loading.visibility = View.VISIBLE
        val ardData = RetrofitServer.getConnection()!!.create(APIRequestData::class.java)
        val registerData = ardData.user_register(etUsername.text.toString(), etNama.text.toString(), etEmail.text.toString(), etNoTelp.text.toString(), etAlamat.text.toString(), etPassword.text.toString())
        registerData.enqueue(object: Callback<ResponseModel<String>> {
            override fun onResponse(
                call: Call<ResponseModel<String>>,
                response: Response<ResponseModel<String>>
            ) {
                when(response.code()){
                    200 -> {
                        Toast.makeText(this@RegisterActivity, response.body()!!.message, Toast.LENGTH_LONG).show()
                    }
                }
                loading.visibility = View.GONE
            }

            override fun onFailure(call: Call<ResponseModel<String>>, t: Throwable) {
                loading.visibility = View.GONE
            }

        }
        )
    }
}