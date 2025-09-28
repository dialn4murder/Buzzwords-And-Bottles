package com.example.buzzwordsbottles

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import android.Manifest
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            Scaffold(
                bottomBar = { com.example.buzzwordsbottles.screens.BottomAppBar(navController) }
            ) { innerPadding ->
                Navigation(navController)
            }
        }

        checkCameraPermission()

    }

    private fun checkCameraPermission() {
        // Starts the camera if the user has granted camera permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED
        ) {
            // replaceFragment(CameraFragment(), "cameraFragment")
        // Requests camera permissions while the app is running
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 101)
        }
    }

}