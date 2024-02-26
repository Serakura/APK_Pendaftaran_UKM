package com.example.distrodeku.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.example.tbesar.UkmActivity
import com.example.tbesar.R
import com.example.tbesar.api.RetrofitClient
import com.example.tbesar.model.ukm.ukm
import com.example.tbesar.model.ukm.ukmAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var ukmArrayList : ArrayList<ukm>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_home, container, false)
        val listView : ListView = view.findViewById(R.id.listView)


        RetrofitClient.instance.tampilUkm().enqueue(object : Callback<List<ukm>>{
            override fun onResponse(call: Call<List<ukm>>, response: Response<List<ukm>>) {
                if(response.body() != null && response.isSuccessful()) {
                    ukmArrayList = ArrayList()
                    for (i in response.body()!!.indices){
                        val mobel = ukm(response.body()!![i]!!.id_ukm,response.body()!![i]!!.singkatan_ukm,response.body()!![i]!!.nama_ukm,response.body()!![i]!!.deskripsi,response.body()!![i]!!.gambar)
                        ukmArrayList.add(mobel)
                    }
                    listView.adapter = ukmAdapter(requireActivity(),ukmArrayList)
                    listView.setOnItemClickListener { parent, view, position, id ->
                        val id = response.body()!![position]!!.id_ukm
                        val ukm = response.body()!![position]!!.nama_ukm
                        val skt = response.body()!![position]!!.singkatan_ukm
                        val dsk = response.body()!![position]!!.deskripsi
                        val gmbr = response.body()!![position]!!.gambar

                        val i = Intent(activity,UkmActivity::class.java)
                        i.putExtra("id_ukm",id)
                        i.putExtra("nama_ukm",ukm)
                        i.putExtra("singkatan_ukm",skt)
                        i.putExtra("deskripsi",dsk)
                        i.putExtra("gambar",gmbr)
                        startActivity(i)
                    }


                }
            }

            override fun onFailure(call: Call<List<ukm>>, t: Throwable) {
                Toast.makeText(requireActivity().applicationContext,t.message,Toast.LENGTH_LONG).show()
            }

        })





        val bFilter : TextView = view.findViewById(R.id.tv_filter)
        bFilter.setOnClickListener{
            Toast.makeText(requireActivity().applicationContext,"Maaf, Masih Tahap Pengembangan!",Toast.LENGTH_LONG).show()
        }

        val search : EditText = view.findViewById(R.id.et_search)

        return view
    }
}