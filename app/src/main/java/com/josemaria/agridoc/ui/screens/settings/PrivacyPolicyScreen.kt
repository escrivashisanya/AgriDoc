package com.josemaria.agridoc.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import com.josemaria.agridoc.ui.components.AppScaffold

@Composable
fun PrivacyPolicyScreen(navController: NavController) {
    val primaryGreen = Color(0xFF1B5E20)
    val secondaryGreen = Color(0xFF4CAF50)

    AppScaffold(
        title = "Privacy Policy",
        navController = navController
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {

            // Header Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    primaryGreen,
                                    secondaryGreen
                                )
                            ),
                            shape = MaterialTheme.shapes.extraLarge
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Security,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(72.dp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Your Privacy Matters",
                            color = Color.White,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "AgriDoc",
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 18.sp
                        )
                    }
                }
            }

            // Content
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = MaterialTheme.shapes.extraLarge,
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    PrivacySection(
                        title = "1. Information We Collect",
                        content = "We collect account details, farm information, crop images, and usage analytics to provide accurate disease diagnosis and personalized farming recommendations."
                    )

                    PrivacySection(
                        title = "2. How We Use Your Data",
                        content = "Your information helps us improve plant disease detection, generate treatment recommendations, enhance weather forecasts, and deliver a better farming experience."
                    )

                    PrivacySection(
                        title = "3. Image Analysis",
                        content = "Plant images uploaded through the application are securely processed using AI models. Images may be stored temporarily to improve diagnostic accuracy."
                    )

                    PrivacySection(
                        title = "4. Data Security",
                        content = "We employ industry-standard encryption, secure authentication, and cloud protection mechanisms to safeguard your personal and agricultural data."
                    )

                    PrivacySection(
                        title = "5. Third-Party Services",
                        content = "AgriDoc may use secure third-party services such as Firebase, TensorFlow Lite, and weather APIs to deliver core application features."
                    )

                    PrivacySection(
                        title = "6. Your Rights",
                        content = "You may update, download, or delete your personal information at any time through your profile settings or by contacting support."
                    )

                    PrivacySection(
                        title = "7. Contact Us",
                        content = "For questions regarding this privacy policy, contact us at support@agridoc.com."
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Last Updated: April 2026",
                        color = Color.Gray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun PrivacySection(
    title: String,
    content: String
) {
    Column(
        modifier = Modifier.padding(bottom = 24.dp)
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B5E20)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = content,
            fontSize = 16.sp,
            lineHeight = 26.sp,
            color = Color.DarkGray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrivacyPolicyScreenPreview() {
    PrivacyPolicyScreen(rememberNavController())
}
