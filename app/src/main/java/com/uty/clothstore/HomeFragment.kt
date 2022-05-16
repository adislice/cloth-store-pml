package com.uty.clothstore

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uty.clothstore.adapter.HomeRVAdapter
import com.uty.clothstore.model.HomeRVModel

private const val TAG = "HomeFragment"

class HomeFragment : Fragment() {

    private var produkList = ArrayList<HomeRVModel>()
    private var produkView: RecyclerView? = null
    private var produkAdapter: RecyclerView.Adapter<*>? = null
    private var produkViewManager: GridLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        produkList.add(
            HomeRVModel(
                "キャップ HIROSHIMA VTS",
                200000,
                "https://ksufmvei.sirv.com/cloth-store/m62335136641_1.jpg"))
        produkList.add(
            HomeRVModel(
                "トレンチコート (帝人)グリーン　　新品未使用",
                1000000,
                "https://ksufmvei.sirv.com/cloth-store/m11717748462_1.jpg"))
        produkList.add(
            HomeRVModel(
                "トップス",
                450000,
                "https://ksufmvei.sirv.com/cloth-store/m56266346169_1.jpg"))
        produkList.add(
            HomeRVModel(
                "【FRAGILE】ジップアップニット　セーター　サイズ38",
                300000,
                "https://ksufmvei.sirv.com/cloth-store/m60346299519_1.jpg"))
        produkAdapter = HomeRVAdapter(produkList)
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
        produkView = view.findViewById(R.id.home_rv_produk)
        produkView?.apply {
            this.setHasFixedSize(true)
            adapter = produkAdapter
            layoutManager = produkViewManager
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        produkView?.adapter = null
        produkView = null
        produkViewManager = null
    }

}