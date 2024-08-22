package com.example.fauzi_chalange_chapter6.data.repository

import com.example.fauzi_chalange_chapter6.data.datasource.AuthLocalDataSource
import com.example.fauzi_chalange_chapter6.data.datasource.AuthRemoteDataSource
import com.example.fauzi_chalange_chapter6.domain.repository.AuthRepository

class AuthRepositoryImpl (
    private val authLocalDataSource: AuthLocalDataSource,
    private val authRemoteDataSource: AuthRemoteDataSource,

    ): AuthRepository {

    override suspend fun login(username: String, password: String): String {
        return authRemoteDataSource.login(username,password)
    }

    override suspend fun saveToken(logtoken: String) {
        authLocalDataSource.saveToken(logtoken)
    }

    override suspend fun loadToken(): String? {
        return authLocalDataSource.loadToken()
    }

    override suspend fun clearToken() {
        authLocalDataSource.clearToken()
    }
}