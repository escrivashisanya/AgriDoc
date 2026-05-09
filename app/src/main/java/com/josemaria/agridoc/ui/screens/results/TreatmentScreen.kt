package com.josemaria.agridoc.ui.screens.results

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.josemaria.agridoc.ui.components.AppScaffold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TreatmentScreen(navController: NavController) {
    val primaryGreen = Color(0xFF1B5E20)
    val secondaryGreen = Color(0xFF43A047)
    val lightGreen = Color(0xFFE8F5E9)
    val backgroundColor = Color(0xFFF7FFF8)

    val treatments = listOf(
        TreatmentItem(
            title = "Apply Recommended Fungicide",
            description = "Use copper-based fungicides or Mancozeb according to manufacturer instructions.",
            icon = Icons.Default.Medication
        ),
        TreatmentItem(
            title = "Remove Infected Leaves",
            description = "Prune and safely dispose of affected plant parts to prevent further spread.",
            icon = Icons.Default.LocalHospital
        ),
        TreatmentItem(
            title = "Improve Soil Health",
            description = "Add organic compost and ensure proper soil drainage for stronger plant immunity.",
            icon = Icons.Default.Science
        ),
        TreatmentItem(
            title = "Monitor Progress",
            description = "Regularly inspect plants and re-scan using the app after treatment application.",
            icon = Icons.Default.HealthAndSafety
        )
    )

    AppScaffold(
        title = "Treatment Guide",
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
                Spacer(modifier = Modifier.height(10.dp))
                TreatmentHeader(primaryGreen, secondaryGreen)
            }

            items(treatments) { treatment ->
                TreatmentCard(
                    treatment = treatment,
                    primaryGreen = primaryGreen,
                    lightGreen = lightGreen
                )
            }

            item {
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}

data class TreatmentItem(
    val title: String,
    val description: String,
    val icon: ImageVector
)

@Composable
private fun TreatmentHeader(
    primaryGreen: Color,
    secondaryGreen: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(30.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Brush.horizontalGradient(listOf(primaryGreen, secondaryGreen)))
                .padding(24.dp)
        ) {
            Column {
                Text(
                    text = "Recommended Steps",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Follow these guidelines for effective recovery.",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
private fun TreatmentCard(
    treatment: TreatmentItem,
    primaryGreen: Color,
    lightGreen: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(50.dp),
                shape = CircleShape,
                color = lightGreen
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = treatment.icon,
                        contentDescription = null,
                        tint = primaryGreen,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = treatment.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = primaryGreen
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = treatment.description,
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TreatmentScreenPreview() {
    TreatmentScreen(rememberNavController())
}
