package com.uty.clothstore

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.uty.clothstore.API.APIRequestData
import com.uty.clothstore.API.RetrofitServer
import com.uty.clothstore.Model.KeranjangItemModel
import com.uty.clothstore.model.ProdukModel
import com.uty.clothstore.model.ResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
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
    private lateinit var tvJudulProduk: TextView
    private lateinit var tvDiskonProduk: TextView
    private lateinit var tvKategoriProduk: TextView
    private lateinit var tvHargaAsliProduk: TextView
    private lateinit var tvHargaFinalProduk: TextView
    private lateinit var tvDeskripsiProduk: TextView
    private lateinit var loadingDetail: View
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
        tvJudulProduk = findViewById(R.id.detail_produk_nama)
        tvDiskonProduk = findViewById(R.id.detail_produk_diskon)
        tvKategoriProduk = findViewById(R.id.detail_produk_kategori)
        tvHargaAsliProduk = findViewById(R.id.detail_produk_harga_asli)
        tvHargaFinalProduk = findViewById(R.id.detail_produk_harga_final)
        tvDeskripsiProduk = findViewById(R.id.detail_produk_deskripsi)
        loadingDetail = findViewById(R.id.loading_detail_produk)

//        val id_user = intent.getIntExtra("id_user", 0)

        val idProduk = intent.getIntExtra("id_produk", 1)

        retrieveDetailProduk(idProduk)

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
            // intent.putExtra("id_user", id_user)
            startActivity(intent)
        }
        btnTambahKeranjang.setOnClickListener{
//            val intent = Intent(this, KeranjangActivity::class.java)
//            Toast.makeText(applicationContext, "kuantitas : " + etQty.text.toString().toInt(), Toast.LENGTH_SHORT).show()
//            if (kategori == "obat") {
//                tambahObatKeKeranjang(id_user, idobat, etqty.text.toString().toInt())
//            }
//            startActivity(intent)


            val qty = Integer.parseInt(etQty.text.toString())
            val gson = Gson()
            var list = ArrayList<KeranjangItemModel>()
            list.add(KeranjangItemModel(idProduk, qty))
            val json = gson.toJson(list)//converting list to Json
            val sharedPref =  getSharedPreferences("app_data", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("keranjang", json)
                apply()
            }

            val json2 = sharedPref.getString("keranjang",null)
            val type = object : TypeToken<ArrayList<KeranjangItemModel>>(){}.type
            val readd = gson.fromJson<ArrayList<KeranjangItemModel>>(json2,type)
            Toast.makeText(this, "id: "+readd[0].id_produk+", qty:"+readd[0].qty, Toast.LENGTH_LONG).show()
        }
    }
    private fun retrieveDetailProduk(id_produk: Int){
        imgProduk.visibility = View.GONE
        tvJudulProduk.visibility = View.GONE
        tvDiskonProduk.visibility = View.GONE
        tvKategoriProduk.visibility = View.GONE
        tvHargaAsliProduk.visibility = View.GONE
        tvHargaFinalProduk.visibility = View.GONE
        tvDeskripsiProduk.visibility = View.GONE
        loadingDetail.visibility = View.VISIBLE
        val ardData = RetrofitServer.getConnection()!!.create(APIRequestData::class.java)
        val tampilProduk = ardData.produk_tampil_data(id_produk)
        tampilProduk.enqueue(object: Callback<ResponseModel<ProdukModel>> {

            override fun onFailure(call: Call<ResponseModel<ProdukModel>>, t: Throwable) {
                Toast.makeText(this@DetailProdukActivity, "Gagal meload produk", Toast.LENGTH_LONG).show()
                loadingDetail.visibility = View.GONE
            }

            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<ResponseModel<ProdukModel>>,
                response: Response<ResponseModel<ProdukModel>>
            ) {
                when(response.code()){
                    200 -> {
                        val diskonnya: Double
                        val img = response.body()!!.records!![0].gambar
                        val hargaAsli:Int = response.body()!!.records!![0].harga
                        val diskonPersen:Int = response.body()!!.records!![0].diskon_persen
                        if(diskonPersen == 0){
                            tvDiskonProduk.visibility = View.GONE
                            tvHargaAsliProduk.visibility = View.GONE
                            tvHargaFinalProduk.text = rupiah(hargaAsli)
                            tvHargaFinalProduk.visibility = View.VISIBLE
                        } else {
                            diskonnya = diskonPersen.toDouble()/100
                            val hargaFinal = hargaAsli - (hargaAsli*diskonnya)
                            tvDiskonProduk.text = "$diskonPersen%"
                            tvHargaAsliProduk.text = rupiah(hargaAsli)
                            tvHargaFinalProduk.text = rupiah(hargaFinal.toInt())

                            tvDiskonProduk.visibility = View.VISIBLE
                            tvHargaAsliProduk.visibility = View.VISIBLE
                            tvHargaFinalProduk.visibility = View.VISIBLE
                        }
                        Glide.with(this@DetailProdukActivity)
                            .load(img)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .placeholder(R.color.white)
                            .into(imgProduk)

                        tvJudulProduk.text = response.body()!!.records!![0].nama_produk
                        tvKategoriProduk.text = response.body()!!.records!![0].nama_kategori
                        tvDeskripsiProduk.text = response.body()!!.records!![0].deskripsi


                    }
                }
                imgProduk.visibility = View.VISIBLE
                tvJudulProduk.visibility = View.VISIBLE
                tvKategoriProduk.visibility = View.VISIBLE
                tvDeskripsiProduk.visibility = View.VISIBLE
                loadingDetail.visibility = View.GONE
            }
        })
    }
}

private fun rupiah(number: Int): String {
    val localeID = Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    numberFormat.maximumFractionDigits = 0
    return numberFormat.format(number)
}