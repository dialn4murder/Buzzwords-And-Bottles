package com.example.buzzwordsbottles

import android.graphics.Camera
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.core.ImageAnalysis
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
import java.util.concurrent.Executor


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            // Enables custom M3 generated colour theme
            AppTheme(dynamicColor = false) {
                // Initialises and remembers important information
                val textAnalysisViewModel: TextAnalysisViewModel = viewModel()
                val navController = rememberNavController()
                val lifecycleOwner = LocalLifecycleOwner.current
                val snackbarHostState = remember { androidx.compose.material3.SnackbarHostState() }


                // Initialises and applies the text analyzer class to the camera and enabled image analysis
                val controller = remember {
                    LifecycleCameraController(applicationContext).apply {
                        setEnabledUseCases(CameraController.IMAGE_ANALYSIS, )

                        // Ensures that it analyses the first image it sees
                        imageAnalysisBackpressureStrategy = ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST

                        setImageAnalysisAnalyzer(
                            ContextCompat.getMainExecutor(applicationContext),
                            TextAnalyzer(textAnalysisViewModel)

                        )
                    }
                }

                // Binds the camera
                LaunchedEffect(controller) {
                    controller.bindToLifecycle(lifecycleOwner)
                }

                Scaffold(
                    // Initialises M3 snack bar
                    snackbarHost = {
                        androidx.compose.material3.SnackbarHost(hostState = snackbarHostState)
                    },
                    // Starts the bottom nav bar
                    bottomBar = {
                        com.example.buzzwordsbottles.screens.BottomAppBar(
                            navController,
                            controller,
                            snackbarHostState
                        )
                    }
                ) { innerPadding ->
                    // Starts the navigation
                    Navigation(navController, controller, textAnalysisViewModel)
                }
            }
        }

    }

    private fun setImageAnalysisAnalyzer(
        executor: Executor,
        analyzer: TextAnalyzer,
        strategyKeepOnlyLatest: Any
    ) {
    }

}