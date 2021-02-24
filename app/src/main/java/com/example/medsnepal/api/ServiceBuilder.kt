package com.example.medsnepal.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceBuilder {

    //base url for android device
    private const val BASE_URL="http://10.0.2.2:5000/api/"
    var token:String?=null
    //network protocol like http
    private val okHttp= OkHttpClient.Builder()

    //building retrofit
    //retrofit for communicatiion between two
    private val retrofitBuilder= Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()) //
        .client(okHttp.build())

    //Create retrofit instance
    private val retrofit= retrofitBuilder.build()
    //Generic function
    fun<T>buildService(serviceType:Class<T>):T{
        return retrofit.create(serviceType)
    }

}