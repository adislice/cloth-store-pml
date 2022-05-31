package com.uty.clothstore.API

import com.uty.clothstore.model.*
import retrofit2.Call
import retrofit2.http.*

interface APIRequestData {

    // Produk
    // * Tampil semua data produk
    @GET("produk/tampil_semua_data.php")
    fun produk_tampil_semua_data(): Call<ResponseModel<DaftarProdukModel>>

    // * Tampil 1 data produk
    @GET("produk/tampil_data.php")
    fun produk_tampil_data(@Query("id", encoded = true) id_produk: Int): Call<ResponseModel<ProdukModel>>

    // User
    // * Login user
    @GET("user/login.php")
    fun user_login(@Query("username", encoded = true) username: String,
                   @Query("password", encoded = true) password: String
    ): Call<ResponseModel<UserLoginModel>>

    // * Register user
    @POST("user/register.php")
    @FormUrlEncoded
    fun user_register(@Field("username") username: String,
                      @Field("nama") nama: String,
                      @Field("email") email: String,
                      @Field("no_telp") no_telp: String,
                      @Field("alamat") alamat: String,
                      @Field("password") password: String
    ): Call<ResponseModel<String>>

    // * Tampil 1 data user
    @GET("user/tampil_data.php")
    fun user_tampil_data(@Query("id", encoded=true) id: Int): Call<ResponseModel<UserModel>>

    // Transaksi
    // * Tambah transaksi
    @POST("transaksi/tambah_transaksi.php")
    fun tambah_transaksi(@Field("id_user") id_user: Int,
                              @Field("penerima") penerima: String,
                              @Field("alamat_penerima") alamat_penerima: String,
                              @Field("no_telp_penerima") no_telp_penerima: String,
                              @Field("status") status: String
    ): Call<ResponseModel<TambahTransaksiModel>>

    // * Tambah produk ke transaksi
    @POST("transaksi/tambah_detail_transaksi.php")
    fun tambah_detail_transaksi(@Field("id_transaksi") id_transaksi: Int,
                                @Field("id_produk") id_produk: Int,
                                @Field("qty") qty: Int
    ): Call<ResponseModel<*>>

    // Produk per kategori
    @GET("produk/tampil_semua_data.php")
    fun tampil_semua_data_by_kategori(@Query("kategori", encoded = true) id_kategori: Int
    ): Call<ResponseModel<DaftarProdukModel>>
}