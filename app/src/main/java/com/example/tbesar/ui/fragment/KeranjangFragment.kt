package com.example.distrodeku.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tbesar.R
import com.example.tbesar.api.RetrofitClient
import com.example.tbesar.model.pinjam.tampilAdapter
import com.example.tbesar.model.pinjam.tampilDaftar
import com.example.tbesar.storage.SharedPrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class keranjangFragment : Fragment() {

    private lateinit var tampilArrayList : ArrayList<tampilDaftar>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_keranjang, container, false)
        val listView : ListView = view.findViewById(R.id.list)
        val nim = SharedPrefManager.getInstance(requireActivity()).user.nim.toString()
        RetrofitClient.instance.tampilDaftar(nim).enqueue(object : Callback<List<tampilDaftar>> {
            override fun onResponse(
                call: Call<List<tampilDaftar>>,
                response: Response<List<tampilDaftar>>
            ) {
                if(response.body() != null && response.isSuccessful()) {
                tampilArrayList = ArrayList()
                    for (i in response.body()!!.indices){
                        val pinjam = tampilDaftar(response.body()!![i]!!.singkatan_ukm,response.body()!![i]!!.nama_ukm,response.body()!![i]!!.tanggal_daftar,response.body()!![i]!!.status,
                            response.body()!![i]!!.gambar)
                        tampilArrayList.add(pinjam)
                    }
                    listView.adapter = tampilAdapter(requireActivity(),tampilArrayList)
                } else {
                    Toast.makeText(requireActivity().applicationContext,"Tidak Ada Pendaftaran Yang Diajukan!",
                        Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<tampilDaftar>>, t: Throwable) {
                Toast.makeText(requireActivity().applicationContext, t.message,Toast.LENGTH_SHORT).show()
            }
        })





        return view
    }


}