package com.uty.clothstore.model

import com.google.gson.annotations.SerializedName

data class TambahTransaksiModel(
    @SerializedName("id_transaksi") val id_transaksi: Int
)
