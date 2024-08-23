package com.example.fauzi_chalange_chapter5.data.datasource.remote

import com.example.fauzi_chalange_chapter5.data.datasource.ClubRemoteDataSource
import com.example.fauzi_chalange_chapter5.data.datasource.remote.retrofit.ApiClubService
import com.example.fauzi_chalange_chapter5.data.datasource.remote.retrofit.model.toClub
import com.example.fauzi_chalange_chapter5.ui.listclub.dataclass.Club

class ClubRemoteDataSourceImpl(
    private val apiClubService: ApiClubService
) : ClubRemoteDataSource {
    override suspend fun fetchData(): List<Club> {
       return apiClubService.getClubsData().data.map { it.toClub() }
    }
}
