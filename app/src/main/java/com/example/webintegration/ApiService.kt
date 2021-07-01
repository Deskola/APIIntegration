package com.example.webintegration

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit

import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


private val BASE_URL = "https://payments.ipayafrica.com/v3/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()


    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create()) //Not needed after adding Moshi converterfactory
        //.addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()


    object MarsApi {
        val retrofitService: MarsApiServiceInterface by lazy {
            retrofit.create(MarsApiServiceInterface::class.java)
        }

}