package com.uty.clothstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var fragm: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var menuKeranjang = findViewById<ImageButton>(R.id.menu_keranjang)
        var bott_nav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        var fm = supportFragmentManager

        if (savedInstanceState == null) {
            var t = fm.beginTransaction()
            var fragme = HomeFragment()
            t.replace(R.id.content_frame, fragme).commit()
            bott_nav.menu.findItem(R.id.menu_home).isChecked = true
        } else {
            fragm = fm.findFragmentById(R.id.content_frame)!!
        }
        bott_nav.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.menu_home -> {
                    var fragme = HomeFragment()
                    fm.beginTransaction()
                        .replace(R.id.content_frame, fragme)
                        .commit()
                }
                R.id.menu_produk -> {
                    var fragme = DaftarProdukFragment()
                    fm.beginTransaction()
                        .replace(R.id.content_frame, fragme)
                        .commit()
                }
                R.id.menu_profil -> {
                    var fragme = ProfileFragment()
                    fm.beginTransaction()
                        .replace(R.id.content_frame, fragme)
                        .commit()
                }
            }

            return@setOnItemSelectedListener true
        }
//        menuKeranjang.setOnClickListener{
//            val intent = Intent(this, KeranjangActivity::class.java)
//            startActivity(intent)
//        }
    }

    fun getBottomNav(): BottomNavigationView{
        return findViewById<BottomNavigationView>(R.id.bottom_nav)
    }
}


