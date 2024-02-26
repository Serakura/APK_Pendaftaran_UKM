package com.example.tbesar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.tbesar.api.RetrofitClient
import com.example.tbesar.model.ukm.daftarUkm
import com.example.tbesar.storage.SharedPrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UkmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ukm)

        val nama = findViewById<TextView>(R.id.detil_nama)
        val singkatan = findViewById<TextView>(R.id.detil_singkatan)
        val deskripsi = findViewById<TextView>(R.id.detil_deskripsi)
        val gambar = findViewById<ImageView>(R.id.detil_gambar)
//        val warna = findViewById<TextView>(R.id.motor_warna)
//        val tahun = findViewById<TextView>(R.id.motor_thn)

//        val jenis = findViewById<TextView>(R.id.motor_jenis)
        val id_ukm = intent.getStringExtra("id_ukm").toString()
        val nm = intent.getStringExtra("nama_ukm")
        val skt = intent.getStringExtra("singkatan_ukm")
        val dsk = intent.getStringExtra("deskripsi")
        val gmbr = intent.getStringExtra("gambar")
        val status = "Seleksi";
        val nim = SharedPrefManager.getInstance(this).user.nim.toString()
//        val wrn = intent.getStringExtra("warna")
//        val thn = intent.getStringExtra("tahun")

//        val jns = intent.getStringExtra("jenis")

        nama.text = nm
        singkatan.text = skt
        deskripsi.text = dsk
        Glide.with(this).load(gmbr).thumbnail(0.5f).diskCacheStrategy(
            DiskCacheStrategy.ALL).into(gambar)

        val btn_back = findViewById<TextView>(R.id.btn_kembali)
        btn_back.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        val btn_pesan = findViewById<Button>(R.id.btn_daftar)
        btn_pesan.setOnClickListener{

            RetrofitClient.instance.daftarUkm(nim,id_ukm,status).enqueue(object:
                Callback<daftarUkm> {
                override fun onResponse(call: Call<daftarUkm>, response: Response<daftarUkm>) {
                    if(response.body() != null && response.isSuccessful() && response.body()?.result_code == true){
                        Toast.makeText(applicationContext, response.body()?.status, Toast.LENGTH_LONG).show()

                        val intent = Intent(this@UkmActivity,MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(applicationContext, response.body()?.status, Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<daftarUkm>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

            })
        }
    }
}