package com.josemaria.agridoc.ui.screens.results

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.josemaria.agridoc.ui.components.AppScaffold
import com.josemaria.agridoc.ui.components.DetailCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiseaseDetailsScreen(
    navController: NavController,
    diseaseName: String = "Tomato Early Blight",
    confidence: Float = 96.4f
) {
    val primaryGreen = Color(0xFF2E7D32)
    val secondaryGreen = Color(0xFF66BB6A)
    val backgroundColor = Color(0xFFF7FAF7)

    AppScaffold(
        title = "Disease Details",
        navController = navController
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                DiseaseHeader(
                    diseaseName = diseaseName,
                    confidence = confidence,
                    primaryGreen = primaryGreen,
                    secondaryGreen = secondaryGreen
                )
            }

            item {
                DetailCard(
                    title = "Description",
                    content = "Early blight is a common fungal disease affecting tomato plants. It causes dark concentric spots on older leaves, stems, and fruits, reducing overall crop productivity.",
                    icon = Icons.Default.Info,
                    color = primaryGreen
                )
            }

            item {
                DetailCard(
                    title = "Symptoms",
                    content = "• Brown circular spots on leaves\n• Yellowing around lesions\n• Premature leaf drop\n• Dark stem lesions\n• Fruit rot near the stem",
                    icon = Icons.Default.BugReport,
                    color = primaryGreen
                )
            }

            item {
                DetailCard(
                    title = "Treatment",
                    content = "Apply Mancozeb or Chlorothalonil fungicide every 7-10 days. Remove infected leaves immediately and avoid overhead irrigation.",
                    icon = Icons.Default.Medication,
                    color = primaryGreen
                )
            }

            item {
                DetailCard(
                    title = "Prevention",
                    content = "• Practice crop rotation\n• Use disease-free seeds\n• Maintain proper plant spacing\n• Mulch around plants\n• Water at soil level",
                    icon = Icons.Default.Shield,
                    color = primaryGreen
                )
            }

            item {
                DetailCard(
                    title = "Recommended Action",
                    content = "Immediate fungicide application is recommended. Monitor neighboring plants for similar symptoms and isolate infected crops if necessary.",
                    icon = Icons.Default.HealthAndSafety,
                    color = primaryGreen
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun DiseaseHeader(
    diseaseName: String,
    confidence: Float,
    primaryGreen: Color,
    secondaryGreen: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
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
                    imageVector = Icons.Default.BugReport,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(72.dp)
                )

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = diseaseName,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(10.dp))

                Surface(
                    color = Color.White.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text = "Confidence: ${String.format("%.1f", confidence)}%",
                        modifier = Modifier.padding(horizontal = 18.dp, vertical = 8.dp),
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DiseaseDetailsScreenPreview() {
    DiseaseDetailsScreen(rememberNavController())
}
