package com.example.fauzi_chalange_chapter6.data.datasource.remote.retrofit.model.req

import com.google.gson.annotations.SerializedName

data class LoginBody(
   @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String
)