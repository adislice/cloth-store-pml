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
import com.uty.clothstore.adapter.HomeRVBannerAdapter
import com.uty.clothstore.model.DaftarProdukModel
import com.uty.clothstore.model.HomeRVBannerModel
import com.uty.clothstore.model.ResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private var produkList = ArrayList<DaftarProdukModel>()
    private var produkView: RecyclerView? = null
    private var produkAdapter: RecyclerView.Adapter<*>? = null
    private var produkViewManager: GridLayoutManager? = null

    private var bannerList = ArrayList<HomeRVBannerModel>()
    private var bannerView: RecyclerView? = null
    private var bannerAdapter: RecyclerView.Adapter<*>? = null
    private var bannerViewManager: LinearLayoutManager? = null

    private var busanaWanita: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        produkAdapter = DaftarProdukRVAdapter(produkList)

        bannerList.add(
            HomeRVBannerModel(
                "https://ksufmvei.sirv.com/cloth-store/banner/banner_1.jpg"
            )
        )
        bannerList.add(
            HomeRVBannerModel(
                "https://ksufmvei.sirv.com/cloth-store/banner/banner_2.jpg"
            )
        )
        bannerAdapter = HomeRVBannerAdapter(bannerList)

        busanaWanita = 1
        ambilDataProdukPerKategori(busanaWanita)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        produkViewManager = GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
        bannerViewManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)
        produkView = view.findViewById(R.id.home_rv_produk)
        bannerView = view.findViewById(R.id.home_rv_banner)
        produkView?.apply {
            this.setHasFixedSize(true)
            adapter = produkAdapter
            layoutManager = produkViewManager
        }

        bannerView?.apply {
            this.setHasFixedSize(true)
            adapter = bannerAdapter
            layoutManager = bannerViewManager
        }
        Toast.makeText(requireActivity(), MyApplication.id_user.toString(), Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        produkView?.adapter = null
        produkView = null
        produkViewManager = null
        bannerView?.adapter = null
        bannerView = null
        bannerViewManager = null
    }

    fun ambilDataProdukPerKategori(id_kategori:Int) {
        val ardData: APIRequestData = RetrofitServer.getConnection()!!.create(APIRequestData::class.java)
        val tampilData: Call<ResponseModel<DaftarProdukModel>> = ardData.tampil_semua_data_by_kategori(id_kategori)
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