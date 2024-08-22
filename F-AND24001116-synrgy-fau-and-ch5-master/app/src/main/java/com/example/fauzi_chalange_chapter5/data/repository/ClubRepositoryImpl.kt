package com.example.fauzi_chalange_chapter5.data.repository

import com.example.fauzi_chalange_chapter5.data.datasource.ClubLocalDataSource
import com.example.fauzi_chalange_chapter5.data.datasource.ClubRemoteDataSource
import com.example.fauzi_chalange_chapter5.domain.ClubRepository
import com.example.fauzi_chalange_chapter5.ui.listclub.dataclass.Club
import com.example.fauzi_chalange_chapter5.ui.listclub.dataclass.mapper.toClub
import com.example.fauzi_chalange_chapter5.ui.listclub.dataclass.mapper.toClubEntity

class ClubRepositoryImpl (
    private val  remoteDataSource: ClubRemoteDataSource,
    private val  localDataSource: ClubLocalDataSource,
): ClubRepository {

    override suspend fun fetchData(): List<Club> {
        return remoteDataSource.fetchData()
    }

    override fun storeData(data: Club) {
        localDataSource.storeDataToLocalDb(data)
    }

    override suspend fun saveFavorite(club: Club) {
        localDataSource.insertClub(club.toClubEntity())
    }

    override suspend fun loadAllClub(): List<Club> {
        return localDataSource.selectAllClubs().toClub()
    }

    override suspend fun deleteClub(club: Club) {
        localDataSource.deleteClub(club.toClubEntity())
    }

    override suspend fun loadClubById(id: Int): Club? {
        return localDataSource.selectClubById(id)?.toClub()
    }
}