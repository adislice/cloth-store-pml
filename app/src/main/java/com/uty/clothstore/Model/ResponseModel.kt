package com.uty.clothstore.Model

import com.google.gson.annotations.SerializedName

data class ResponseModel<T>(
    @SerializedName("records") val records: ArrayList<T>?,
    @SerializedName("message") val message: String?
)
