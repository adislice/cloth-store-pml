package com.uty.clothstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.uty.clothstore.API.APIRequestData
import com.uty.clothstore.API.RetrofitServer
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
                finish()
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
                        Toast.makeText(this@CheckoutActivity, response.body()!!.message, Toast.LENGTH_LONG).show()
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
}

private fun rupiah(number: Int): String {
    val localeID = Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    numberFormat.maximumFractionDigits = 0
    return numberFormat.format(number)
}