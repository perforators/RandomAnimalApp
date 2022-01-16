package com.example.gamethrones.data.remote.services.image_loader_service

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.net.URL

class ImageLoaderServiceImpl(
    private val cache: ImageCache
): ImageLoaderService {

    override fun load(imageLink: String): Bitmap {
        var bitmap = cache[imageLink]

        if (bitmap == null) {
            val inputStream = URL(imageLink).openStream()
            bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream.close()
            cache.put(key = imageLink, value = bitmap)
        }

        return bitmap!!
    }
}