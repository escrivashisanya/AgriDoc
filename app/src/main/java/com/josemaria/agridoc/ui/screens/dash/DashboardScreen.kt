package com.josemaria.agridoc.ui.screens.dash

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import com.josemaria.agridoc.navigation.*
import com.josemaria.agridoc.ui.theme.AgriDocTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    navController: NavController,
    userName: String = "Farmer"
) {
    val primaryGreen = Color(0xFF2E7D32)
    val lightGreen = Color(0xFFE8F5E9)

    val quickActions = listOf(
        DashboardAction("Scan Crop", Icons.Default.CameraAlt, ROUT_CAMERA),
        DashboardAction("History", Icons.Default.History, ROUT_HISTORY),
        DashboardAction("Treatments", Icons.Default.MedicalServices, ROUT_TREATMENT),
        DashboardAction("Weather", Icons.Default.Cloud, ROUT_WEATHER),
        DashboardAction("Marketplace", Icons.Default.Store, ROUT_MARKETPLACE),
        DashboardAction("Profile", Icons.Default.Person, ROUT_PROFILE)
    )

    Scaffold(
        containerColor = Color(0xFFF8FFF8),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = primaryGreen,
                    titleContentColor = Color.White
                ),
                title = {
                    Text(
                        "AgriDoc",
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            Icons.Default.Notifications,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
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
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                Card(
                    shape = RoundedCornerShape(28.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent
                    ),
                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Brush.linearGradient(
                                    listOf(
                                        Color(0xFF1B5E20),
                                        Color(0xFF4CAF50)
                                    )
                                )
                            )
                            .padding(24.dp)
                    ) {
                        Column {
                            Text(
                                "Welcome Back, $userName 👋",
                                color = Color.White,
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                "Protect your crops with AI-powered disease detection and expert recommendations.",
                                color = Color.White.copy(alpha = 0.95f),
                                fontSize = 15.sp
                            )

                            Spacer(modifier = Modifier.height(24.dp))

                            Button(
                                onClick = {
                                    navController.navigate(ROUT_CAMERA)
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White,
                                    contentColor = primaryGreen
                                ),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Icon(Icons.Default.CameraAlt, null)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Start Scanning")
                            }
                        }
                    }
                }
            }

            item {
                Text(
                    "Quick Actions",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = primaryGreen
                )
            }

            item {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.height(420.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    userScrollEnabled = false
                ) {
                    items(quickActions) { action ->
                        QuickActionCard(action) {
                            navController.navigate(action.route)
                        }
                    }
                }
            }

            item {
                Text(
                    "Farm Insights",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = primaryGreen
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    StatCard(
                        modifier = Modifier.weight(1f),
                        title = "Scans",
                        value = "128",
                        icon = Icons.Default.DocumentScanner
                    )

                    StatCard(
                        modifier = Modifier.weight(1f),
                        title = "Healthy",
                        value = "94%",
                        icon = Icons.Default.Eco
                    )
                }
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = lightGreen
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Text(
                            "Today's Tip 🌱",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = primaryGreen
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            "Regularly inspect the underside of leaves where most fungal infections begin.",
                            fontSize = 15.sp
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

data class DashboardAction(
    val title: String,
    val icon: ImageVector,
    val route: String
)

@Composable
fun QuickActionCard(
    action: DashboardAction,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                action.icon,
                contentDescription = null,
                tint = Color(0xFF2E7D32),
                modifier = Modifier.size(42.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                action.title,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun StatCard(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    icon: ImageVector
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(22.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                icon,
                contentDescription = null,
                tint = Color(0xFF2E7D32),
                modifier = Modifier.size(34.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                value,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Text(title)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    AgriDocTheme {
        DashboardScreen(navController = rememberNavController())
    }
}
