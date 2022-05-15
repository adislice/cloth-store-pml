package com.uty.clothstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import java.text.NumberFormat
import java.util.*

class DetailProdukActivity : AppCompatActivity() {
    private lateinit var etQty: EditText
    private lateinit var btnTambahQty: ImageButton
    private lateinit var btnKurangQty: ImageButton
    private lateinit var btnKembali: ImageButton
    private lateinit var btnKeranjang: ImageButton
    private lateinit var btnTambahKeranjang: Button
    private lateinit var imgProduk: ImageView
    private lateinit var judulProduk: TextView
    private lateinit var diskonProduk: TextView
    private lateinit var kategoriProduk: TextView
    private lateinit var hargaAsliProduk: TextView
    private lateinit var hargaFinalProduk: TextView
    private lateinit var deskripsiProduk: TextView
    private var qty: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_produk)

        supportActionBar?.hide()

        etQty = findViewById(R.id.produk_qty)
        btnTambahQty = findViewById(R.id.button_produk_tambah)
        btnKurangQty = findViewById(R.id.button_produk_kurangi)
        btnKembali = findViewById(R.id.detail_produk_back)
        btnKeranjang = findViewById(R.id.detail_produk_keranjang)
        btnTambahKeranjang = findViewById(R.id.detail_produk_tambah_keranjang)
        imgProduk = findViewById(R.id.detail_produk_gambar)
        judulProduk = findViewById(R.id.detail_produk_nama)
        diskonProduk = findViewById(R.id.detail_produk_diskon)
        kategoriProduk = findViewById(R.id.detail_produk_kategori)
        hargaAsliProduk = findViewById(R.id.detail_produk_harga_asli)
        hargaFinalProduk = findViewById(R.id.detail_produk_harga_final)
        deskripsiProduk = findViewById(R.id.detail_produk_deskripsi)

        // INTERAKSI QUANTITY
        qty = etQty.text.toString().toInt()
        btnTambahQty.setOnClickListener {
            // Pass total stok produk dari database ke sini untuk ganti angka 10
            if(qty < 10)
            qty ++
            etQty.setText(qty.toString())
        }
        btnKurangQty.setOnClickListener {
            if (qty > 1){
                qty --
                etQty.setText(qty.toString())
            }
        }
        // Set On Click Listener di setiap komponen Button
        btnKembali.setOnClickListener {finish()}
        btnKeranjang.setOnClickListener {
            // Intent ke Activity Keranjang
            val intent = Intent(this, KeranjangActivity::class.java)
//    intent.putExtra("id_user", id_user)
            startActivity(intent)
        }
        btnTambahKeranjang.setOnClickListener{
            val intent = Intent(this, KeranjangActivity::class.java)
            Toast.makeText(applicationContext, "kuantitas : " + etQty.text.toString().toInt(), Toast.LENGTH_SHORT).show()
//            if (kategori == "obat") {
//                tambahObatKeKeranjang(id_user, idobat, etqty.text.toString().toInt())
//            }
            startActivity(intent)
        }
    }
}

private fun rupiah(number: Double): String {
    val localeID = Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    numberFormat.maximumFractionDigits = 0
    return numberFormat.format(number)
}