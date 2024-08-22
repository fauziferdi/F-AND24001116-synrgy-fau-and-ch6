package com.example.fauzi_chalange_chapter6.data.datasource.remote

import com.example.fauzi_chalange_chapter6.data.datasource.PlayerRemoteDataSource
import com.example.fauzi_chalange_chapter6.data.datasource.remote.retrofit.ApiPlayerService
import com.example.fauzi_chalange_chapter6.data.datasource.remote.retrofit.model.toClub
import com.example.fauzi_chalange_chapter6.domain.model.Player

class PlayerRemoteDataSourceImpl(
    private val apiPlayerService: ApiPlayerService
) : PlayerRemoteDataSource {
    override suspend fun fetchData(): List<Player> {
       return apiPlayerService.getPlayersData().data.map { it.toClub() }
    }
}
