package com.uty.clothstore.model

import com.google.gson.annotations.SerializedName

data class ResponseModel<T>(
    @SerializedName("records") val records: ArrayList<T>?,
    @SerializedName("message") val message: String?
)
