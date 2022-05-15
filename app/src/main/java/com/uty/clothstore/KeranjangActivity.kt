package com.uty.clothstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uty.clothstore.adapter.KeranjangRVAdapter
import com.uty.clothstore.model.KeranjangRVModel

class KeranjangActivity : AppCompatActivity() {
    private lateinit var keranjangView: RecyclerView
    private var keranjangList = ArrayList<KeranjangRVModel>()
    private var keranjangAdapter: RecyclerView.Adapter<*>? = null
    private var keranjangViewManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keranjang)

        keranjangView = findViewById(R.id.keranjang_rv)
        keranjangList.add(
            KeranjangRVModel(
                "キャップ HIROSHIMA VTS",
                200000,
                "https://ksufmvei.sirv.com/cloth-store/m62335136641_1.jpg",
                1))
        keranjangList.add(
            KeranjangRVModel(
                "トレンチコート (帝人)グリーン　　新品未使用",
                1000000,
                "https://ksufmvei.sirv.com/cloth-store/m11717748462_1.jpg",
                1))
        keranjangList.add(
            KeranjangRVModel(
                "トップス",
                450000,
                "https://ksufmvei.sirv.com/cloth-store/m56266346169_1.jpg",
                1))
        keranjangList.add(
            KeranjangRVModel(
                "【FRAGILE】ジップアップニット　セーター　サイズ38",
                300000,
                "https://ksufmvei.sirv.com/cloth-store/m60346299519_1.jpg",
                1))
        keranjangList.add(
            KeranjangRVModel(
                "【FRAGILE】ジップアップニット　セーター　サイズ38",
                300000,
                "https://ksufmvei.sirv.com/cloth-store/m60346299519_1.jpg",
                1))
        keranjangList.add(
            KeranjangRVModel(
                "【FRAGILE】ジップアップニット　セーター　サイズ38",
                300000,
                "https://ksufmvei.sirv.com/cloth-store/m60346299519_1.jpg",
                1))
        keranjangList.add(
            KeranjangRVModel(
                "【FRAGILE】ジップアップニット　セーター　サイズ38",
                300000,
                "https://ksufmvei.sirv.com/cloth-store/m60346299519_1.jpg",
                1))
        keranjangList.add(
            KeranjangRVModel(
                "【FRAGILE】ジップアップニット　セーター　サイズ38",
                300000,
                "https://ksufmvei.sirv.com/cloth-store/m60346299519_1.jpg",
                1))
        keranjangList.add(
            KeranjangRVModel(
                "【FRAGILE】ジップアップニット　セーター　サイズ38",
                300000,
                "https://ksufmvei.sirv.com/cloth-store/m60346299519_1.jpg",
                1))
        keranjangViewManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        keranjangAdapter = KeranjangRVAdapter(keranjangList)
        keranjangView?.apply {
            this.setHasFixedSize(true)
            adapter = keranjangAdapter
            layoutManager = keranjangViewManager
        }
    }
}