package com.example.androidretrofiteweaterapi.utils

import com.example.androidbottomnavigationviewtest.ui.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: ApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl(Util.vaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }
}