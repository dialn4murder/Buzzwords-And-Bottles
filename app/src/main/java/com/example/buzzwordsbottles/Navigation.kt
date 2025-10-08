package com.example.buzzwordsbottles

import androidx.camera.view.LifecycleCameraController
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.buzzwordsbottles.classes.NavRoute
import com.example.buzzwordsbottles.screens.CameraScreen
import com.example.buzzwordsbottles.screens.DescriptionsScreen

@Composable
fun Navigation(navController: NavHostController, controller: LifecycleCameraController) {
    NavHost(navController, startDestination = NavRoute.Camera.name) {
        composable(NavRoute.Camera.name) { CameraScreen(modifier = Modifier,controller) }
        composable(NavRoute.Descriptions.name) { DescriptionsScreen(modifier = Modifier) }
    }
}