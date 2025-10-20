package com.example.buzzwordsbottles.classes

import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy

/**
 * TextAnalyzer will analyse each frame for text which will be used to scrape websites for descriptions
 *
 */

class WineAnalyzer(
    private val viewModel: WineViewModel,
    private val classifier: WineClassifier,
    ) : ImageAnalysis.Analyzer {
    // Ensures doesn't go multiple times
    var frameSkipCounter = 0

    /**
     * Analyzes the image and prints the text of each frame
     *
     * @param imageProxy frame to be analysed
     */
    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        // Ensures doesn't go multiple times
        if (frameSkipCounter % 60 == 0) {
            // Gets rotation info
            val rotationDegrees = imageProxy.imageInfo.rotationDegrees
            // Changes imageProxy to bitmap and ensures its the right size for the model
            val bitmap = imageProxy
                .toBitmap()
                .centerCrop(224 , 224 )

            // Wine information stored into a List
            val results = classifier.classify(bitmap, rotationDegrees)

            // Loops through the list and sends it to the viewmodel
            results.forEach { it
                viewModel.setScannedText(Descriptions(it.description, it.title, it.score))
            }

            imageProxy.close()
        }
        frameSkipCounter++
        }

}