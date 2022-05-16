package com.uty.clothstore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uty.clothstore.API.APIRequestData
import com.uty.clothstore.API.RetrofitServer
import com.uty.clothstore.adapter.DaftarProdukRVAdapter
import com.uty.clothstore.adapter.HomeRVAdapter
import com.uty.clothstore.model.DaftarProdukModel
import com.uty.clothstore.model.HomeRVModel
import com.uty.clothstore.model.ResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class DaftarProdukFragment : Fragment() {
    private var produkList = ArrayList<DaftarProdukModel>()
    private var produkView: RecyclerView? = null
    private var produkAdapter: RecyclerView.Adapter<*>? = null
    private var produkViewManager: GridLayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_produk, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        produkViewManager = GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
        produkView = view.findViewById(R.id.rv_daftar_produk)

        ambilDataDaftarProduk()
    }

    fun ambilDataDaftarProduk() {
        val ardData: APIRequestData = RetrofitServer.getConnection()!!.create(APIRequestData::class.java)
        val tampilData: Call<ResponseModel<DaftarProdukModel>> = ardData.produk_tampil_semua_data()
        tampilData.enqueue(object: Callback<ResponseModel<DaftarProdukModel>> {
            override fun onResponse(
                call: Call<ResponseModel<DaftarProdukModel>>,
                response: Response<ResponseModel<DaftarProdukModel>>
            ) {
                when (response.code()) {
                    200 -> {
                        produkList = response.body()!!.records!!
                        produkAdapter = DaftarProdukRVAdapter(produkList)

                        produkView?.apply {
                            this.setHasFixedSize(true)
                            adapter = produkAdapter
                            layoutManager = produkViewManager
                        }
                    }
                    404 -> Toast.makeText(requireContext(), response.raw().request().url().toString(), Toast.LENGTH_LONG).show()

                }
            }

            override fun onFailure(call: Call<ResponseModel<DaftarProdukModel>>, t: Throwable) {
                Toast.makeText(requireContext(), "Terjadi kesalahan saat menghubungkan ke server!", Toast.LENGTH_LONG).show()
            }

        })
    }
}