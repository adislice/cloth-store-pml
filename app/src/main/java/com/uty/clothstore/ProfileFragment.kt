package com.uty.clothstore

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.uty.clothstore.API.APIRequestData
import com.uty.clothstore.API.RetrofitServer
import com.uty.clothstore.model.ResponseModel
import com.uty.clothstore.model.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {
    private lateinit var tvNama: TextView
    private lateinit var tvUsername: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvAlamat: TextView
    private lateinit var tvNoTelp: TextView
    private lateinit var loadingProfil: View
    private lateinit var btnLogout: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val fm = requireActivity().supportFragmentManager
        tvNama = view.findViewById(R.id.profile_nama)
        tvUsername = view.findViewById(R.id.profile_username)
        tvEmail = view.findViewById(R.id.profile_email)
        tvNoTelp = view.findViewById(R.id.profile_no_telp)
        tvAlamat = view.findViewById(R.id.profile_alamat)
        loadingProfil = view.findViewById(R.id.loading_profil)
        btnLogout = view.findViewById(R.id.profile_logout)
        val sharedPref =  requireContext().getSharedPreferences("app_data", Context.MODE_PRIVATE)



        lihatProfil()
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
        btnLogout.setOnClickListener {
            loadingProfil.visibility = View.VISIBLE
            sharedPref.edit().clear().apply()
            loadingProfil.visibility = View.GONE
            val i = Intent(requireContext(), LandingActivity::class.java)
            startActivity(i)
            requireActivity().finish()
        }
    }

    private fun lihatProfil(){
        tvNama.visibility = View.GONE
        tvUsername.visibility = View.GONE
        tvEmail.visibility = View.GONE
        tvNoTelp.visibility = View.GONE
        tvAlamat.visibility = View.GONE
        loadingProfil.visibility = View.VISIBLE
        val ardData = RetrofitServer.getConnection()!!.create(APIRequestData::class.java)
        val userData = ardData.user_tampil_data(MyApplication.id_user)
        userData.enqueue(object : Callback<ResponseModel<UserModel>> {
            override fun onResponse(
                call: Call<ResponseModel<UserModel>>,
                response: Response<ResponseModel<UserModel>>
            ) {
                when(response.code()){
                    200 -> {
                        tvNama.text = response.body()!!.records!![0].nama
                        tvUsername.text = response.body()!!.records!![0].username
                        tvEmail.text = response.body()!!.records!![0].email
                        tvNoTelp.text = response.body()!!.records!![0].no_telp
                        tvAlamat.text = response.body()!!.records!![0].alamat
                    }
                }
                tvNama.visibility = View.VISIBLE
                tvUsername.visibility = View.VISIBLE
                tvEmail.visibility = View.VISIBLE
                tvNoTelp.visibility = View.VISIBLE
                tvAlamat.visibility = View.VISIBLE
                loadingProfil.visibility = View.GONE
            }

            override fun onFailure(call: Call<ResponseModel<UserModel>>, t: Throwable) {
                Toast.makeText(view?.context, "Gagal meload profil", Toast.LENGTH_LONG).show()
                loadingProfil.visibility = View.GONE
            }

        })
    }
}