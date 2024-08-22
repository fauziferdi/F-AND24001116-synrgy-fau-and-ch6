package com.example.fauzi_chalange_chapter6.domain.repository
import com.example.fauzi_chalange_chapter6.domain.model.Player

interface PlayerRepository {

    suspend fun fetchData(): List<Player>

    fun storeData(data: Player)

    suspend fun saveFavorite(player: Player)

    suspend fun loadAllClub(): List<Player>

    suspend fun deleteClub(player: Player)

    suspend fun loadClubById(id: Int): Player?
}