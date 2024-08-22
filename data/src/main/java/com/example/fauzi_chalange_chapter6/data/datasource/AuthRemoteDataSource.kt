package com.example.fauzi_chalange_chapter6.data.datasource

interface AuthRemoteDataSource {
    suspend fun login(username: String, password: String): String
}