package com.example.fauzi_chalange_chapter5.domain

interface AuthRepository {
   suspend fun login(username: String, password: String): String
   suspend fun saveToken(logtoken: String)
   suspend fun loadToken(): String?
   suspend fun clearToken()
}