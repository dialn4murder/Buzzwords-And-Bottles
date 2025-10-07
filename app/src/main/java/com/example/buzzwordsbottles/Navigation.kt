package com.example.buzzwordsbottles

import androidx.camera.view.LifecycleCameraController
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.buzzwordsbottles.screens.CameraScreen
import com.example.buzzwordsbottles.screens.DescriptionsScreen

@Composable
fun Navigation(navController: NavHostController, controller: LifecycleCameraController) {
    NavHost(navController, startDestination = "camera") {
        composable("camera") { CameraScreen(modifier = Modifier,controller) }
        composable("descriptions") { DescriptionsScreen(modifier = Modifier) }
    }
}