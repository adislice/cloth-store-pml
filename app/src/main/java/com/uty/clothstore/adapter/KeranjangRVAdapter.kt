package com.uty.clothstore.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.uty.clothstore.DetailProdukActivity
import com.uty.clothstore.R
import com.uty.clothstore.model.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class KeranjangRVAdapter(private val dataSet: ArrayList<KeranjangRVModel>) :
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
        val produkGambar: String = dataSet[position].produkGambar

        viewHolder.keranjangProdukJudul.text = dataSet[position].produkJudul
        viewHolder.keranjangProdukHarga.text = rupiah(dataSet[position].produkHarga)
        viewHolder.keranjangProdukJumlah.text = dataSet[position].produkJumlah.toString()

        Glide.with(viewHolder.itemView.context)
            .load(produkGambar)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.drawable.nophoto)
            .into(viewHolder.itemView.findViewById(R.id.keranjang_gambar_produk))

//        viewHolder.itemView.setOnClickListener{
//            val context = viewHolder.itemView.context
//            val intent = Intent(context, DetailProdukActivity::class.java)
//            intent.putExtra()
//            intent.putExtra()
//            intent.putExtra()
//            context.startActivity(intent)
//        }

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