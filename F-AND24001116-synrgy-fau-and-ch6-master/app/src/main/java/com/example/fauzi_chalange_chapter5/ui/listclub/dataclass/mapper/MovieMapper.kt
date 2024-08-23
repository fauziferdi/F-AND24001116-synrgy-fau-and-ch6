package com.example.fauzi_chalange_chapter5.ui.listclub.dataclass.mapper

import com.example.fauzi_chalange_chapter5.data.datasource.local.room.ClubEntity
import com.example.fauzi_chalange_chapter5.ui.listclub.dataclass.Club

fun Club.toClubEntity(): ClubEntity {
    return ClubEntity(
        nama = nama,
        image = image,
        id = id,
    )
}

fun ClubEntity.toClub(): Club {
    return Club(
        nama = nama,
        image = image,
        id = id,
    )
}

fun List<ClubEntity>.toClub(): List<Club> {
    return map { clubEntity -> clubEntity.toClub() }
}