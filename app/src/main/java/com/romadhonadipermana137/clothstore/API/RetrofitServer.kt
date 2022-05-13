package com.romadhonadipermana137.clothstore.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitServer {
    companion object {
        private val baseURL: String = "https://raw.githubusercontent.com/adislice/response-sample/main/"
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