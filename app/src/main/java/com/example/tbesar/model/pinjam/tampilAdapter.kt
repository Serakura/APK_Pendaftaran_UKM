package com.example.tbesar.model.pinjam

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

class tampilAdapter(private val context : Activity, private val arrayList : ArrayList<tampilDaftar>) : ArrayAdapter<tampilDaftar>(context, R.layout.list_daftar,arrayList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.list_daftar,null)

        val imageView : ImageView = view.findViewById(R.id.daftar_pict)
        val nama : TextView = view.findViewById(R.id.daftar_nama)
        val singkatan : TextView = view.findViewById(R.id.daftar_singkatan)
        val tanggal : TextView = view.findViewById(R.id.daftar_tanggal)
        val status : TextView = view.findViewById(R.id.daftar_status)

        Glide.with(context).load(arrayList[position].gambar).thumbnail(0.5f).diskCacheStrategy(
            DiskCacheStrategy.ALL).into(imageView)


        nama.text = arrayList[position].nama_ukm
        singkatan.text = arrayList[position].singkatan_ukm
        tanggal.text = arrayList[position].tanggal_daftar
        status.text = arrayList[position].status

        return view
    }
}