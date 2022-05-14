package com.uty.clothstore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

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

        val fm = requireActivity().supportFragmentManager
        val btn = view.findViewById<Button>(R.id.tombol)
        val promo = view.findViewById<TextView>(R.id.promo_code)

        btn.setOnClickListener {
            promo.text = "asasas"
            val fragme = HomeFragment()
            fm.beginTransaction()
                .replace(R.id.content_frame, fragme)
                .commit()

//            (activity as MainActivity).getBottomNav().menu.findItem(R.id.menu_home).isChecked = true

        }
        Glide.with(requireContext())
            .load("https://pbs.twimg.com/media/FLZUG3FXwAUN6jP.jpg")
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.color.white)
            .into(view.findViewById(R.id.iv_contoh))
    }
}