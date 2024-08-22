package com.example.fauzi_chalange_chapter5.data.datasource

import com.example.fauzi_chalange_chapter5.data.datasource.local.room.ClubEntity
import com.example.fauzi_chalange_chapter5.ui.listclub.dataclass.Club

interface ClubLocalDataSource {
    fun storeDataToLocalDb(data: Club)

    suspend fun insertClub(clubEntity: ClubEntity)

    suspend fun deleteClub(clubEntity: ClubEntity)

    suspend fun selectClubById(id: Int): ClubEntity?

    suspend fun selectAllClubs():List<ClubEntity>
}