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
fun HelpSupportScreen(
    navController: NavController
) {
    val primaryGreen = Color(0xFF1B5E20)
    val secondaryGreen = Color(0xFF4CAF50)

    AppScaffold(
        title = "Help & Support",
        navController = navController
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {

            // Hero Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(primaryGreen, secondaryGreen)
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Surface(
                        modifier = Modifier.size(100.dp),
                        shape = CircleShape,
                        color = Color.White.copy(alpha = 0.2f)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.SupportAgent,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(55.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "We're Here To Help",
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Text(
                        text = "24/7 Customer Support",
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 18.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Quick Actions
            SupportOptionCard(
                title = "Live Chat",
                subtitle = "Chat instantly with our support team",
                icon = Icons.Default.Chat,
                primaryGreen = primaryGreen,
                onClick = { }
            )

            SupportOptionCard(
                title = "Email Support",
                subtitle = "support@agridoc.com",
                icon = Icons.Default.Email,
                primaryGreen = primaryGreen,
                onClick = { }
            )

            SupportOptionCard(
                title = "Call Us",
                subtitle = "+254 700 123 456",
                icon = Icons.Default.Phone,
                primaryGreen = primaryGreen,
                onClick = { }
            )

            SupportOptionCard(
                title = "FAQs",
                subtitle = "Browse common questions",
                icon = Icons.Default.QuestionAnswer,
                primaryGreen = primaryGreen,
                onClick = { }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // FAQ Section
            Text(
                text = "Frequently Asked Questions",
                modifier = Modifier.padding(horizontal = 20.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = primaryGreen
            )

            Spacer(modifier = Modifier.height(12.dp))

            FAQCard(
                question = "How does disease detection work?",
                answer = "Simply upload a clear image of your crop, and our AI instantly analyzes it for possible diseases."
            )

            FAQCard(
                question = "Is the diagnosis accurate?",
                answer = "Our AI model is trained using thousands of crop disease images for high accuracy and reliability."
            )

            FAQCard(
                question = "Can I use the app offline?",
                answer = "Basic features work offline, but AI diagnosis and weather forecasts require internet access."
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun SupportOptionCard(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    primaryGreen: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(22.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(56.dp),
                shape = CircleShape,
                color = primaryGreen.copy(alpha = 0.1f)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        icon,
                        contentDescription = null,
                        tint = primaryGreen,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = subtitle,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }

            Icon(
                Icons.Default.ChevronRight,
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun FAQCard(
    question: String,
    answer: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = question,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1B5E20)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = answer,
                fontSize = 15.sp,
                color = Color.DarkGray,
                lineHeight = 24.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HelpSupportScreenPreview() {
    HelpSupportScreen(rememberNavController())
}
