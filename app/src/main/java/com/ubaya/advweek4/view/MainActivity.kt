package com.ubaya.advweek4.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.ubaya.advweek4.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        navController = (supportFragmentManager.findFragmentById(R.id.hostFragment)
//                as NavHostFragment).navController
//        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
//        NavigationUI.setupWithNavController(navView, navController)
//
//        // Hubungkan antara bottom nav view dengan NavController
//        bottomNav.setupWithNavController(navController)
    }

//    override fun onSupportNavigateUp(): Boolean {
//        return NavigationUI.navigateUp(navController , drawerLayout) || super.onSupportNavigateUp()
//    }
}