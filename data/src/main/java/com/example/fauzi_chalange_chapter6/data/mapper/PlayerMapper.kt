package com.example.fauzi_chalange_chapter6.data.mapper

import com.example.fauzi_chalange_chapter6.data.datasource.local.room.PlayerEntity
import com.example.fauzi_chalange_chapter6.domain.model.Player

fun Player.toPlayerEntity(): PlayerEntity {
    return PlayerEntity(
        nama = nama,
        image = image,
        id = id,
    )
}

fun PlayerEntity.toPlayer(): Player {
    return Player(
        nama = nama,
        image = image,
        id = id,
    )
}

fun List<PlayerEntity>.toPlayer(): List<Player> {
    return map { clubEntity -> clubEntity.toPlayer() }
}