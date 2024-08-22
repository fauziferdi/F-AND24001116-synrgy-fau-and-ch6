package com.example.fauzi_chalange_chapter6.data.datasource.remote.retrofit

import com.example.fauzi_chalange_chapter6.data.datasource.remote.retrofit.model.req.LoginBody
import com.example.fauzi_chalange_chapter6.data.datasource.remote.retrofit.model.res.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ReqresService {

    @POST("login")
    suspend fun login(
        @Body loginBody: LoginBody,
    ): LoginResponse

}