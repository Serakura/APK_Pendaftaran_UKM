package com.example.tbesar.model.ukm

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.tbesar.R

class ukmAdapter(private val context : Activity, private val arrayList : ArrayList<ukm>) : ArrayAdapter<ukm>(context, R.layout.list_item,arrayList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.list_item,null)

        val imageView : ImageView = view.findViewById(R.id.ukm_pict)
        val nama : TextView = view.findViewById(R.id.ukm_nama)
        val singkatan : TextView = view.findViewById(R.id.ukm_singkatan)

        Glide.with(context).load(arrayList[position].gambar).thumbnail(0.5f).diskCacheStrategy(
            DiskCacheStrategy.ALL).into(imageView)


        nama.text = arrayList[position].nama_ukm
        singkatan.text = arrayList[position].singkatan_ukm


        return view
    }
}