package com.example.fauzi_chalange_chapter6.data.datasource.remote.retrofit.model

import com.example.fauzi_chalange_chapter6.data.datasource.remote.retrofit.model.res.PlayerResponse
import com.example.fauzi_chalange_chapter6.domain.model.Player

fun PlayerResponse.toClub():Player{
    return Player(
        id = player_id,
        nama = player_name,
        image = player_photo,
    )

}