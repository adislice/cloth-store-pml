package com.uty.clothstore.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.uty.clothstore.DetailProdukActivity
import com.uty.clothstore.R
import com.uty.clothstore.model.DaftarProdukModel
import com.uty.clothstore.model.HomeRVModel
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class DaftarProdukRVAdapter(private val dataSet: ArrayList<DaftarProdukModel>) :
    RecyclerView.Adapter<DaftarProdukRVAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val produk_judul: TextView
        val produk_harga: TextView
        val produk_gambar: ImageView
        val produk_diskon_persen: TextView

        init {
            // Define click listener for the ViewHolder's View.
            produk_judul = view.findViewById(R.id.produk_judul)
            produk_harga = view.findViewById(R.id.produk_harga)
            produk_gambar = view.findViewById(R.id.produk_gambar)
            produk_diskon_persen = view.findViewById(R.id.produk_diskon_persen)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_home_carousel, viewGroup, false)

        return DaftarProdukRVAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val produkId: Int = dataSet[position].id_produk
        val produkGambar: String = dataSet[position].gambar!!

        viewHolder.produk_judul.text = dataSet[position].nama_produk
        viewHolder.produk_harga.text = rupiah(dataSet[position].harga)

        if (dataSet[position].diskon_persen != null) {
            if (dataSet[position].diskon_persen!! > 0) {
                viewHolder.produk_diskon_persen.text = "-" + dataSet[position].diskon_persen.toString() + "%"
                viewHolder.produk_diskon_persen.visibility = View.VISIBLE
            }
        }

        Glide.with(viewHolder.itemView.context)
            .load(produkGambar)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.drawable.nophoto)
            .into(viewHolder.itemView.findViewById(R.id.produk_gambar))

        viewHolder.itemView.setOnClickListener{
            val context = viewHolder.itemView.context
            val intent = Intent(context, DetailProdukActivity::class.java)
//          intent.putExtra()
//            intent.putExtra()
//            intent.putExtra()
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = dataSet.size
}

private fun rupiah(number: Int): String {
    val localeID = Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    numberFormat.maximumFractionDigits = 0
    return numberFormat.format(number)
}