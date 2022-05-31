package com.uty.clothstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var fragm: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var menuKeranjang = findViewById<ImageButton>(R.id.menu_keranjang)
        val bott_nav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val fm = supportFragmentManager

        if (savedInstanceState == null) {
            val t = fm.beginTransaction()
            val fragme = HomeFragment()
            t.replace(R.id.content_frame, fragme).commit()
            bott_nav.menu.findItem(R.id.menu_home).isChecked = true
        } else {
            fragm = fm.findFragmentById(R.id.content_frame)!!
        }
        bott_nav.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.menu_home -> {
                    val fragme = HomeFragment()
                    fm.beginTransaction()
                        .replace(R.id.content_frame, fragme)
                        .commit()
                }
                R.id.menu_produk -> {
                    val fragme = DaftarProdukFragment()
                    fm.beginTransaction()
                        .replace(R.id.content_frame, fragme)
                        .commit()
                }
                R.id.menu_profil -> {
                    val fragme = ProfileFragment()
                    fm.beginTransaction()
                        .replace(R.id.content_frame, fragme)
                        .commit()
                }
            }

            return@setOnItemSelectedListener true
        }

        val appbar: MaterialToolbar = findViewById(R.id.topAppBar)

        appbar.setOnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.menu_keranjang -> {
                    val intent = Intent(this, KeranjangActivity::class.java)
                    startActivity(intent)
                    return@setOnMenuItemClickListener true
                }
                else -> return@setOnMenuItemClickListener true
            }
        }


    }

    fun getBottomNav(): BottomNavigationView{
        return findViewById<BottomNavigationView>(R.id.bottom_nav)
    }
}


