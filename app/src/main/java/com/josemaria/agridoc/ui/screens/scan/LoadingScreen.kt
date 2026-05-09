package com.josemaria.agridoc.ui.screens.scan

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.josemaria.agridoc.navigation.ROUT_PREVIEW
import com.josemaria.agridoc.navigation.ROUT_RESULT
import com.josemaria.agridoc.ui.viewmodel.ScanUiState
import com.josemaria.agridoc.ui.viewmodel.ScanViewModel

@Composable
fun LoadingScreen(
    navController: NavController,
    viewModel: ScanViewModel
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(state.isLoading, state.disease) {
        if (!state.isLoading && state.disease != "Unknown" && state.disease.isNotEmpty()) {
            navController.navigate(ROUT_RESULT) {
                popUpTo(ROUT_PREVIEW) { inclusive = true }
            }
        }
    }

    LoadingContent()
}

@Composable
fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            CircularProgressIndicator()

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                "Analyzing Plant Health...",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "AI is detecting diseases and treatments"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    LoadingContent()
}
