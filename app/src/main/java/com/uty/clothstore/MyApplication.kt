package com.uty.clothstore

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MyApplication: Application() {

    companion object {
        var id_user = 1

//        fun addToCart(context: Context, id_produk: Int) {
//            val gson = Gson()
//            var sharedPref =  context.getSharedPreferences("app_data", Context.MODE_PRIVATE)
//            val json = sharedPref.getString("keranjang", null)
//            val type = object : TypeToken<ArrayList<Int>>(){}.type //converting the json to list
//            return gson.fromJson(json,type)//returning the list
//            if ()
//        }
    }
}