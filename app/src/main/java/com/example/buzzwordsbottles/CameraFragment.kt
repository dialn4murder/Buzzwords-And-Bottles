package com.example.buzzwordsbottles

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.example.buzzwordsbottles.databinding.FragmentCameraBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class CameraFragment : Fragment(), CameraListener, ScannedTextListener {

    /**
     * Initialises binding for login fragment
     */
    private lateinit var binding: FragmentCameraBinding

    private lateinit var cameraExecutor: ExecutorService
    private lateinit var cameraProvider: ProcessCameraProvider
    private var cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    private var preview: Preview? = null

    /**
     * Gives binding something to bind to while creating the fragment
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCameraBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    /**
     * Calls to start the camera for the fragment and starts a thread to analyse each camera frame for text
     *
     * @param view
     * @param savedInstanceState
     */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Thread to analyse each camera frame
        cameraExecutor = Executors.newSingleThreadExecutor()

        // Starts the camera
        startCamera()

    }

    /**
     * Starts the camera for the application and calls
     * the analysis of each frame whilst the camera is opened
     *
     */

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            // manages opening the camera
            cameraProvider = cameraProviderFuture.get()

            // Builds camera preview
            preview = Preview.Builder().build().also {
                it.surfaceProvider = binding.cameraView.surfaceProvider
            }

            // Defines which camera to use
            cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            cameraProvider.unbindAll()
            // Binds camera to the lifecycle
            cameraProvider.bindToLifecycle(this, cameraSelector, preview)
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    /**
     * Calls the usual shut down of the application but adds a shutdown of the camera thread when the
     * fragment is destroyed
     *
     */

    override fun analyseOnPress(){

        // Starts analysis by calling the TextAnalyzer class and binds it to cameraExecutor
        val imageAnalyzer = ImageAnalysis.Builder().build().also {
            it.setAnalyzer(cameraExecutor, TextAnalyzer(this))
        }

        // Unbinds everything from camera provider to be re-bound
        cameraProvider.unbindAll()

        // Re-bind preview + analyzer together
        cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalyzer)
    }

    // Overrides onDestroy to ensure that camera shuts down with the fragment
    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    override fun textFound(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }

}