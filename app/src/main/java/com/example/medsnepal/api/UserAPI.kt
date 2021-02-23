package com.example.medsnepal.api

import com.example.medsnepal.entity.Product
import com.example.medsnepal.entity.ProductList
import com.example.medsnepal.entity.Signin
import com.example.medsnepal.entity.User
import com.example.medsnepal.response.LoginResponse
import com.example.medsnepal.response.RegisterResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

public interface UserAPI {
    // Register User
    @POST("users/register")
    suspend fun registerUser(
        @Body user: User
    ): Response<RegisterResponse>

    @FormUrlEncoded
    @POST("users/signin")
    suspend fun checkuser(
            @Field("email")email:String,
            @Field("password")password:String
    ): Response<LoginResponse>

    @POST("products/list")
    fun productlist(): Call<Product>
}