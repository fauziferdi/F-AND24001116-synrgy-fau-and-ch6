package com.example.fauzi_chalange_chapter5.data.datasource.local

import com.example.fauzi_chalange_chapter5.data.datasource.ClubLocalDataSource
import com.example.fauzi_chalange_chapter5.data.datasource.local.room.ClubDao
import com.example.fauzi_chalange_chapter5.data.datasource.local.room.ClubEntity
import com.example.fauzi_chalange_chapter5.ui.listclub.dataclass.Club

class ClubLocalDataSourceImpl (
    private val clubDao : ClubDao,
) : ClubLocalDataSource{

    override fun storeDataToLocalDb(data: Club) {

    }

    override suspend fun selectAllClubs(): List<ClubEntity> {
        return clubDao.selectAllClubs()
    }

    override suspend fun selectClubById(id: Int): ClubEntity? {
        return clubDao.selectClubById(id)
    }

    override suspend fun insertClub(clubEntity: ClubEntity) {
        clubDao.insertClub(clubEntity)
    }

    override suspend fun deleteClub(clubEntity: ClubEntity) {
        clubDao.deleteClub(clubEntity)
    }
}