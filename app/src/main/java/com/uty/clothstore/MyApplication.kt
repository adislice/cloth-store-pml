package com.uty.clothstore

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.uty.clothstore.model.KeranjangRVModel
import java.text.NumberFormat
import java.util.*

class MyApplication: Application() {

    companion object {
        var id_user = 1

        fun addToCart(context: Context, idProduk: Int, qty: Int, hargaDiskon: Double) {
            var ada = false
            var list = ArrayList<KeranjangRVModel>()
            val gson = Gson()
            val sharedPref =  context.getSharedPreferences("app_data", Context.MODE_PRIVATE)
            // cek dulu
            if (sharedPref.contains("keranjang")){
                val json2 = sharedPref.getString("keranjang", null)
                val type = object : TypeToken<ArrayList<KeranjangRVModel>>() {}.type
                list = gson.fromJson<ArrayList<KeranjangRVModel>>(json2, type)
                for (i in list.indices) {
                    if (list[i].produkId == idProduk) {
                        list[i].produkQty = qty
                        list[i].produkHargaDiskon = hargaDiskon
                        ada = true
                    }
                }
            }
            if (!ada) {
                list.add(KeranjangRVModel(idProduk, qty, hargaDiskon))
            }
            val json = gson.toJson(list)//converting list to Json
            with(sharedPref.edit()) {
                putString("keranjang", json)
                apply()
            }
            Toast.makeText(
                context,
                "Item ditambahkan ke keranjang",
                Toast.LENGTH_LONG
            ).show()
        }

        fun delFromCart(context: Context, idProduk: Int){
            var list = ArrayList<KeranjangRVModel>()
            val gson = Gson()
            val sharedPref =  context.getSharedPreferences("app_data", Context.MODE_PRIVATE)
            if (sharedPref.contains("keranjang")){
                val json2 = sharedPref.getString("keranjang", null)
                val type = object : TypeToken<ArrayList<KeranjangRVModel>>() {}.type
                list = gson.fromJson<ArrayList<KeranjangRVModel>>(json2, type)
                for (i in 0 until list.size) {
                    if (list[i].produkId == idProduk) {
                        list.removeAt(i)
                        break
                    }
                }
                val json = gson.toJson(list)//converting list to Json
                with(sharedPref.edit()) {
                    putString("keranjang", json)
                    apply()
                }
                Toast.makeText(
                    context,
                    "Item dihapus",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        fun getSemuaKeranjang(context: Context): ArrayList<KeranjangRVModel>? {
            var list = ArrayList<KeranjangRVModel>()
            val gson = Gson()
            val sharedPref =  context.getSharedPreferences("app_data", Context.MODE_PRIVATE)
            if (sharedPref.contains("keranjang")){
                val json2 = sharedPref.getString("keranjang", null)
                val type = object : TypeToken<ArrayList<KeranjangRVModel>>() {}.type
                return gson.fromJson<ArrayList<KeranjangRVModel>>(json2, type)
            }
            return list
        }
        fun bersihkanKeranjang(context: Context){
            var list = ArrayList<KeranjangRVModel>()
            val gson = Gson()
            val sharedPref =  context.getSharedPreferences("app_data", Context.MODE_PRIVATE)
            // cek dulu

            val json2 = sharedPref.getString("keranjang", null)
            val type = object : TypeToken<ArrayList<KeranjangRVModel>>() {}.type
            list = gson.fromJson<ArrayList<KeranjangRVModel>>(json2, type)
            list.clear()

            val json = gson.toJson(list)//converting list to Json
            with(sharedPref.edit()) {
                putString("keranjang", json)
                apply()
            }
        }

        fun rupiah(number: Int): String {
            val localeID = Locale("in", "ID")
            val numberFormat = NumberFormat.getCurrencyInstance(localeID)
            numberFormat.maximumFractionDigits = 0
            return numberFormat.format(number)
        }
    }
}