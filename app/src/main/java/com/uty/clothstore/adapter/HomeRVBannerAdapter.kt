package com.uty.clothstore.adapter

import android.content.Intent
import android.net.Uri
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

class HomeRVBannerAdapter(private val dataSet: ArrayList<HomeRVBannerModel>) :
    RecyclerView.Adapter<HomeRVBannerAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bannerGambar: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            bannerGambar = view.findViewById(R.id.home_banner)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_home_banner, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val produkGambar: String = dataSet[position].fotoBanner
        val Link: String = dataSet[position].urlLink

        Glide.with(viewHolder.itemView.context)
            .load(produkGambar)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.drawable.nophoto)
            .into(viewHolder.itemView.findViewById(R.id.home_banner))

        viewHolder.itemView.setOnClickListener{
            val context = viewHolder.itemView.context
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(Link)
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