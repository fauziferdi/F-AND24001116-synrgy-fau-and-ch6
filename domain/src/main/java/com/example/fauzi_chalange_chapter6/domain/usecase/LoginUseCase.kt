package com.example.fauzi_chalange_chapter6.domain.usecase

import com.example.fauzi_chalange_chapter6.domain.repository.AuthRepository


class LoginUseCase(
    private val repository: AuthRepository,
) {

    suspend fun login(username: String, password: String) {
        if (username.isEmpty() || password.isEmpty()) {
            throw IllegalArgumentException("Username or password cannot be empty")
        } else {
            val logtoken = repository.login(username, password)
            repository.saveToken(logtoken)
        }
    }
}