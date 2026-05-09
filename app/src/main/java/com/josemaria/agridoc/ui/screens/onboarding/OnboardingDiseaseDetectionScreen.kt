package com.josemaria.agridoc.ui.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.josemaria.agridoc.navigation.ROUT_ONBOARDING_TREATMENT

@Composable
fun OnboardingDiseaseDetectionScreen(
    navController: NavController
) {
    val primaryGreen = Color(0xFF1B5E20)
    val secondaryGreen = Color(0xFF4CAF50)

    Scaffold(
        containerColor = Color.White
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.White,
                            Color(0xFFF1FFF4)
                        )
                    )
                )
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    modifier = Modifier.size(220.dp),
                    shape = CircleShape,
                    color = secondaryGreen.copy(alpha = 0.12f)
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "📸",
                            fontSize = 110.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = "AI Disease Detection",
                    fontSize = 34.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = primaryGreen,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Capture a photo of your crop and our advanced AI instantly detects diseases, pests, and nutrient deficiencies with high precision.",
                    fontSize = 18.sp,
                    lineHeight = 30.sp,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(3) { index ->
                        Box(
                            modifier = Modifier
                                .padding(6.dp)
                                .size(
                                    if (index == 1) 28.dp else 12.dp,
                                    12.dp
                                )
                                .background(
                                    color = if (index == 1)
                                        primaryGreen
                                    else
                                        Color.LightGray,
                                    shape = RoundedCornerShape(50)
                                )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = {
                        navController.navigate(ROUT_ONBOARDING_TREATMENT)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = primaryGreen
                    )
                ) {
                    Text(
                        text = "Next",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Icon(
                        Icons.Default.ArrowForward,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingDiseaseDetectionScreenPreview() {
    OnboardingDiseaseDetectionScreen(rememberNavController())
}
