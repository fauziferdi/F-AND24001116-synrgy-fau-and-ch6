package com.example.fauzi_chalange_chapter6.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.appcompat.app.AppCompatActivity
import com.example.fauzi_chalange_chapter6.ui.MainActivity
import com.example.fauzi_chalange_chapter6.databinding.ActivityLoginBinding
import com.example.fauzi_chalange_chapter6.MyApplication

class LoginActivity : AppCompatActivity() {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener{
            if (binding.valueUsername.text.isNullOrEmpty()) {
                binding.valueUsername.error = "Username tidak boleh kosong"
            }else if (binding.valuePassword.text.isNullOrEmpty()) {
                binding.valuePassword.error = "Password tidak boleh kosong"
            }else{
                binding.valueUsername.error = null
                binding.valuePassword.error = null
                viewModel.login(
                    username = binding.valueUsername.text.toString(),
                    password = binding.valuePassword.text.toString()
                )

            }
        }

        viewModel.loading.observe(this) {
            isLoading ->
            if (isLoading) {
                binding.flipperButtonLogin.displayedChild = 1
            } else {
                binding.flipperButtonLogin.displayedChild = 0
            }
        }

        viewModel.success.observe(this){isSuccess ->
            if (isSuccess) {
                Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

        viewModel.error.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }

    }

}