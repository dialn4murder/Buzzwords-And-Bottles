package com.example.buzzwordsbottles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.buzzwordsbottles.classes.TextAnalysisViewModel
import com.example.compose.AppTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {


            val textAnalysisViewModel: TextAnalysisViewModel = viewModel()
            val navController = rememberNavController()
            val lifecycleOwner = LocalLifecycleOwner.current

            // Initialises and applies the text analyzer class to the camera and enabled image analysis
            val controller = remember {
                LifecycleCameraController(applicationContext).apply {
                    setEnabledUseCases(CameraController.IMAGE_ANALYSIS)
                    setImageAnalysisAnalyzer(ContextCompat.getMainExecutor(applicationContext), TextAnalyzer(textAnalysisViewModel))
                }
            }

            // Binds the camera
            LaunchedEffect(controller) {
                controller.bindToLifecycle(lifecycleOwner)
            }

            Scaffold(
                // Starts the bottom nav bar
                bottomBar = { com.example.buzzwordsbottles.screens.BottomAppBar(navController, controller) }
            ) { innerPadding ->
                // Starts the navigation
                Navigation(navController, controller, textAnalysisViewModel)
            }
        }

    }

}