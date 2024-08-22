package com.example.fauzi_chalange_chapter6.data.datasource.local

import com.example.fauzi_chalange_chapter6.data.datasource.PlayerLocalDataSource
import com.example.fauzi_chalange_chapter6.data.datasource.local.room.PlayerDao
import com.example.fauzi_chalange_chapter6.data.datasource.local.room.PlayerEntity
import com.example.fauzi_chalange_chapter6.domain.model.Player

class PlayerLocalDataSourceImpl (
    private val playerDao : PlayerDao,
) : PlayerLocalDataSource{

    override fun storeDataToLocalDb(data: Player) {

    }

    override suspend fun selectAllPlayers(): List<PlayerEntity> {
        return playerDao.selectAllPlayers()
    }

    override suspend fun selectPlayerById(id: Int): PlayerEntity? {
        return playerDao.selectPlayerById(id)
    }

    override suspend fun insertPlayer(playerEntity: PlayerEntity) {
        playerDao.insertPlayer(playerEntity)
    }

    override suspend fun deletePlayer(playerEntity: PlayerEntity) {
        playerDao.deletePlayer(playerEntity)
    }
}