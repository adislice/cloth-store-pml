package com.romadhonadipermana137.clothstore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.size
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class PromoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_promo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var fm = requireActivity().supportFragmentManager
        var btn = view.findViewById<Button>(R.id.tombol)
        var promo = view.findViewById<TextView>(R.id.promo_code)

        btn.setOnClickListener {
            promo.text = "asasas"
            var fragme = HomeFragment()
            fm.beginTransaction()
                .replace(R.id.content_frame, fragme)
                .commit()

//            (activity as MainActivity).getBottomNav().menu.findItem(R.id.menu_home).isChecked = true

        }
    }
}