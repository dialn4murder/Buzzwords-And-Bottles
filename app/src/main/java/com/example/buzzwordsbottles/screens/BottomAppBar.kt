package com.example.buzzwordsbottles.screens

import android.util.Log
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.core.takePicture
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.buzzwordsbottles.classes.Classification
import com.example.buzzwordsbottles.classes.WineAnalyzer
import com.example.buzzwordsbottles.classes.NavRoute
import com.example.buzzwordsbottles.classes.TextAnalysisViewModel
import kotlinx.coroutines.coroutineScope

@Composable
fun BottomAppBar(
    navController: NavHostController,
    controller: LifecycleCameraController,
    analyzer: WineAnalyzer
) {
    // Initialises and remembers important information
    val context = LocalContext.current
//    val textAnalysisViewModel: TextAnalysisViewModel = viewModel()


    BottomAppBar(
        actions = {
            // Camera and description buttons
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
        // FAB to start analysis
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    controller.takePicture(
                        ContextCompat.getMainExecutor(context),
                        object : ImageCapture.OnImageCapturedCallback(){
                            override fun onCaptureSuccess(image: ImageProxy) {
                                analyzer.frameSkipCounter = 0
                                analyzer.analyze(image)
                                super.onCaptureSuccess(image)
                            }

                            override fun onError(exception: ImageCaptureException) {
                                Log.d("Camera", "Error taking photo")
                                super.onError(exception)
                            }
                        }
                    )

                },
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(Icons.Filled.Add, "Localized description")
            }
        }
    )
}