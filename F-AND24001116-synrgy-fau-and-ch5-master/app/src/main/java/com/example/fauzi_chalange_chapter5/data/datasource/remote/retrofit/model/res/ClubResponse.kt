package com.example.fauzi_chalange_chapter5.data.datasource.remote.retrofit.model.res

import com.google.gson.annotations.SerializedName

data class ClubResponse (
    @SerializedName("team_id")
    val team_id: Int,
    @SerializedName("team_name")
    val team_name: String,
    @SerializedName("team_logo")
    val team_logo: String,
)