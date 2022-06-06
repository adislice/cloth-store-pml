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
//        keranjangList.add(
//            KeranjangRVModel(
//                "FRAGILE - Oversized Wool Sweater Dewasa Elegan Sederhana",
//                10000,
//                "https://ksufmvei.sirv.com/cloth-store/m62335136641_1.jpg",
//                1))
//        keranjangList.add(
//            KeranjangRVModel(
//                "Vivienne Westwood Red Label Denim",
//                10000,
//                "https://static.mercdn.net/item/detail/orig/photos/m13451739216_1.jpg?1643179806",
//                1))
//        keranjangList.add(
//            KeranjangRVModel(
//                "L'EST ROSE Gaun Pola Bunga Kotak-kotak Musim Semi / Musim Panas",
//                10000,
//                "https://static.mercdn.net/item/detail/orig/photos/m43540024457_1.jpg?1652407185",
//                1))
//        keranjangList.add(
//            KeranjangRVModel(
//                "HERILL 22ss Jacket Weekend NEW Warna Blue Navy",
//                10000,
//                "https://static.mercdn.net/item/detail/orig/photos/m85062866189_1.jpg?1650211616",
//                1))
//        keranjangList.add(
//            KeranjangRVModel(
//                "L'EST ROSE Dress Hitam Lipit Renda Kamisol Ukuran S",
//                10000,
//                "https://static.mercdn.net/item/detail/orig/photos/m10322858278_1.jpg?1651474675",
//                1))

        val tvTotal = findViewById<TextView>(R.id.keranjang_total)
        keranjangList = MyApplication.getSemuaKeranjang(this)!!

        keranjangViewManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        keranjangAdapter = KeranjangRVAdapter(keranjangList, tvTotal)
        keranjangView?.apply {
            this.setHasFixedSize(true)
            adapter = keranjangAdapter
            layoutManager = keranjangViewManager
        }

        btnCheckout.setOnClickListener{
            val intent = Intent(this, CheckoutActivity::class.java)
            startActivity(intent)
        }

        var total: Double = 0.0
        for (item in keranjangList) {
            total += item.produkHargaDiskon * item.produkQty
        }

        tvTotal.text = "Rp. " + total.toInt()
        loadingKrj.visibility = View.GONE
    }
}