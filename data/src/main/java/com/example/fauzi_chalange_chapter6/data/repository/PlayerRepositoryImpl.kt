package com.example.fauzi_chalange_chapter6.data.repository

import com.example.fauzi_chalange_chapter6.data.datasource.PlayerLocalDataSource
import com.example.fauzi_chalange_chapter6.data.datasource.PlayerRemoteDataSource
import com.example.fauzi_chalange_chapter6.domain.repository.PlayerRepository
import com.example.fauzi_chalange_chapter6.domain.model.Player
import com.example.fauzi_chalange_chapter6.data.mapper.toPlayer
import com.example.fauzi_chalange_chapter6.data.mapper.toPlayerEntity

class PlayerRepositoryImpl (
    private val  remoteDataSource: PlayerRemoteDataSource,
    private val  localDataSource: PlayerLocalDataSource,
): PlayerRepository {

    override suspend fun fetchData(): List<Player> {
        return remoteDataSource.fetchData()
    }

    override fun storeData(data: Player) {
        localDataSource.storeDataToLocalDb(data)
    }

    override suspend fun saveFavorite(player: Player) {
        localDataSource.insertPlayer(player.toPlayerEntity())
    }

    override suspend fun loadAllClub(): List<Player> {
        return localDataSource.selectAllPlayers().toPlayer()
    }

    override suspend fun deleteClub(player: Player) {
        localDataSource.deletePlayer(player.toPlayerEntity())
    }

    override suspend fun loadClubById(id: Int): Player? {
        return localDataSource.selectPlayerById(id)?.toPlayer()
    }
}