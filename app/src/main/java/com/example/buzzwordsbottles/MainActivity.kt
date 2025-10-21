package com.example.buzzwordsbottles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.buzzwordsbottles.classes.ObserveAsEvents
import com.example.buzzwordsbottles.classes.SnackbarController
import com.example.buzzwordsbottles.classes.WineViewModel
import com.example.buzzwordsbottles.classes.TfLiteWineClassifier
import com.example.buzzwordsbottles.classes.WineAnalyzer
import com.example.buzzwordsbottles.screens.Navigation
import com.example.compose.AppTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // Enables custom M3 generated colour theme
            AppTheme(dynamicColor = false) {
                // Initialises and remembers important information
                val wineViewModel: WineViewModel = viewModel()
                val navController = rememberNavController()
                val lifecycleOwner = LocalLifecycleOwner.current
                val snackbarHostState = remember { androidx.compose.material3.SnackbarHostState() }
                val coroutine = rememberCoroutineScope()

                wineViewModel.initialiseList()

                // Observes if there are any snack bars to be made
                ObserveAsEvents(
                    flow = SnackbarController.events,
                    snackbarHostState
                ) { event ->
                    coroutine.launch {
                        snackbarHostState.currentSnackbarData?.dismiss()

                        val result = snackbarHostState.showSnackbar(
                            message = event.message,
                            actionLabel = event.action?.name,
                            duration = SnackbarDuration.Short
                        )

                        if (result == SnackbarResult.ActionPerformed) {
                            event.action?.action?.invoke()
                        }
                    }
                }

                // Initialises analyzer
                val analyzer = remember {
                    WineAnalyzer(
                        wineViewModel,
                        classifier = TfLiteWineClassifier(
                            context = applicationContext
                        )
                    )
                }


                // Initialises and applies the text analyzer class to the camera and enabled image analysis
                val controller = remember {
                    LifecycleCameraController(applicationContext).apply {
                        setEnabledUseCases(CameraController.IMAGE_ANALYSIS)
                        setEnabledUseCases(CameraController.IMAGE_CAPTURE)
                        setImageAnalysisAnalyzer(
                            ContextCompat.getMainExecutor(applicationContext),
                            analyzer
                        )

                    }
                }

                // Binds the camera
                LaunchedEffect(controller) {
                    controller.bindToLifecycle(lifecycleOwner )
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
                            analyzer
                        )
                    }
                ) { innerPadding ->
                    // Starts the navigation
                    Navigation(navController, controller, wineViewModel)
                }
            }
        }

    }

}