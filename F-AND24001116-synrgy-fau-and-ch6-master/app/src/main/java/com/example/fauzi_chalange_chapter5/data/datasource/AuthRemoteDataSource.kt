package com.example.fauzi_chalange_chapter5.data.datasource

interface AuthRemoteDataSource {
    suspend fun login(username: String, password: String): String
}