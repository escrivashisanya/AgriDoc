package com.josemaria.agridoc.ui.screens.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.josemaria.agridoc.ui.components.AppScaffold
import com.josemaria.agridoc.ui.components.DetailCard

import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.josemaria.agridoc.ui.theme.AgriDocTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun HistoryDetailsScreen(
    navController: NavController,
    scanId: String = "" // 🔥 Default value to prevent nav errors if id is missing
) {

    val primaryGreen = Color(0xFF1B5E20)
    val secondaryGreen = Color(0xFF43A047)

    val isPreview = LocalInspectionMode.current
    val auth = if (isPreview) null else FirebaseAuth.getInstance()
    val db = if (isPreview) null else FirebaseDatabase.getInstance().reference

    var cropName by remember { mutableStateOf("Loading...") }
    var diseaseName by remember { mutableStateOf("Loading...") }
    var scanDate by remember { mutableStateOf("Loading...") }
    var confidence by remember { mutableStateOf("Loading...") }
    var treatment by remember { mutableStateOf("Loading...") }
    var status by remember { mutableStateOf("Loading...") }

    // 🔥 FETCH DATA FROM FIREBASE
    LaunchedEffect(scanId) {
        if (isPreview) {
            cropName = "Tomato Plant"
            diseaseName = "Early Blight"
            scanDate = "25 Oct 2024"
            confidence = "98.2%"
            treatment = "Use copper-based fungicides and improve air circulation by pruning lower leaves."
            status = "Treatment Required"
            return@LaunchedEffect
        }
        val userId = auth?.currentUser?.uid ?: return@LaunchedEffect

        db?.child("scans")?.child(userId)?.child(scanId)
            ?.get()
            ?.addOnSuccessListener { snapshot ->

                cropName = snapshot.child("cropName").value?.toString() ?: "Unknown Crop"
                diseaseName = snapshot.child("diseaseName").value?.toString() ?: "Unknown Disease"
                scanDate = snapshot.child("date").value?.toString() ?: "-"
                confidence = snapshot.child("confidence").value?.toString() ?: "-"
                treatment = snapshot.child("treatment").value?.toString() ?: "-"
                status = snapshot.child("status").value?.toString() ?: "-"

            }
    }

    AppScaffold(
        title = "Scan Details",
        navController = navController
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {

            item {
                Spacer(modifier = Modifier.height(8.dp))

                HistoryHeader(
                    cropName = cropName,
                    diseaseName = diseaseName,
                    primaryGreen = primaryGreen,
                    secondaryGreen = secondaryGreen
                )
            }

            item {
                DetailCard("Scan Date", scanDate, Icons.Default.CalendarToday, primaryGreen)
            }

            item {
                DetailCard("Confidence Score", confidence, Icons.Default.HealthAndSafety, primaryGreen)
            }

            item {
                DetailCard("Recommended Treatment", treatment, Icons.Default.Medication, primaryGreen)
            }

            item {
                DetailCard("Crop Status", status, Icons.Default.LocalFlorist, primaryGreen)
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryDetailsScreenPreview() {
    AgriDocTheme {
        HistoryDetailsScreen(navController = rememberNavController(), scanId = "preview_id")
    }
}

@Composable
private fun HistoryHeader(
    cropName: String,
    diseaseName: String,
    primaryGreen: Color,
    secondaryGreen: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(
                        listOf(primaryGreen, secondaryGreen)
                    )
                )
                .padding(28.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.History,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(72.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = cropName,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = diseaseName,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White.copy(alpha = 0.9f)
                )
            }
        }
    }
}

