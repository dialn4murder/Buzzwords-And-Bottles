package com.example.buzzwordsbottles.classes

import android.util.Log
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
                Log.d("scan", it.name)
                viewModel.setScannedText(Descriptions(it.name, it.score))
            }

            imageProxy.close()
        }
        frameSkipCounter++

//        // Checks if analysis has finished
//        if (!toggle){
//            imageProxy.close()
//            return
//        }
//
//        toggle = false
//
//        // Current frame to be analyzed if null return
//        val mediaImage = imageProxy.image ?: return
//        val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
//
//
//        recognizer.process(image)
//            .addOnSuccessListener { visionText ->
//                var text = ""
//                for (block in visionText.textBlocks) {
//                    text += block.text
//                    Log.d("Scanned Text", block.text)
//                }
//                viewModel.setScannedText(text)
//
//            }
//            .addOnCompleteListener {
//                imageProxy.close()
//            }
//
        }

}