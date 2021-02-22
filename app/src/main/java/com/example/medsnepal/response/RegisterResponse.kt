package com.example.medsnepal.response

data class RegisterResponse(
        var isAdmin:String?=null,
        var message:String?=null,
        val success:Boolean?=null,
        val token:String?=null
)
