package com.uty.clothstore.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("id_user") val id_user: Int,
    @SerializedName("username") val username: String,
    @SerializedName("nama") val nama: String,
    @SerializedName("email") val email: String,
    @SerializedName("no_telp") val no_telp: String,
    @SerializedName("alamat") val alamat: String,
    @SerializedName("password") val password: String
)
