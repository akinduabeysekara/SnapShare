package com.example.snapshare.photo

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.example.snapshare.R

private const val JPEG_MIME_TYPE = "image/jpeg"

/** Opens the Android system share sheet for the JPEG at [photo]. */
fun Context.openShareSheet(photo: Uri) {
    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        type = JPEG_MIME_TYPE
        putExtra(Intent.EXTRA_STREAM, photo)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    startActivity(Intent.createChooser(sendIntent, getString(R.string.share_sheet_title)))
}
