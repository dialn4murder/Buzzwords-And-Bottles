package com.example.buzzwordsbottles.screens

import androidx.camera.view.LifecycleCameraController
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.buzzwordsbottles.classes.NavRoute
import com.example.buzzwordsbottles.classes.WineViewModel

@Composable
fun Navigation(navController: NavHostController, controller: LifecycleCameraController, wineViewModel: WineViewModel) {

    // Starts the navhost
    NavHost(navController, startDestination = NavRoute.Camera.name) {
        // Maps the route to the screens
        composable(NavRoute.Camera.name) { CameraScreen(modifier = Modifier,controller) }
        composable(NavRoute.Descriptions.name) { DescriptionsScreen(modifier = Modifier, wineViewModel ) }
    }
}