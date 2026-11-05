package com.example.snapshare.photo

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val LOG_TAG = "CaptureFiles"
private const val PROVIDER_SUFFIX = ".capturefiles"
private const val STAMP_PATTERN = "yyyyMMdd_HHmmss"

/**
 * Reserves an empty JPEG in the cache directory for the camera app to write into.
 *
 * @return a shareable content URI for the file, or null if it could not be created.
 */
fun Context.newCaptureUri(): Uri? = try {
    val stamp = SimpleDateFormat(STAMP_PATTERN, Locale.getDefault()).format(Date())
    val destination = File.createTempFile("capture_${stamp}_", ".jpg", cacheDir)
    FileProvider.getUriForFile(this, packageName + PROVIDER_SUFFIX, destination)
} catch (failure: Exception) {
    Log.e(LOG_TAG, "Could not reserve a capture file", failure)
    null
}
