package com.uty.clothstore.API

import com.uty.clothstore.Model.DaftarProdukModel
import com.uty.clothstore.Model.ProdukModel
import com.uty.clothstore.Model.ResponseModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET

interface APIRequestData {
    @GET("produk/tampil_semua_data.php")
    fun produk_tampil_semua_data(): Call<ResponseModel<DaftarProdukModel>>

    @GET("produk/tampil_data.php")
    fun produk_tampil_data(@Field("id_produk") id_produk: Int): Call<ResponseModel<ProdukModel>>
}