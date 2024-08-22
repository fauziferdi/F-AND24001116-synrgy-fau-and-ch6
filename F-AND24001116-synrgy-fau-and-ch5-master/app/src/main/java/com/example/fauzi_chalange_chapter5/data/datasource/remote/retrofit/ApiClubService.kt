package com.example.fauzi_chalange_chapter5.data.datasource.remote.retrofit

import com.example.fauzi_chalange_chapter5.data.datasource.remote.retrofit.model.res.ApiClubResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiClubService {
    @GET("api/v1/{teams}")
    @Headers(
        "accept: application/json",
    )
    suspend fun getClubsData(
        @Path("teams") teams: String = "teams",
    ): ApiClubResponse
}