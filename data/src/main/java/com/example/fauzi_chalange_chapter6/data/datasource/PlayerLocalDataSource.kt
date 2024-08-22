package com.example.fauzi_chalange_chapter6.data.datasource

import com.example.fauzi_chalange_chapter6.data.datasource.local.room.PlayerEntity
import com.example.fauzi_chalange_chapter6.domain.model.Player

interface PlayerLocalDataSource {
    fun storeDataToLocalDb(data: Player)

    suspend fun insertPlayer(playerEntity: PlayerEntity)

    suspend fun deletePlayer(playerEntity: PlayerEntity)

    suspend fun selectPlayerById(id: Int): PlayerEntity?

    suspend fun selectAllPlayers():List<PlayerEntity>
}