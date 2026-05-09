package com.josemaria.agridoc.ui.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.josemaria.agridoc.ui.components.AppScaffold

@Composable
fun AboutScreen(navController: NavController) {
    AppScaffold(
        title = "About AgriDoc",
        navController = navController
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier.size(120.dp),
                shape = CircleShape,
                color = Color(0xFF1B5E20).copy(alpha = 0.1f)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(text = "🌿", fontSize = 60.sp)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "AgriDoc",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1B5E20)
            )

            Text(
                text = "Version 1.0.0",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "AgriDoc is an advanced agricultural assistant powered by Artificial Intelligence. Our mission is to empower farmers with real-time crop disease diagnosis, expert treatment recommendations, and localized weather insights to ensure food security and sustainable farming practices.",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                lineHeight = 26.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            HorizontalDivider()

            Spacer(modifier = Modifier.height(24.dp))

            AboutInfoRow(label = "Developed by", value = "AgriDoc Team")
            AboutInfoRow(label = "Contact", value = "support@agridoc.com")
            AboutInfoRow(label = "Website", value = "www.agridoc.com")

            Spacer(modifier = Modifier.height(48.dp))

            Text(
                text = "© 2024 AgriDoc Inc. All rights reserved.",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}

@Composable
private fun AboutInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontWeight = FontWeight.Medium, color = Color.Gray)
        Text(text = value, fontWeight = FontWeight.Bold, color = Color(0xFF1B5E20))
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    AboutScreen(rememberNavController())
}
