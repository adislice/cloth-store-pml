package com.uty.clothstore.model

import com.google.gson.annotations.SerializedName

data class UserLoginModel (
    @SerializedName("id_user") val id_user: Int,
    @SerializedName("nama") val nama: String
)