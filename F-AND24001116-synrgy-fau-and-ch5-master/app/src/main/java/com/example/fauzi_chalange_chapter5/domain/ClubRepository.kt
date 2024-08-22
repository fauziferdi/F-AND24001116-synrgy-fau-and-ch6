package com.example.fauzi_chalange_chapter5.domain
import com.example.fauzi_chalange_chapter5.ui.listclub.dataclass.Club

interface ClubRepository {

    suspend fun fetchData(): List<Club>

    fun storeData(data: Club)

    suspend fun saveFavorite(club: Club)

    suspend fun loadAllClub(): List<Club>

    suspend fun deleteClub(club: Club)

    suspend fun loadClubById(id: Int): Club?
}