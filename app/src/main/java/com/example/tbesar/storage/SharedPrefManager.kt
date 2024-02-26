package com.example.tbesar.storage

import android.content.Context
import com.example.tbesar.model.login.loginData


class SharedPrefManager private constructor(private val mCtx: Context) {

    val isLoggedIn: Boolean
        get() {
            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getString("nim", null) != null
        }

    val user: loginData
        get() {
            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return loginData(
                sharedPreferences.getString("nim", null),
                sharedPreferences.getString("nama", null),
                sharedPreferences.getString("jenis_kelamin", null),
                sharedPreferences.getString("agama", null),
                sharedPreferences.getString("tempat_lahir", null),
                sharedPreferences.getString("tanggal_lahir", null),
                sharedPreferences.getString("prodi", null),
                sharedPreferences.getString("angkatan", null),
                sharedPreferences.getString("email", null),
            )
        }


    fun saveUser(user: loginData) {

        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("nim", user.nim)
        editor.putString("nama", user.nama)
        editor.putString("jenis_kelamin", user.jenkel)
        editor.putString("agama", user.agama)
        editor.putString("tempat_lahir", user.tempat_lahir)
        editor.putString("tanggal_lahir", user.tanggal_lahir)
        editor.putString("prodi", user.prodi)
        editor.putString("angkatan", user.angkatan)
        editor.putString("email", user.email)

        editor.apply()

    }

    fun clear() {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        private val SHARED_PREF_NAME = "my_shared_preff"
        private var mInstance: SharedPrefManager? = null
        @Synchronized
        fun getInstance(mCtx: Context): SharedPrefManager {
            if (mInstance == null) {
                mInstance = SharedPrefManager(mCtx)
            }
            return mInstance as SharedPrefManager
        }
    }

}