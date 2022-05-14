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
import com.uty.clothstore.LoginActivity
import com.uty.clothstore.R
import com.uty.clothstore.model.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class HomeRVAdapter(private val dataSet: ArrayList<HomeRVModel>) :
    RecyclerView.Adapter<HomeRVAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val produk_judul: TextView
        val produk_harga: TextView
        val produk_gambar: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            produk_judul = view.findViewById(R.id.produk_judul)
            produk_harga = view.findViewById(R.id.produk_harga)
            produk_gambar = view.findViewById(R.id.produk_gambar)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_home_carousel, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val produkGambar: String = dataSet[position].produkGambar

        viewHolder.produk_judul.text = dataSet[position].produkJudul
        viewHolder.produk_harga.text = rupiah(dataSet[position].produkHarga)

        Glide.with(viewHolder.itemView.context)
            .load(produkGambar)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.drawable.nophoto)
            .into(viewHolder.itemView.findViewById(R.id.produk_gambar))

        viewHolder.itemView.setOnClickListener{
            val context = viewHolder.itemView.context
            val intent = Intent(context, LoginActivity::class.java)
//            intent.putExtra()
//            intent.putExtra()
//            intent.putExtra()
            context.startActivity(intent)
        }

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