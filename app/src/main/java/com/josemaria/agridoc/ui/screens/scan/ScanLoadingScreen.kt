package com.josemaria.agridoc.ui.screens.scan

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.josemaria.agridoc.navigation.ROUT_RESULT
import com.josemaria.agridoc.navigation.ROUT_SCAN_LOADING
import com.josemaria.agridoc.ui.viewmodel.ScanUiState
import com.josemaria.agridoc.ui.viewmodel.ScanViewModel
import kotlinx.coroutines.delay

@Composable
fun ScanLoadingScreen(
    navController: NavController,
    viewModel: ScanViewModel
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.analyzeImage()
    }

    LaunchedEffect(state.isLoading) {
        if (!state.isLoading && state.disease != "Unknown") {
            navController.navigate(ROUT_RESULT) {
                popUpTo(ROUT_SCAN_LOADING) { inclusive = true }
            }
        }
    }

    ScanLoadingContent(state = state)
}

@Composable
fun ScanLoadingContent(
    state: ScanUiState
) {
    val primaryGreen = Color(0xFF2E7D32)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .background(primaryGreen.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(100.dp),
                color = primaryGreen,
                strokeWidth = 6.dp
            )
            Text(
                text = "🌿",
                fontSize = 48.sp
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Analyzing Crop Image",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = primaryGreen
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Our AI is identifying potential diseases...",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(60.dp))

        // Simulated progress steps
        LoadingStep(text = "Uploading image...", active = true)
        LoadingStep(text = "Running AI model...", active = state.isLoading)
        LoadingStep(text = "Generating recommendations...", active = !state.isLoading && state.disease != "Unknown")
    }
}

@Composable
private fun LoadingStep(text: String, active: Boolean) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(CircleShape)
                .background(if (active) Color(0xFF2E7D32) else Color.LightGray)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            color = if (active) Color.Black else Color.Gray,
            fontWeight = if (active) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ScanLoadingScreenPreview() {
    ScanLoadingContent(state = ScanUiState(isLoading = true))
}
