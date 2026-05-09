package com.josemaria.agridoc.ui.screens.scan

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
// import com.google.firebase.database.FirebaseDatabase
import com.josemaria.agridoc.navigation.ROUT_CAMERA
import com.josemaria.agridoc.navigation.ROUT_IMAGE_PREVIEW
import com.josemaria.agridoc.ui.components.AppScaffold

@Composable
fun ScanScreen(navController: NavController) {

    val primaryGreen = Color(0xFF1B5E20)

    // 🔥 Firebase instances (READY FOR USE)
    // val auth = FirebaseAuth.getInstance()
    // val db = FirebaseDatabase.getInstance().reference

    AppScaffold(
        title = "Plant Scanner",
        navController = navController
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Scan Your Crop",
                style = MaterialTheme.typography.headlineMedium,
                color = primaryGreen,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Take a photo of the affected area to identify the disease and get treatment recommendations.",
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(48.dp))

            // 📸 CAMERA OPTION
            ScanOptionCard(
                title = "Use Camera",
                description = "Take a new photo with your device camera.",
                icon = Icons.Default.CameraAlt,
                color = primaryGreen,
                onClick = {
                    navController.navigate(ROUT_CAMERA)
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 🖼️ GALLERY OPTION
            ScanOptionCard(
                title = "Upload from Gallery",
                description = "Choose an existing photo from your storage.",
                icon = Icons.Default.AddPhotoAlternate,
                color = Color(0xFF4CAF50),
                onClick = {
                    navController.navigate(ROUT_IMAGE_PREVIEW)
                }
            )
        }
    }
}

@Composable
fun ScanOptionCard(
    title: String,
    description: String,
    icon: ImageVector,
    color: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(56.dp),
                shape = MaterialTheme.shapes.medium,
                color = color.copy(alpha = 0.1f)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = color,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.LightGray
            )
        }
    }
}
