package com.example.fauzi_chalange_chapter6.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.fauzi_chalange_chapter6.R
import com.example.fauzi_chalange_chapter6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        setupNavigationComponentWithAppBar()
    }

    override fun onSupportNavigateUp(): Boolean {
        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.host_fragment_con) as NavHostFragment
        return host.navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun setupNavigationComponentWithAppBar() {
        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.host_fragment_con) as NavHostFragment? ?: return
        setupActionBarWithNavController(host.navController)
    }



}