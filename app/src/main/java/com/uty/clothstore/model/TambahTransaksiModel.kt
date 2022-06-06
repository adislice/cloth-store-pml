package com.uty.clothstore.model

import com.google.gson.annotations.SerializedName

data class TambahTransaksiModel(
    @SerializedName("id_transaksi") val id_transaksi: Int,
    @SerializedName("subtotal") val subtotal: Int,
    @SerializedName("penerima") val penerima: String,
    @SerializedName("alamat_penerima") val alamat_penerima: String,
    @SerializedName("no_telp_penerima") val no_telp_penerima: String,
    @SerializedName("metode_pembayaran") val metode_pembayaran: String,
    @SerializedName("status") val status: String,
)
