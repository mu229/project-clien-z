package com.example.projectzulfa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.uas.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class TentangKita : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tentangkiita)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment2) as NavHostFragment
        val navController = navHostFragment.navController

        findViewById<BottomNavigationView>(R.id.bottom_navigation2)
            .setupWithNavController(navController)
    }
}