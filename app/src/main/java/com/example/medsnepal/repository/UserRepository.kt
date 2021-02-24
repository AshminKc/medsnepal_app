package com.example.medsnepal.repository

import com.example.medsnepal.api.MyAPIRequest
import com.example.medsnepal.api.ServiceBuilder
import com.example.medsnepal.api.UserAPI
import com.example.medsnepal.entity.ProductList
import com.example.medsnepal.entity.Signin
import com.example.medsnepal.entity.User
import com.example.medsnepal.response.LoginResponse
import com.example.medsnepal.response.RegisterResponse

class UserRepository :

    MyAPIRequest() {
    val api = ServiceBuilder.buildService(UserAPI::class.java)
    suspend fun registerUser(user: User): RegisterResponse{
        return apiRequest {
            api.registerUser(user)
        }
    }

    suspend fun checkuser(signin: Signin): LoginResponse{
        return apiRequest {
            api.checkuser(signin)
        }
    }

   /* suspend fun productlist() : ProductList{
        return  apiRequest {
            api.productlist()
        }
    }*/
       /* suspend fun checkuser(username: String, password: String): LoginResponse {
            return apiRequest {
                myApi.checkUser(username, password)
            }
        }*/
}