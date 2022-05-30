package com.uty.clothstore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
        tvNama = view.findViewById(R.id.profile_nama)
        tvUsername = view.findViewById(R.id.profile_username)
        tvEmail = view.findViewById(R.id.profile_email)
        tvNoTelp = view.findViewById(R.id.profile_no_telp)
        tvAlamat = view.findViewById(R.id.profile_alamat)
        lihat_profil()
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

    fun lihat_profil(){
        var ardData = RetrofitServer.getConnection()!!.create(APIRequestData::class.java)
        var userData = ardData.user_tampil_data(MyApplication.id_user)
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
            }

            override fun onFailure(call: Call<ResponseModel<UserModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}