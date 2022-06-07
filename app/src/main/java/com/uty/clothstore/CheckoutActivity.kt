package com.uty.clothstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.airbnb.lottie.LottieAnimationView
import com.uty.clothstore.API.APIRequestData
import com.uty.clothstore.API.RetrofitServer
import com.uty.clothstore.model.KeranjangRVModel
import com.uty.clothstore.model.ResponseModel
import com.uty.clothstore.model.TambahTransaksiModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.*

class CheckoutActivity : AppCompatActivity() {
    private lateinit var totalBayar: TextView
    private lateinit var loading: View
    private lateinit var etNama: EditText
    private lateinit var etAlamat: EditText
    private lateinit var etNoTelp: EditText
    private lateinit var etPayment: AutoCompleteTextView
    private lateinit var btnBayar: TextView
    private lateinit var sukses: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        loading = findViewById(R.id.loading_checkout)

        etNama      = findViewById(R.id.checkout_et_nama)
        etAlamat    = findViewById(R.id.checkout_et_alamat)
        etNoTelp    = findViewById(R.id.checkout_et_notelp)
        etPayment   = findViewById(R.id.checkout_et_payment)
        totalBayar  = findViewById(R.id.checkout_total_bayar)
        btnBayar    = findViewById(R.id.checkout_bayar)
        sukses = findViewById(R.id.layoutsukses)

        val totalan = intent.getDoubleExtra("total", 0.0)

        totalBayar.text = rupiah(totalan.toInt())
        val type = arrayOf("DANA", "OVO", "GoPay")

        val adapter = ArrayAdapter(
            this,
            R.layout.menu_payment,
            type
        )

        etPayment.setAdapter(adapter)

        btnBayar.setOnClickListener{
            val nama = etNama.text.toString()
            val alamat = etAlamat.text.toString()
            val notelp = etNoTelp.text.toString()
            val payment = etPayment.text.toString()

            if(nama.isEmpty() &&
                alamat.isEmpty() &&
                notelp.isEmpty() &&
                payment.isEmpty()
            ) {
                Toast.makeText(applicationContext,"Form harus diisi dengan lengkap", Toast.LENGTH_SHORT).show()
            } else if (nama.isEmpty()) {
                etNama.error = "Nama penerima tidak boleh kosong"
            } else if (alamat.isEmpty()) {
                etAlamat.error = "Alamat pengiriman tidak boleh kosong"
            } else if (notelp.isEmpty()) {
                etNoTelp.error = "Nomor telepon penerima tidak boleh kosong"
            } else if (notelp.isEmpty()) {
                etPayment.error = "Metode pembayaran salah"
            } else {
                payment(totalan.toInt())
                Toast.makeText(applicationContext,"Transaksi berhasil dibuat", Toast.LENGTH_SHORT).show()
//                finish()
            }
        }
    }
    private fun payment(subtotal: Int){
        loading.visibility = View.VISIBLE
        val idUser:Int = MyApplication.id_user
        val statusPembayaran = "Belum bayar"
        val ardData = RetrofitServer.getConnection()!!.create(APIRequestData::class.java)
        val tambahTransaksiData = ardData.tambah_transaksi(
            idUser.toInt(),
            subtotal.toInt(),
            etNama.text.toString(),
            etAlamat.text.toString(),
            etNoTelp.text.toString(),
            etPayment.text.toString(),
            statusPembayaran.toString()
        )
        tambahTransaksiData.enqueue(object: Callback<ResponseModel<TambahTransaksiModel>> {
            override fun onResponse(
                call: Call<ResponseModel<TambahTransaksiModel>>,
                response: Response<ResponseModel<TambahTransaksiModel>>
            ) {
                when(response.code()){
                    200 -> {
                        val idTransaksi = response.body()!!.records!![0].id_transaksi
                        val carts = MyApplication.getSemuaKeranjang(this@CheckoutActivity)!!
                        for (item in carts) {
                            tambah_item_ke_transaksi(idTransaksi, item)
                        }
                        sukses.visibility = View.VISIBLE
                        val lottie_sukses = sukses.findViewById<LottieAnimationView>(R.id.lottie_sukses)
                        lottie_sukses.playAnimation()
                        val btn_selesai = sukses.findViewById<Button>(R.id.btn_selesai)
                        btn_selesai.setOnClickListener {
                            sukses.visibility = View.GONE
                            MyApplication.bersihkanKeranjang(this@CheckoutActivity)
                            finish()
                        }
                    }
                }
                loading.visibility = View.GONE
            }

            override fun onFailure(call: Call<ResponseModel<TambahTransaksiModel>>, t: Throwable) {
                loading.visibility = View.GONE
                Toast.makeText(this@CheckoutActivity, "Transaksi gagal", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun tambah_item_ke_transaksi(idTransaksi: Int, item: KeranjangRVModel){
        val total = item.produkHargaDiskon*item.produkQty
        val ardData = RetrofitServer.getConnection()!!.create(APIRequestData::class.java)
        val tambahDetailTransaksi = ardData.tambah_detail_transaksi(idTransaksi, item.produkId, item.produkQty, total.toInt())
        tambahDetailTransaksi.enqueue(object: Callback<ResponseModel<String>>{
            override fun onResponse(
                call: Call<ResponseModel<String>>,
                response: Response<ResponseModel<String>>,
            ) {
                when(response.code()){
                    200 -> {
                        Log.v("Transaksi", "Berhasil menambahkan item ${item.produkId.toString()} ke transaksi ${idTransaksi.toString()}")
                    }
                    404 -> {
                        Log.v("Transaksi", "Gagal menambahkan item ${item.produkId.toString()} ke transaksi ${idTransaksi.toString()}")
                    }
                }
            }

            override fun onFailure(call: Call<ResponseModel<String>>, t: Throwable) {
                TODO("Not yet implemented")
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