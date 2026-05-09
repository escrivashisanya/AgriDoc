package com.josemaria.agridoc.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
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

@Composable
fun ProfileScreen(
    navController: NavController,
    userName: String = "Josemaria",
    userEmail: String = "josemaria@example.com"
) {
    val primaryGreen = Color(0xFF2E7D32)
    val secondaryGreen = Color(0xFF66BB6A)
    val backgroundColor = Color(0xFFF7FAF7)

    AppScaffold(
        title = "Profile",
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
                Spacer(modifier = Modifier.height(12.dp))
            }

            item {
                ProfileHeader(
                    userName = userName,
                    userEmail = userEmail,
                    primaryGreen = primaryGreen,
                    secondaryGreen = secondaryGreen
                )
            }

            item {
                StatisticsSection(primaryGreen)
            }

            item {
                MenuSection(
                    navController = navController,
                    primaryGreen = primaryGreen
                )
            }

            item {
                LogoutButton(primaryGreen)
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
private fun ProfileHeader(
    userName: String,
    userEmail: String,
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
                Box(
                    modifier = Modifier
                        .size(110.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.25f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(60.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = userName,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = userEmail,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White.copy(alpha = 0.9f)
                )
            }
        }
    }
}

@Composable
private fun StatisticsSection(primaryGreen: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        StatCard(
            title = "128",
            subtitle = "Scans",
            icon = Icons.Default.DocumentScanner,
            color = primaryGreen,
            modifier = Modifier.weight(1f)
        )

        StatCard(
            title = "94%",
            subtitle = "Accuracy",
            icon = Icons.Default.Analytics,
            color = primaryGreen,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun StatCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun MenuSection(
    navController: NavController,
    primaryGreen: Color
) {
    Card(
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column {
            ProfileMenuItem(
                icon = Icons.Default.Person,
                title = "Edit Profile",
                onClick = { navController.navigate(ROUT_EDIT_PROFILE) },
                color = primaryGreen
            )

            ProfileMenuItem(
                icon = Icons.Default.History,
                title = "Scan History",
                onClick = { navController.navigate(ROUT_HISTORY) },
                color = primaryGreen
            )

            ProfileMenuItem(
                icon = Icons.Default.Settings,
                title = "Settings",
                onClick = { navController.navigate(ROUT_SETTINGS) },
                color = primaryGreen
            )

            ProfileMenuItem(
                icon = Icons.Default.Help,
                title = "Help & Support",
                onClick = { navController.navigate(ROUT_HELP_SUPPORT) },
                color = primaryGreen
            )

            ProfileMenuItem(
                icon = Icons.Default.Info,
                title = "About App",
                onClick = { navController.navigate(ROUT_ABOUT) },
                color = primaryGreen
            )
        }
    }
}

@Composable
private fun ProfileMenuItem(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit,
    color: Color
) {
    ListItem(
        modifier = Modifier.clickable(onClick = onClick),
        headlineContent = {
            Text(
                text = title,
                fontWeight = FontWeight.Medium
            )
        },
        leadingContent = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color
            )
        },
        trailingContent = {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null
            )
        }
    )

    HorizontalDivider()
}

@Composable
private fun LogoutButton(primaryGreen: Color) {
    Button(
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp),
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = primaryGreen
        )
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.Logout,
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = "Logout",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(rememberNavController())
}
