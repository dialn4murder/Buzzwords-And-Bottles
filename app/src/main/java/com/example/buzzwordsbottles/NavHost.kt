package com.example.buzzwordsbottles

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavHost(navController: NavHostController) {
    NavHost(navController, startDestination = "descriptions") {
        composable("camera") { CameraScreen() }
        composable("descriptions") { DescriptionsScreen() }
    }
}