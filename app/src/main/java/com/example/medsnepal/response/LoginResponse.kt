package com.example.medsnepal.response

data class LoginResponse(
        var isAdmin:String?=null,
        var message:String?=null,
        val success:Boolean?=null,
        val token:String?=null
)
