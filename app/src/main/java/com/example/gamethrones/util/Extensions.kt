package com.example.gamethrones.util

import android.graphics.Bitmap
import android.graphics.Matrix

fun Bitmap.resize(newWidth: Int, newHeight: Int): Bitmap {
    val scaleWidth: Float = newWidth.toFloat() / width
    val scaleHeight: Float = newHeight.toFloat() / height
    val matrix = Matrix()
    matrix.postScale(scaleWidth, scaleHeight)
    return Bitmap.createBitmap(this, 0, 0, width, height, matrix, false)
}