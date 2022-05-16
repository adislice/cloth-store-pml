package com.uty.clothstore.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitServer {
    companion object {
        private val baseURL: String = "https://store.elfreid.my.id/"
        private var retro: Retrofit? = null

        fun getConnection(): Retrofit? {
            if (retro == null) {
                retro = Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retro
        }
    }
}