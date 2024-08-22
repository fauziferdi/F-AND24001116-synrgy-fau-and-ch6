package com.example.fauzi_chalange_chapter6.data.datasource.remote

import com.example.fauzi_chalange_chapter6.data.datasource.AuthRemoteDataSource
import com.example.fauzi_chalange_chapter6.data.datasource.remote.retrofit.ReqresService
import com.example.fauzi_chalange_chapter6.data.datasource.remote.retrofit.model.req.LoginBody
import com.example.fauzi_chalange_chapter6.domain.model.User
import kotlinx.coroutines.delay

class AuthRemoteDataSourceImpl(
    private val reqresService: ReqresService,
) : AuthRemoteDataSource {
    private val users
        get() = listOf(
            User(
                username = "fauzi",
                password = "12345"
            ),
        )

    override suspend fun login(username: String, password: String): String {
        delay(1000)
        return if (users.contains(User(username, password))) {
             "dashdy23622g2ysy2w27s288s282"
        } else {
            reqresService.login(
                loginBody = LoginBody(
                    username = username,
                    password = password,
                )
            ).logtoken
        }
    }
}