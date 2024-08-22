package com.example.fauzi_chalange_chapter6.data.datasource

import com.example.fauzi_chalange_chapter6.domain.model.Player

interface PlayerRemoteDataSource {
    suspend fun fetchData(): List<Player>
}