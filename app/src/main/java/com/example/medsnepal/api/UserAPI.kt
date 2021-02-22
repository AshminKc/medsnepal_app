package com.example.medsnepal.api

import com.example.medsnepal.entity.User
import com.example.medsnepal.response.RegisterResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

public interface UserAPI {
    // Register User
    @POST("users/register")
    suspend fun registerUser(
        @Body user: User
    ): Response<RegisterResponse>
}