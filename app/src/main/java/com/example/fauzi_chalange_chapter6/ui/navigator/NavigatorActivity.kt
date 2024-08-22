package com.example.fauzi_chalange_chapter6.ui.navigator


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fauzi_chalange_chapter6.ui.MainActivity
import com.example.fauzi_chalange_chapter6.ui.login.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class NavigatorActivity : AppCompatActivity() {
    private val viewModel by viewModel<NavigatorViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.isLoggedIn.observe(this) { isLoggedIn ->
            if (isLoggedIn) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
        viewModel.checkLogin()
    }
}