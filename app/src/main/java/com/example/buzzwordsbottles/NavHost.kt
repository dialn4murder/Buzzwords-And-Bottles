package com.example.buzzwordsbottles

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavHost() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "camera") {
        composable("camera") { DescriptionsScreen() }
        composable("descriptions") { DescriptionsScreen() }
    }
}