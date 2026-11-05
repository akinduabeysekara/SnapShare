package com.example.snapshare.photo

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.util.Log
import androidx.exifinterface.media.ExifInterface

private const val LOG_TAG = "UprightBitmapDecoder"

/** Maps an EXIF orientation tag to the clockwise rotation it needs, or null if none is needed. */
internal fun rotationForExifOrientation(orientation: Int): Float? = when (orientation) {
    ExifInterface.ORIENTATION_ROTATE_90 -> 90f
    ExifInterface.ORIENTATION_ROTATE_180 -> 180f
    ExifInterface.ORIENTATION_ROTATE_270 -> 270f
    else -> null
}

/** Decodes the image at [source], rotated so it displays the way it was shot. */
fun Context.decodeUprightBitmap(source: Uri): Bitmap? = try {
    val decoded = contentResolver.openInputStream(source)
        ?.use { stream -> BitmapFactory.decodeStream(stream) }
    decoded?.let { applyExifRotation(it, source) }
} catch (failure: Exception) {
    Log.e(LOG_TAG, "Could not decode $source", failure)
    null
}

private fun Context.applyExifRotation(image: Bitmap, source: Uri): Bitmap {
    val orientation = contentResolver.openInputStream(source)?.use { stream ->
        ExifInterface(stream).getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_UNDEFINED
        )
    } ?: return image
    val degrees = rotationForExifOrientation(orientation) ?: return image
    val rotation = Matrix().apply { postRotate(degrees) }
    return Bitmap.createBitmap(image, 0, 0, image.width, image.height, rotation, true)
}
