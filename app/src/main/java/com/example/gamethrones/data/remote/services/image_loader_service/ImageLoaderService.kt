package com.example.gamethrones.data.remote.services.image_loader_service

import android.graphics.Bitmap

interface ImageLoaderService {

    fun load(imageLink: String): Bitmap
}