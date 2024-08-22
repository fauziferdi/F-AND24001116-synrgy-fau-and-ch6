package com.example.fauzi_chalange_chapter6.data.datasource.remote.retrofit.model.res

import com.google.gson.annotations.SerializedName

data class PlayerResponse (
    @SerializedName("player_id")
    val player_id: Int,
    @SerializedName("player_name")
    val player_name: String,
    @SerializedName("player_photo")
    val player_photo: String,
)