package com.example.gamethrones.data.remote.services.image_loader_service

import android.graphics.Bitmap
import java.lang.ref.SoftReference

class ImageCache {

    private val softMap: MutableMap<String, SoftReference<Bitmap>> = HashMap()

    operator fun get(key: String): Bitmap? {
        return if (softMap.containsKey(key)) {
            val reference: SoftReference<Bitmap>? = softMap[key]
            reference?.get()
        } else {
            null
        }
    }

    fun put(key: String, value: Bitmap) {
        softMap[key] = SoftReference(value)
    }

    fun clear() {
        softMap.clear()
    }
}