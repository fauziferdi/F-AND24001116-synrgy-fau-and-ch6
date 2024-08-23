package com.example.fauzi_chalange_chapter5.data.datasource.remote.retrofit.model

import com.example.fauzi_chalange_chapter5.data.datasource.remote.retrofit.model.res.ClubResponse
import com.example.fauzi_chalange_chapter5.ui.listclub.dataclass.Club

fun ClubResponse.toClub():Club{
    return Club(
        id = team_id,
        nama = team_name,
        image = team_logo,
    )

}