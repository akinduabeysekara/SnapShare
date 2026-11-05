package com.example.snapshare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.snapshare.ui.capture.CaptureRoute
import com.example.snapshare.ui.theme.SnapShareTheme

class CaptureActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SnapShareTheme {
                CaptureRoute()
            }
        }
    }
}
