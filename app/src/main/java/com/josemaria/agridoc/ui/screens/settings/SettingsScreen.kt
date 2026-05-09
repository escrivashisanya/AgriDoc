package com.josemaria.agridoc.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.josemaria.agridoc.navigation.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController
) {
    var darkMode by remember { mutableStateOf(false) }
    var notificationsEnabled by remember { mutableStateOf(true) }
    var biometricEnabled by remember { mutableStateOf(false) }

    val primaryGreen = Color(0xFF1B5E20)
    val secondaryGreen = Color(0xFF4CAF50)
    val backgroundColor = Color(0xFFF5FFF7)

    Scaffold(
        containerColor = backgroundColor,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Settings",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = primaryGreen
                )
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {

            // Header Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                primaryGreen,
                                secondaryGreen
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Surface(
                        modifier = Modifier.size(90.dp),
                        shape = CircleShape,
                        color = Color.White.copy(alpha = 0.2f)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(48.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Application Settings",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Text(
                        text = "Customize your experience",
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Preferences
            SettingsSection(title = "Preferences")

            SettingsSwitchItem(
                title = "Dark Mode",
                subtitle = "Enable dark theme",
                icon = Icons.Default.DarkMode,
                checked = darkMode,
                onCheckedChange = { darkMode = it }
            )

            SettingsSwitchItem(
                title = "Notifications",
                subtitle = "Receive alerts and updates",
                icon = Icons.Default.Notifications,
                checked = notificationsEnabled,
                onCheckedChange = { notificationsEnabled = it }
            )

            SettingsSwitchItem(
                title = "Biometric Login",
                subtitle = "Use fingerprint authentication",
                icon = Icons.Default.Fingerprint,
                checked = biometricEnabled,
                onCheckedChange = { biometricEnabled = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Account
            SettingsSection(title = "Account")

            SettingsNavigationItem(
                title = "Edit Profile",
                subtitle = "Update your personal information",
                icon = Icons.Default.Person,
                onClick = {
                    navController.navigate(ROUT_EDIT_PROFILE)
                }
            )

            SettingsNavigationItem(
                title = "Change Password",
                subtitle = "Update your account password",
                icon = Icons.Default.Lock,
                onClick = {
                    navController.navigate(ROUT_CHANGE_PASSWORD)
                }
            )

            SettingsNavigationItem(
                title = "Language",
                subtitle = "English",
                icon = Icons.Default.Language,
                onClick = { }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Support
            SettingsSection(title = "Support")

            SettingsNavigationItem(
                title = "Help Center",
                subtitle = "Get assistance",
                icon = Icons.Default.Help,
                onClick = {
                    navController.navigate(ROUT_HELP_SUPPORT)
                }
            )

            SettingsNavigationItem(
                title = "Privacy Policy",
                subtitle = "Read our terms",
                icon = Icons.Default.Security,
                onClick = {
                    navController.navigate(ROUT_PRIVACY_POLICY)
                }
            )

            SettingsNavigationItem(
                title = "About App",
                subtitle = "Version 1.0.0",
                icon = Icons.Default.Info,
                onClick = {
                    navController.navigate(ROUT_ABOUT)
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Logout Button
            Button(
                onClick = {
                    navController.navigate(ROUT_LOGIN) {
                        popUpTo(0)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(58.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red.copy(alpha = 0.85f)
                ),
                shape = RoundedCornerShape(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Logout,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Logout",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun SettingsSection(title: String) {
    Text(
        text = title,
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF1B5E20)
    )
}

@Composable
fun SettingsSwitchItem(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFF1B5E20),
                modifier = Modifier.size(30.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )

                Text(
                    text = subtitle,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }

            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange
            )
        }
    }
}

@Composable
fun SettingsNavigationItem(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFF1B5E20),
                modifier = Modifier.size(30.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )

                Text(
                    text = subtitle,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(rememberNavController())
}
