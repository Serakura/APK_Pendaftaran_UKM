package com.example.tbesar.api

import com.example.tbesar.model.login.login
import com.example.tbesar.model.ukm.ukm
import com.example.tbesar.model.ukm.daftarUkm
import com.example.tbesar.model.pinjam.tampilDaftar
import com.example.tbesar.model.register.register
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
    @FormUrlEncoded
    @POST("register.php")
    fun createUser(
        @Field("nim") nim:String,
        @Field("nama") nama:String,
        @Field("jenis_kelamin") jenkel:String,
        @Field("agama") agama:String,
        @Field("tempat_lahir") tempat_lahir:String,
        @Field("tanggal_lahir") tanggal_lahir:String,
        @Field("prodi") prodi:String,
        @Field("angkatan") angkatan:String,
        @Field("email") email:String,
        @Field("password") password:String
    ):Call<register>

    @FormUrlEncoded
    @POST("login.php")
    fun userLogin(
        @Field("nim") nim:String,
        @Field("password") password:String
    ):Call<login>

    @GET("tampilUkm.php")
    fun tampilUkm() :Call<List<ukm>>

    @FormUrlEncoded
    @POST("daftarUkm.php")
    fun daftarUkm(
        @Field("nim") nim: String,
        @Field("id_ukm") id_ukm: String,
        @Field("status") status: String
    ):Call<daftarUkm>

    @FormUrlEncoded
    @POST("tampilDaftar.php")
    fun tampilDaftar(
        @Field("nim") nim: String
    ): Call<List<tampilDaftar>>
}