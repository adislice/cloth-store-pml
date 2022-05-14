package com.uty.clothstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class LandingActivity: AppCompatActivity() {

    private val TAG = "LandingActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        getSupportActionBar()?.hide()

        val btnRegister: Button = findViewById(R.id.btnLandingRegister)
        val btnLogin: Button = findViewById(R.id.btnLandingLogin)

        btnRegister.setBackgroundResource(R.drawable.bg_button)
        btnLogin.setBackgroundResource(R.drawable.bg_button)

        btnLogin.setOnClickListener {
            val i = Intent(this.applicationContext, LoginActivity::class.java)
            startActivity(i)
            Log.v(TAG, "Berhasil pindah ke Login")
        }

        btnRegister.setOnClickListener {
            val i = Intent(this.applicationContext, MainActivity::class.java)
            startActivity(i)
            Log.v(TAG, "Berhasil pindah ke Main Activity")
        }
    }
}