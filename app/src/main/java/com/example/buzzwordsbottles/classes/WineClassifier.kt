package com.example.buzzwordsbottles.classes

import android.graphics.Bitmap

interface WineClassifier {
    fun classify(bitmap: Bitmap, rotation: Int): List<Classification>
}