package com.example.tbesar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.isEmpty
import com.example.tbesar.api.RetrofitClient
import com.example.tbesar.model.register.register
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val et_nim = findViewById<EditText>(R.id.txt_reg_nim)
        val et_name = findViewById<EditText>(R.id.txt_reg_nama)
        val rg_jenkel = findViewById<RadioGroup>(R.id.jenkel_Grup)
        val et_agama = findViewById<EditText>(R.id.txt_reg_agama)
        val et_tempatlahir = findViewById<EditText>(R.id.txt_reg_tempat)
        val et_tanggallahir = findViewById<EditText>(R.id.txt_reg_tenggal)
        val et_prodi = findViewById<EditText>(R.id.txt_reg_prodi)
        val et_angkatan = findViewById<EditText>(R.id.txt_reg_angkatan)
        val et_email = findViewById<EditText>(R.id.txt_reg_email)
        val et_password = findViewById<EditText>(R.id.txt_reg_password)


        val buttonRegister = findViewById<Button>(R.id.button_register)
        buttonRegister.setOnClickListener{
            val nim =et_nim.text.toString().trim()
            val nama = et_name.text.toString().trim()

            val agama = et_agama.text.toString().trim()
            val tempatlahir = et_tempatlahir.text.toString().trim()
            val tanggallahir = et_tanggallahir.text.toString().trim()
            val prodi = et_prodi.text.toString().trim()
            val angkatan = et_angkatan.text.toString().trim()
            val email = et_email.text.toString().trim()
            val password = et_password.text.toString().trim()
            val jk = rg_jenkel.checkedRadioButtonId
            val jkl = findViewById<RadioButton>(jk)
            val jenkel = jkl.text.toString().trim()

            if(nama.isEmpty()){
                et_name.error="Nama required"
                et_name.requestFocus()
                return@setOnClickListener
            }
            if(nim.isEmpty()){
                et_nim.error="Nomor Induk Mahasiswa required"
                et_nim.requestFocus()
                return@setOnClickListener
            }
            if(agama.isEmpty()){
                et_agama.error="Agama required"
                et_agama.requestFocus()
                return@setOnClickListener
            }

            if(rg_jenkel.checkedRadioButtonId == -1){
                jkl.setError("Select Item!")
            }

            if(tempatlahir.isEmpty()){
                et_tempatlahir.error="Tempat lahir required"
                et_tempatlahir.requestFocus()
                return@setOnClickListener
            }
            if(tanggallahir.isEmpty()){
                et_tanggallahir.error="Tanggal lahir required"
                et_tanggallahir.requestFocus()
                return@setOnClickListener
            }
            if(prodi.isEmpty()){
                et_prodi.error="Program Studi required"
                et_prodi.requestFocus()
                return@setOnClickListener
            }
            if(angkatan.isEmpty()){
                et_angkatan.error="Angkatan required"
                et_angkatan.requestFocus()
                return@setOnClickListener
            }
            if(email.isEmpty()){
                et_email.error="Email required"
                et_email.requestFocus()
                return@setOnClickListener
            }
            if(password.isEmpty()){
                et_password.error="Password required"
                et_password.requestFocus()
                return@setOnClickListener
            }

            RetrofitClient.instance.createUser(nim,nama,jenkel,agama,tempatlahir,tanggallahir,prodi,angkatan,email,password)
                .enqueue(object: Callback<register>{
                override fun onResponse(call: Call<register>, response: Response<register>) {
                    if(response.body() != null && response.isSuccessful() && response.body()?.result_code == true){
                    Toast.makeText(applicationContext, response.body()?.status, Toast.LENGTH_LONG).show()

                    val intent = Intent(this@RegisterActivity,LoginActivity::class.java)
                    startActivity(intent)

                    } else {
                        Toast.makeText(applicationContext, response.body()?.status, Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<register>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

            })

        }

    }


}