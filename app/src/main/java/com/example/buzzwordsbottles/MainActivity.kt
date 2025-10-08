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
import com.example.buzzwordsbottles.classes.SharedViewModel


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val sharedViewModel: SharedViewModel = viewModel()
            val navController = rememberNavController()
            val lifecycleOwner = LocalLifecycleOwner.current

            val controller = remember {
                LifecycleCameraController(applicationContext).apply {
                    setEnabledUseCases(CameraController.IMAGE_ANALYSIS)
                    setImageAnalysisAnalyzer(ContextCompat.getMainExecutor(applicationContext), TextAnalyzer(sharedViewModel))
                }
            }

            LaunchedEffect(controller) {
                controller.bindToLifecycle(lifecycleOwner)
            }

            Scaffold(
                bottomBar = { com.example.buzzwordsbottles.screens.BottomAppBar(navController, controller) }
            ) { innerPadding ->
                Navigation(navController, controller)
            }
        }

    }

}