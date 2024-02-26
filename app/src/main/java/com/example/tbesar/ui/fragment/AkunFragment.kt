package com.example.distrodeku.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.tbesar.LoginActivity
import com.example.tbesar.MainActivity
import com.example.tbesar.R
import com.example.tbesar.storage.SharedPrefManager


class akunFragment : Fragment() {
    lateinit var s:SharedPrefManager
    lateinit var btnLogout:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_akun, container, false)
        val tvNama : TextView = view.findViewById(R.id.akun_nama)
        val tvNim : TextView = view.findViewById(R.id.akun_nim)
        val tvAgama : TextView = view.findViewById(R.id.akun_agama)
        val tvJenkel : TextView = view.findViewById(R.id.akun_jenkel)
        val tvTempat : TextView = view.findViewById(R.id.akun_tempat)
        val tvTanggal : TextView = view.findViewById(R.id.akun_tanggal)
        val tvProdi : TextView = view.findViewById(R.id.akun_prodi)
        val tvAngkatan : TextView = view.findViewById(R.id.akun_angkatan)
        val tvEmail : TextView = view.findViewById(R.id.akun_email)

        val args = this.arguments

        val namaUser = args?.get("nama")
        val nimUser = args?.get("nim")
        val agamaUser = args?.get("agama")
        val jenkelUser = args?.get("jenis_kelamin")
        val tempatUser = args?.get("tempat_lahir")
        val tanggalUser = args?.get("tanggal_lahir")
        val prodiUser = args?.get("prodi")
        val angkatanUser = args?.get("angkatan")
        val emailUser = args?.get("email")

        tvNama.text = namaUser.toString()
        tvNim.text = nimUser.toString()
        tvAgama.text = agamaUser.toString()
        tvJenkel.text = jenkelUser.toString()
        tvTempat.text = tempatUser.toString()
        tvTanggal.text = tanggalUser.toString()
        tvProdi.text = prodiUser.toString()
        tvAngkatan.text = angkatanUser.toString()
        tvEmail.text = emailUser.toString()


        s = SharedPrefManager.getInstance(requireActivity())

        btnLogout = view.findViewById(R.id.btn_logout)
        btnLogout.setOnClickListener(){
            s.clear()
            val intent = Intent(activity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }



    return view
    }

}


