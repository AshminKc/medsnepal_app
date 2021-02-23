package com.example.medsnepal.ui

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.graphics.rotationMatrix
import androidx.datastore.preferences.protobuf.Api
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medsnepal.R
import com.example.medsnepal.adapter.ProductAdapter
import com.example.medsnepal.api.MyAPIRequest
import com.example.medsnepal.api.ServiceBuilder
import com.example.medsnepal.api.UserAPI
import com.example.medsnepal.entity.Product
import com.example.medsnepal.ui.ProductsFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    lateinit var progerssProgressDialog: ProgressDialog
    var dataList = ArrayList<Product>()
    lateinit var recyclerView: RecyclerView
    private val TAG = "HomeFragment"
    private var adapter: ProductAdapter? = null

    companion object {

        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.fragment_home, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = ProductAdapter(dataList)
        progerssProgressDialog=ProgressDialog(context)
        progerssProgressDialog.setTitle("Loading")
        progerssProgressDialog.setCancelable(false)
        progerssProgressDialog.show()

        getAllData()

        return rootView

    }

    private fun getAllData(){
        val call: Call<Product> = ServiceBuilder.getClient.productlist()
        call.enqueue(object : Callback<Product> {

            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                progerssProgressDialog.dismiss()
                dataList.addAll(response.body())
                recyclerView.adapter = ProductAdapter(dataList)
                recyclerView.adapter = adapter
                recyclerView.adapter?.notifyDataSetChanged()
                Log.d(TAG, "onResponse: ")
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                progerssProgressDialog.dismiss()
                Log.d(TAG, "onFailure: " + t.toString())
            }

        })
    }
}
