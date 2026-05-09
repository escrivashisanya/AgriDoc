package com.josemaria.agridoc.ui.screens.scan

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Science
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.josemaria.agridoc.navigation.ROUT_LOADING
import com.josemaria.agridoc.ui.viewmodel.ScanUiState
import com.josemaria.agridoc.ui.viewmodel.ScanViewModel

@Composable
fun PreviewScreen(
    navController: NavController,
    viewModel: ScanViewModel
) {
    val state by viewModel.uiState.collectAsState()
    PreviewContent(
        state = state,
        navController = navController,
        onAnalyzeClick = {
            viewModel.analyzeImage()
            navController.navigate(ROUT_LOADING)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreviewContent(
    state: ScanUiState,
    navController: NavController,
    onAnalyzeClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Preview Plant Image")
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column {
                Text(
                    text = "Review Your Capture",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Ensure the affected leaf is clearly visible before analysis.",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(24.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    elevation = CardDefaults.cardElevation(12.dp)
                ) {
                    state.bitmap?.let { bitmap ->
                        Image(
                            painter = rememberAsyncImagePainter(bitmap),
                            contentDescription = "Captured Plant",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(420.dp)
                                .clip(RoundedCornerShape(24.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            Column {

                Button(
                    onClick = onAnalyzeClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    shape = RoundedCornerShape(18.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Science,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = "Analyze Plant",
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedButton(
                    onClick = {
                        navController.popBackStack()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    shape = RoundedCornerShape(18.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = "Retake Photo",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreenPreview() {
    PreviewContent(
        state = ScanUiState(),
        navController = rememberNavController(),
        onAnalyzeClick = {}
    )
}
