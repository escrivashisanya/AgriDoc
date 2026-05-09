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
fun PreventionScreen(navController: NavController) {
    val primaryGreen = Color(0xFF1B5E20)
    val secondaryGreen = Color(0xFF4CAF50)
    val lightGreen = Color(0xFFE8F5E9)
    val background = Color(0xFFF8FFFA)

    val preventionTips = listOf(
        PreventionTip(
            title = "Use Certified Seeds",
            description = "Always plant certified disease-free seeds to minimize infection risks.",
            icon = Icons.Default.CheckCircle
        ),
        PreventionTip(
            title = "Proper Watering",
            description = "Avoid overwatering and water plants early in the morning.",
            icon = Icons.Default.WaterDrop
        ),
        PreventionTip(
            title = "Crop Rotation",
            description = "Rotate crops seasonally to break disease cycles in the soil.",
            icon = Icons.Default.Eco
        ),
        PreventionTip(
            title = "Maintain Hygiene",
            description = "Clean tools regularly and remove infected plant debris immediately.",
            icon = Icons.Default.Shield
        ),
        PreventionTip(
            title = "Proper Spacing",
            description = "Allow adequate spacing between plants for better air circulation.",
            icon = Icons.Default.LocalFlorist
        )
    )

    AppScaffold(
        title = "Disease Prevention",
        navController = navController
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(12.dp))
                PreventionHeader(primaryGreen, secondaryGreen)
            }

            items(preventionTips) { tip ->
                PreventionCard(
                    tip = tip,
                    primaryGreen = primaryGreen,
                    lightGreen = lightGreen
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

data class PreventionTip(
    val title: String,
    val description: String,
    val icon: ImageVector
)

@Composable
private fun PreventionHeader(
    primaryGreen: Color,
    secondaryGreen: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Brush.verticalGradient(listOf(primaryGreen, secondaryGreen)))
                .padding(24.dp)
        ) {
            Column {
                Text(
                    text = "Stay Ahead of Diseases",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Preventive measures are the best way to ensure crop health.",
                    color = Color.White.copy(alpha = 0.85f),
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
private fun PreventionCard(
    tip: PreventionTip,
    primaryGreen: Color,
    lightGreen: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(48.dp),
                shape = CircleShape,
                color = lightGreen
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = tip.icon,
                        contentDescription = null,
                        tint = primaryGreen,
                        modifier = Modifier.size(26.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = tip.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = primaryGreen
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = tip.description,
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    lineHeight = 20.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreventionScreenPreview() {
    PreventionScreen(rememberNavController())
}
