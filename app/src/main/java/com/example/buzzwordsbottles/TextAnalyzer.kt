package com.example.buzzwordsbottles

import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

/**
 * TextAnalyzer will analyse each frame for text which will be used to scrape websites for descriptions
 *
 */

class TextAnalyzer : ImageAnalysis.Analyzer {
    // Initialises ML Kit
    private val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

    /**
     * Analyzes the image and prints the text of each frame
     *
     * @param imageProxy frame to be analysed
     */
    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image ?: return
        val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

        recognizer.process(image)
            .addOnSuccessListener { visionText ->
                for (block in visionText.textBlocks) {
                    Log.d("Scanned Text", block.text)
                }
            }
            .addOnFailureListener { e ->
                Log.e("Text Error", "Error recognizing text", e)
            }
            .addOnCompleteListener {
                imageProxy.close()
            }
    }
}