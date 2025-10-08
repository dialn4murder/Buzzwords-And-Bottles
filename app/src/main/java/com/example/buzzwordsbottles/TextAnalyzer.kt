package com.example.buzzwordsbottles

import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.view.LifecycleCameraController
import com.example.buzzwordsbottles.classes.SharedViewModel
import com.example.buzzwordsbottles.interfaces.ScannedTextListener
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

/**
 * TextAnalyzer will analyse each frame for text which will be used to scrape websites for descriptions
 *
 */

class TextAnalyzer(private val viewModel: SharedViewModel) : ImageAnalysis.Analyzer {
    // Initialises ML Kit
    private val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

    var toggle = false

    /**
     * Analyzes the image and prints the text of each frame
     *
     * @param imageProxy frame to be analysed
     */
    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        //Log.d("Scanned Text", "ANALYSIS HAS BEEN RAN RAN RAN RAN RAN RAN")
        // Checks if analysis has finished
        if (!toggle){
            imageProxy.close()
            return
        }

        toggle = false

        // Current frame to be analyzed if null return
        val mediaImage = imageProxy.image ?: return
        val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)


        recognizer.process(image)
            .addOnSuccessListener { visionText ->
                var text = ""
                for (block in visionText.textBlocks) {
                    text += block.text
                    Log.d("Scanned Text", block.text)
                }
                viewModel.setScannedText(text)

            }
            .addOnCompleteListener {
                imageProxy.close()
//                controller.clearImageAnalysisAnalyzer()
//                toggle = true
            }

        }

}