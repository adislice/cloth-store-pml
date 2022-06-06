package com.uty.clothstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import java.text.NumberFormat
import java.util.*

class CheckoutActivity : AppCompatActivity() {
    private lateinit var totalBayar: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        totalBayar = findViewById(R.id.checkout_total_bayar)
        val totalan = intent.getDoubleExtra("total", 0.0)

        totalBayar.text = rupiah(totalan.toInt())
        val type = arrayOf("DANA", "OVO", "GoPay")

        val adapter = ArrayAdapter(
            this,
            R.layout.menu_payment,
            type
        )

        val editTextFilledExposedDropdown = findViewById<AutoCompleteTextView>(R.id.filled_exposed_dropdown)
        editTextFilledExposedDropdown.setAdapter(adapter)
    }
}

private fun rupiah(number: Int): String {
    val localeID = Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    numberFormat.maximumFractionDigits = 0
    return numberFormat.format(number)
}