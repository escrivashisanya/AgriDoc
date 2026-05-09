package com.josemaria.agridoc.ui.screens.scan

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.josemaria.agridoc.navigation.ROUT_SCAN_LOADING
import com.josemaria.agridoc.ui.viewmodel.ScanUiState
import com.josemaria.agridoc.ui.viewmodel.ScanViewModel

@Composable
fun ImagePreviewScreen(
    navController: NavController,
    viewModel: ScanViewModel
) {
    val state by viewModel.uiState.collectAsState()
    ImagePreviewContent(state = state, navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImagePreviewContent(
    state: ScanUiState,
    navController: NavController
) {
    val primaryGreen = Color(0xFF2E7D32)
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Confirm Image") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Display captured image
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(20.dp)
                    .background(Color.DarkGray, RoundedCornerShape(24.dp)),
                contentAlignment = Alignment.Center
            ) {
                state.bitmap?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = "Captured Image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                } ?: Text(
                    text = "No Image Captured",
                    color = Color.White,
                    fontSize = 18.sp
                )
            }

            // Bottom Actions
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Is the image clear?",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Ensure the infected area is well-lit and in focus for accurate diagnosis.",
                        color = Color.Gray,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        OutlinedButton(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier
                                .weight(1f)
                                .height(56.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
                        ) {
                            Icon(Icons.Default.Close, null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Retake")
                        }

                        Button(
                            onClick = { navController.navigate(ROUT_SCAN_LOADING) },
                            modifier = Modifier
                                .weight(1f)
                                .height(56.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = primaryGreen)
                        ) {
                            Icon(Icons.Default.Check, null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Analyze")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ImagePreviewScreenPreview() {
    ImagePreviewContent(
        state = ScanUiState(),
        navController = androidx.navigation.compose.rememberNavController()
    )
}
