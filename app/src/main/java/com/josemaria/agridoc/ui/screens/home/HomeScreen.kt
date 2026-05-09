package com.josemaria.agridoc.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.josemaria.agridoc.navigation.*
import com.josemaria.agridoc.ui.components.AppScaffold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    userName: String = "Farmer"
) {
    val primaryGreen = Color(0xFF2E7D32)
    val lightGreen = Color(0xFF66BB6A)

    val quickActions = listOf(
        QuickAction("Scan Crop", Icons.Default.CameraAlt, ROUT_CAMERA),
        QuickAction("History", Icons.Default.History, ROUT_HISTORY),
        QuickAction("Diseases", Icons.Default.BugReport, ROUT_DISEASE_DETAILS),
        QuickAction("Weather", Icons.Default.Cloud, ROUT_WEATHER)
    )

    AppScaffold(
        title = "AgriDoc",
        navController = navController,
        actions = {
            IconButton(onClick = { /* Notifications */ }) {
                BadgedBox(badge = {
                    Badge {
                        Text("3")
                    }
                }) {
                    Icon(Icons.Default.Notifications, null, tint = Color.White)
                }
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { navController.navigate(ROUT_SCAN) },
                containerColor = primaryGreen,
                contentColor = Color.White,
                icon = {
                    Icon(Icons.Default.CameraAlt, null)
                },
                text = {
                    Text("Scan Plant")
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item { Spacer(modifier = Modifier.height(8.dp)) }

            item {
                HeroSection(primaryGreen, lightGreen)
            }

            item {
                StatisticsSection(primaryGreen)
            }

            item {
                Text(
                    text = "Quick Actions",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                QuickActionsGrid(
                    actions = quickActions,
                    navController = navController,
                    primaryGreen = primaryGreen
                )
            }

            item {
                RecentScansSection(primaryGreen)
            }

            item {
                FarmingTipsSection(lightGreen)
            }

            item { Spacer(modifier = Modifier.height(90.dp)) }
        }
    }
}

data class QuickAction(
    val title: String,
    val icon: ImageVector,
    val route: String
)

@Composable
private fun HeroSection(primary: Color, secondary: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .background(
                    Brush.linearGradient(
                        listOf(primary, secondary)
                    )
                )
                .padding(24.dp)
        ) {
            Column {
                Text(
                    text = "Protect Your Crops with AI",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Instant disease detection, treatment recommendations, and crop monitoring at your fingertips.",
                    color = Color.White.copy(alpha = 0.95f)
                )
            }
        }
    }
}

@Composable
private fun StatisticsSection(primary: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        StatCard("128", "Scans", Icons.Default.DocumentScanner, primary, Modifier.weight(1f))
        StatCard("94%", "Accuracy", Icons.Default.Analytics, primary, Modifier.weight(1f))
    }
}

@Composable
private fun StatCard(
    value: String,
    label: String,
    icon: ImageVector,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(icon, null, tint = color, modifier = Modifier.size(34.dp))
            Spacer(modifier = Modifier.height(12.dp))
            Text(value, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.headlineMedium)
            Text(label, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
private fun QuickActionsGrid(
    actions: List<QuickAction>,
    navController: NavController,
    primaryGreen: Color
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.height(260.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        userScrollEnabled = false
    ) {
        items(actions) { action ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate(action.route) },
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(primaryGreen.copy(alpha = 0.12f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            action.icon,
                            null,
                            tint = primaryGreen,
                            modifier = Modifier.size(30.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = action.title,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
private fun RecentScansSection(primary: Color) {
    Card(
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = "Recent Diagnosis",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            ListItem(
                headlineContent = { Text("Tomato Early Blight") },
                supportingContent = { Text("Confidence: 96.4%") },
                leadingContent = {
                    Icon(Icons.Default.LocalFlorist, null, tint = primary)
                }
            )
        }
    }
}

@Composable
private fun FarmingTipsSection(accent: Color) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = accent.copy(alpha = 0.12f)
        )
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.TipsAndUpdates,
                null,
                tint = accent,
                modifier = Modifier.size(42.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = "Daily Farming Tip",
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Inspect leaves early in the morning for the best disease detection results."
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}
