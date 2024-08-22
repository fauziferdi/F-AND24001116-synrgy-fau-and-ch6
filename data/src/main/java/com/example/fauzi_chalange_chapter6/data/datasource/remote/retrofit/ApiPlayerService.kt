package com.example.fauzi_chalange_chapter6.data.datasource.remote.retrofit

import com.example.fauzi_chalange_chapter6.data.datasource.remote.retrofit.model.res.ApiPlayerResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiPlayerService {
    @GET("api/v1/team/players/{id}")
    @Headers(
        "accept: application/json",
    )
    suspend fun getPlayersData(
        @Path("id") teams: String = "471",
    ): ApiPlayerResponse
}