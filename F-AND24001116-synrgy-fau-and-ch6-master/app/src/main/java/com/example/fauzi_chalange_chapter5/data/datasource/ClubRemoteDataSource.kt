package com.example.fauzi_chalange_chapter5.data.datasource

import com.example.fauzi_chalange_chapter5.ui.listclub.dataclass.Club

interface ClubRemoteDataSource {
    suspend fun fetchData(): List<Club>
}