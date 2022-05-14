package com.uty.clothstore.Model

import com.google.gson.annotations.SerializedName

data class ProdukModel (
    @SerializedName("id_produk") val id_produk: Int,
    @SerializedName("nama_produk") val nama_produk: String,
    @SerializedName("nama_kategori") val nama_kategori: String,
    @SerializedName("harga") val harga: Int,
    @SerializedName("stok") val stok: Int,
    @SerializedName("nama_diskon") val nama_diskon: String?,
    @SerializedName("diskon_persen") val diskon_persen: Int?,
    @SerializedName("gambar") val gambar: String?,
    @SerializedName("deskripsi") val deskripsi: String?
)