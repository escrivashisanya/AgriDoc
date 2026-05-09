package com.josemaria.agridoc.ui.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowBack
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
import com.josemaria.agridoc.navigation.ROUT_LOGIN
import com.josemaria.agridoc.navigation.ROUT_ONBOARDING

@Composable
fun OnboardingTreatmentScreen(
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
                            text = "🌿",
                            fontSize = 110.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = "Smart Treatment Solutions",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = primaryGreen,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Get expert treatment recommendations, prevention strategies, and crop management solutions tailored specifically for your farm.",
                    fontSize = 18.sp,
                    lineHeight = 30.sp,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(28.dp))

                FeatureChip("Instant Recommendations")
                FeatureChip("Prevention Tips")
                FeatureChip("Farmer-Friendly Guidance")
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
                                    if (index == 2) 28.dp else 12.dp,
                                    12.dp
                                )
                                .background(
                                    color = if (index == 2)
                                        primaryGreen
                                    else
                                        Color.LightGray,
                                    shape = RoundedCornerShape(50)
                                )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            navController.popBackStack()
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(58.dp),
                        shape = RoundedCornerShape(18.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = primaryGreen
                        )
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = "Back",
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Button(
                        onClick = {
                            navController.navigate(ROUT_LOGIN) {
                                popUpTo(ROUT_ONBOARDING) {
                                    inclusive = true
                                }
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(58.dp),
                        shape = RoundedCornerShape(18.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = primaryGreen
                        )
                    ) {
                        Text(
                            text = "Get Started",
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Icon(
                            Icons.Default.ArrowForward,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FeatureChip(text: String) {
    Surface(
        modifier = Modifier.padding(vertical = 6.dp),
        shape = RoundedCornerShape(50.dp),
        color = Color(0xFFE8F5E9)
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = 18.dp,
                vertical = 10.dp
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = Color(0xFF1B5E20),
                modifier = Modifier.size(18.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = text,
                color = Color(0xFF1B5E20),
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingTreatmentScreenPreview() {
    OnboardingTreatmentScreen(rememberNavController())
}
