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
    private lateinit var etNoTelp: EditText
    private lateinit var etPassword: EditText
    private lateinit var etPassword2: EditText
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
        etNoTelp = findViewById(R.id.et_reg_telp)
        etAlamat = findViewById(R.id.et_reg_alamat)
        etPassword = findViewById(R.id.et_reg_password)
        etPassword2 = findViewById(R.id.et_reg_password2)
        loading = findViewById(R.id.loading_register)
        
        btnRegister.setOnClickListener{
            val nama = etNama.text.toString()
            val uname = etUsername.text.toString()
            val email = etEmail.text.toString()
            val alamat = etAlamat.text.toString()
            val notelp = etNoTelp.text.toString()
            val password = etPassword.text.toString()
            val password2 = etPassword2.text.toString()

            if(nama.isEmpty() &&
                uname.isEmpty() &&
                email.isEmpty() &&
                alamat.isEmpty() &&
                password.isEmpty() &&
                password2.isEmpty()
            ) {
                Toast.makeText(applicationContext,"Form harus diisi dengan lengkap", Toast.LENGTH_SHORT).show()
            } else if (nama.isEmpty()) {
                etNama.error = "Nama lengkap tidak boleh kosong"
            } else if (nama.isEmpty()) {
                etUsername.error = "Username tidak boleh kosong"
            } else if (email.isEmpty()) {
                etEmail.error = "Email tidak boleh kosong"
            } else if (notelp.isEmpty()) {
                etNoTelp.error = "No. Telepon tidak boleh kosong"
            } else if (alamat.isEmpty()) {
                etAlamat.error = "ALamat tidak boleh kosong"
            } else if (password.isEmpty()) {
                Toast.makeText(applicationContext,"Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else if (password2.isEmpty()) {
                Toast.makeText(applicationContext,"Konfirmasi Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else if (password != password2) {
                Toast.makeText(applicationContext,"Konfirmasi Password tidak cocok", Toast.LENGTH_SHORT).show()
            } else {
                register()
                Toast.makeText(applicationContext,"Pendaftaran berhasil", Toast.LENGTH_SHORT).show()
                finish()
            }
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