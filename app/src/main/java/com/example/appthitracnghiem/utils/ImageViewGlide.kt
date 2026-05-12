package com.example.appthitracnghiem.utils

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.example.appthitracnghiem.R

/**
 * Loads an image with Glide. Blank/null URLs show [emptyUrlRes] instead of crashing.
 */
fun ImageView.loadNetworkImage(
    url: String?,
    @DrawableRes placeholderRes: Int = R.mipmap.ic_launcher_foreground,
    @DrawableRes errorRes: Int = R.drawable.errorimage,
    @DrawableRes emptyUrlRes: Int = placeholderRes,
) {
    val trimmed = url?.trim().orEmpty()
    if (trimmed.isEmpty()) {
        setImageResource(emptyUrlRes)
        return
    }
    Glide.with(this)
        .load(trimmed)
        .placeholder(placeholderRes)
        .error(errorRes)
        .into(this)
}

/**
 * Loads an image from a content/file [Uri]. Null/empty URIs show [emptyUriRes].
 * Use [transform] for Glide options on [RequestBuilder] (e.g. `{ centerCrop() }`).
 */
fun ImageView.loadNetworkImage(
    uri: Uri?,
    @DrawableRes placeholderRes: Int = R.mipmap.ic_launcher_foreground,
    @DrawableRes errorRes: Int = R.drawable.errorimage,
    @DrawableRes emptyUriRes: Int = placeholderRes,
    transform: (RequestBuilder<Drawable>.() -> RequestBuilder<Drawable>)? = null,
) {
    if (uri == null || uri == Uri.EMPTY) {
        setImageResource(emptyUriRes)
        return
    }
    var request = Glide.with(this)
        .load(uri)
        .placeholder(placeholderRes)
        .error(errorRes)
    request = if (transform != null) request.run(transform) else request
    request.into(this)
}
