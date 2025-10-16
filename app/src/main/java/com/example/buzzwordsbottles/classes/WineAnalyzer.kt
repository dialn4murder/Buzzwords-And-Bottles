package com.example.buzzwordsbottles.classes

import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import org.checkerframework.checker.fenum.qual.SwingElementOrientation

/**
 * TextAnalyzer will analyse each frame for text which will be used to scrape websites for descriptions
 *
 */

class WineAnalyzer(
    private val viewModel: TextAnalysisViewModel,
    private val classifier: WineClassifier,
    private val onResults: (List<Classification>) -> Unit
    ) : ImageAnalysis.Analyzer {
    // Initialises ML Kit
    private val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

    var frameSkipCounter = 0
    var toggle = false

    /**
     * Analyzes the image and prints the text of each frame
     *
     * @param imageProxy frame to be analysed
     */
    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        if (frameSkipCounter % 60 == 0) {
            val rotationDegrees = imageProxy.imageInfo.rotationDegrees
            val bitmap = imageProxy
                .toBitmap()
                .centerCrop(224 , 224 )

            val results = classifier.classify(bitmap, rotationDegrees)
            onResults(results)

            results.forEach { it
                Log.d("scan", it.name)
                viewModel.setScannedText(it.name)
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