package com.example.snapshare.ui.capture

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.example.snapshare.R
import com.example.snapshare.photo.decodeUprightBitmap
import com.example.snapshare.photo.newCaptureUri
import com.example.snapshare.photo.openShareSheet

/** Owns the permission and camera flows and feeds the resulting state to [CaptureScreen]. */
@Composable
fun CaptureRoute() {
    val context = LocalContext.current
    var uiState by remember { mutableStateOf(CaptureUiState()) }
    var pendingUri by remember { mutableStateOf<Uri?>(null) }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { saved ->
        val written = pendingUri
        if (saved && written != null) {
            uiState = CaptureUiState(
                photoUri = written,
                preview = context.decodeUprightBitmap(written)
            )
        } else {
            context.toast(R.string.toast_capture_cancelled)
        }
    }

    fun startCapture() {
        val destination = context.newCaptureUri()
        if (destination == null) {
            context.toast(R.string.toast_capture_file_error)
            return
        }
        pendingUri = destination
        cameraLauncher.launch(destination)
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        uiState = uiState.copy(showPermissionNotice = !granted)
        if (granted) startCapture() else context.toast(R.string.toast_permission_denied)
    }

    CaptureScreen(
        state = uiState,
        onCaptureClick = {
            if (context.hasCameraPermission()) {
                uiState = uiState.copy(showPermissionNotice = false)
                startCapture()
            } else {
                permissionLauncher.launch(Manifest.permission.CAMERA)
            }
        },
        onShareClick = { uiState.photoUri?.let { context.openShareSheet(it) } }
    )
}

private fun Context.hasCameraPermission(): Boolean =
    ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
        PackageManager.PERMISSION_GRANTED

private fun Context.toast(@StringRes message: Int) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
