package com.example.buzzwordsbottles.screens

import androidx.camera.view.LifecycleCameraController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.FormatListBulleted
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.buzzwordsbottles.TextAnalyzer
import com.example.buzzwordsbottles.classes.NavRoute

@Composable
fun BottomAppBar(navController: NavHostController, controller: LifecycleCameraController) {
    val context = LocalContext.current
    val analyzer = remember {TextAnalyzer()}
    BottomAppBar(
        actions = {
            IconButton(onClick = { navController.navigate(NavRoute.Camera.name) }) {
                Icon(Icons.Filled.CameraAlt, contentDescription = "Localized description")
            }
            IconButton(onClick = { navController.navigate(NavRoute.Descriptions.name) }) {
                Icon(
                    Icons.AutoMirrored.Filled.FormatListBulleted,
                    contentDescription = "Localized description",
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    controller.setImageAnalysisAnalyzer(
                        ContextCompat.getMainExecutor(context),
                        analyzer
                    )
                    analyzer.toggle = true

                },
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(Icons.Filled.Add, "Localized description")
            }
        }
    )
}