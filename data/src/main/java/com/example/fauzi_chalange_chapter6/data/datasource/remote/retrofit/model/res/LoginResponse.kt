package com.example.fauzi_chalange_chapter6.data.datasource.remote.retrofit.model.res

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("id")
    val id: String,
    @SerializedName("logtoken")
    val logtoken: String
)