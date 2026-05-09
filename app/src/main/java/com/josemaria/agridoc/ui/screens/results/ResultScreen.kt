package com.josemaria.agridoc.ui.screens.results

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
// import com.google.firebase.database.FirebaseDatabase
import com.josemaria.agridoc.navigation.ROUT_CAMERA
import com.josemaria.agridoc.ui.components.AppScaffold
import com.josemaria.agridoc.ui.components.ResultSection
import com.josemaria.agridoc.ui.viewmodel.ScanUiState
import com.josemaria.agridoc.ui.viewmodel.ScanViewModel

@Composable
fun ResultScreen(
    navController: NavController,
    viewModel: ScanViewModel
) {
    val state by viewModel.uiState.collectAsState()

    // 🔥 SAVE TO FIREBASE WHEN RESULT LOADS
    /*
    LaunchedEffect(state) {
        if (state.disease.isNotEmpty()) {
            // saveScanToFirebase(state)
        }
    }
    */

    ResultContent(state = state, navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultContent(
    state: ScanUiState,
    navController: NavController
) {
    AppScaffold(
        title = "Diagnosis Results",
        navController = navController
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            item {
                Card {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Text(
                            state.disease,
                            style = MaterialTheme.typography.headlineMedium
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        LinearProgressIndicator(
                            progress = { state.confidence },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            "Confidence: ${(state.confidence * 100).toInt()}%"
                        )
                    }
                }
            }

            item {
                ResultSection(
                    title = "Treatment",
                    content = state.treatment
                )
            }

            item {
                ResultSection(
                    title = "Prevention",
                    content = state.prevention
                )
            }

            item {
                Button(
                    onClick = {
                        navController.navigate(ROUT_CAMERA)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Scan Another Plant")
                }
            }
        }
    }
}