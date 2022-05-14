package com.uty.clothstore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fm = requireActivity().supportFragmentManager
//        val btn = view.findViewById<Button>(R.id.tombol)
//        val promo = view.findViewById<TextView>(R.id.promo_code)
//
//        btn.setOnClickListener {
//            promo.text = "asasas"
//            val fragme = HomeFragment()
//            fm.beginTransaction()
//                .replace(R.id.content_frame, fragme)
//                .commit()
//        }
    }
}