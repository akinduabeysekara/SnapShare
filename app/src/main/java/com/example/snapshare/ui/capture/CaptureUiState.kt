package com.example.snapshare.ui.capture

import android.graphics.Bitmap
import android.net.Uri

data class CaptureUiState(
    val photoUri: Uri? = null,
    val preview: Bitmap? = null,
    val showPermissionNotice: Boolean = false
) {
    val hasPhoto: Boolean get() = photoUri != null
}
