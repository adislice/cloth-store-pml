package com.uty.clothstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uty.clothstore.adapter.KeranjangRVAdapter
import com.uty.clothstore.model.KeranjangRVModel
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class KeranjangActivity : AppCompatActivity() {
    private lateinit var keranjangView: RecyclerView
    private var keranjangList = ArrayList<KeranjangRVModel>()
    private var keranjangAdapter: RecyclerView.Adapter<*>? = null
    private var keranjangViewManager: LinearLayoutManager? = null
    private lateinit var btnCheckout: Button
    private lateinit var loadingKrj: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keranjang)

        val back_btn = findViewById<Toolbar>(R.id.keranjang_appbar)
        back_btn.setNavigationOnClickListener {
            onBackPressed()
        }

        btnCheckout = findViewById(R.id.keranjang_checkout)
        loadingKrj = findViewById(R.id.loading_keranjang)
        loadingKrj.visibility = View.VISIBLE
        keranjangView = findViewById(R.id.keranjang_rv)

        val tvTotal = findViewById<TextView>(R.id.keranjang_total)
        keranjangList = MyApplication.getSemuaKeranjang(this)!!

        keranjangViewManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        keranjangAdapter = KeranjangRVAdapter(keranjangList, tvTotal)
        keranjangView?.apply {
            this.setHasFixedSize(true)
            adapter = keranjangAdapter
            layoutManager = keranjangViewManager
        }

        var total: Double = 0.0
        for (item in keranjangList) {
            total += item.produkHargaDiskon * item.produkQty
        }

        tvTotal.text = rupiah(total.toInt())
        loadingKrj.visibility = View.GONE

        btnCheckout.setOnClickListener{
            val intent = Intent(this, CheckoutActivity::class.java)
            intent.putExtra("total", total)
            startActivity(intent)
        }
    }
}

private fun rupiah(number: Int): String {
    val localeID = Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    numberFormat.maximumFractionDigits = 0
    return numberFormat.format(number)
}