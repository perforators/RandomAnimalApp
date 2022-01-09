package com.example.gamethrones.util

import android.content.Context
import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url: String, context: Context) {
    Picasso.with(context)
        .load(url)
        .into(this)
}