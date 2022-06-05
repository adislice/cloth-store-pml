package com.uty.clothstore.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.uty.clothstore.API.APIRequestData
import com.uty.clothstore.API.RetrofitServer
import com.uty.clothstore.DetailProdukActivity
import com.uty.clothstore.MyApplication
import com.uty.clothstore.R
import com.uty.clothstore.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class KeranjangRVAdapter(private val dataSet: ArrayList<KeranjangRVModel>, private val krjView: TextView) :
    RecyclerView.Adapter<KeranjangRVAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val keranjangProdukJudul: TextView = view.findViewById(R.id.keranjang_nama_produk)
        val keranjangProdukHarga: TextView = view.findViewById(R.id.keranjang_harga_produk)
        val keranjangProdukJumlah: TextView = view.findViewById(R.id.keranjang_jumlah_produk)
        val keranjangProdukGambar: ImageView = view.findViewById(R.id.keranjang_gambar_produk)
        val keranjangProdukHapus: ImageButton = view.findViewById(R.id.keranjang_hapus_item)

        init {
            // Define click listener for the ViewHolder's View.
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_keranjang, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val produkId: Int = dataSet[position].produkId
        val produkJml = dataSet[position].produkQty
        val hargaStlhDiskon = dataSet[position].produkHargaDiskon

        val ardData = RetrofitServer.getConnection()!!.create(APIRequestData::class.java)
        val tampilProduk = ardData.produk_tampil_data(produkId)
        tampilProduk.enqueue(object: Callback<ResponseModel<ProdukModel>> {
            override fun onResponse(
                call: Call<ResponseModel<ProdukModel>>,
                response: Response<ResponseModel<ProdukModel>>
            ) {
                when(response.code()){
                    200 -> {
                        val produkGambar = response.body()!!.records!![0].gambar
                        val produkHarga = response.body()!!.records!![0].harga
                        viewHolder.keranjangProdukJudul.text = response.body()!!.records!![0].nama_produk
                        viewHolder.keranjangProdukHarga.text = MyApplication.rupiah(hargaStlhDiskon.toInt())
                        viewHolder.keranjangProdukJumlah.text = produkJml.toString()
                        Glide.with(viewHolder.itemView.context)
                            .load(produkGambar)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .placeholder(R.drawable.nophoto)
                            .into(viewHolder.itemView.findViewById(R.id.keranjang_gambar_produk))
                        viewHolder.keranjangProdukHapus.setOnClickListener {
                            MyApplication.delFromCart(viewHolder.itemView.context, produkId)

                            dataSet.removeAt(viewHolder.adapterPosition)
                            notifyItemRemoved(viewHolder.adapterPosition)

                            var total: Double = 0.0
                            for (item in MyApplication.getSemuaKeranjang(viewHolder.itemView.context)!!) {
                                total += item.produkHargaDiskon * item.produkQty
                            }

                            krjView.text = "Rp. " + total.toInt()

                        }
                    }
                }
            }

            override fun onFailure(call: Call<ResponseModel<ProdukModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size



}

private fun rupiah(number: Int): String {
    val localeID = Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    numberFormat.maximumFractionDigits = 0
    return numberFormat.format(number)
}