package com.example.buzzwordsbottles

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.buzzwordsbottles.screens.CameraScreen
import com.example.buzzwordsbottles.screens.DescriptionsScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = "camera") {
        composable("camera") { CameraScreen() }
        composable("descriptions") { DescriptionsScreen() }
    }
}