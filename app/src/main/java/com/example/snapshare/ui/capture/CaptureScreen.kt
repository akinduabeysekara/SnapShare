package com.example.snapshare.ui.capture

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.snapshare.R
import com.example.snapshare.ui.theme.SnapShareTheme

@Composable
fun CaptureScreen(
    state: CaptureUiState,
    onCaptureClick: () -> Unit,
    onShareClick: () -> Unit
) {
    Scaffold(topBar = { CaptureTopBar() }) { barPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(barPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            IntroCard()
            Spacer(Modifier.height(16.dp))
            if (state.showPermissionNotice) {
                PermissionNotice()
            }
            PhotoFrame(preview = state.preview)
            Spacer(Modifier.height(24.dp))
            CaptureActions(
                hasPhoto = state.hasPhoto,
                onCaptureClick = onCaptureClick,
                onShareClick = onShareClick
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CaptureTopBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                fontWeight = FontWeight.Bold
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun CaptureScreenPreview() {
    SnapShareTheme {
        CaptureScreen(
            state = CaptureUiState(),
            onCaptureClick = {},
            onShareClick = {}
        )
    }
}
