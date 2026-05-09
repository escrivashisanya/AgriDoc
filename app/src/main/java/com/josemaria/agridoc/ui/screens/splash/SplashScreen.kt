package com.josemaria.agridoc.ui.screens.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
import com.josemaria.agridoc.navigation.ROUT_ONBOARDING
import com.josemaria.agridoc.navigation.ROUT_SPLASH
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    var startAnimation by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        startAnimation = true
        delay(3000)
        navController.navigate(ROUT_ONBOARDING) {
            popUpTo(ROUT_SPLASH) { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0D5C2F),
                        Color(0xFF2E7D32),
                        Color(0xFF4CAF50)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(
                visible = startAnimation,
                enter = fadeIn() + scaleIn(initialScale = 0.5f)
            ) {
                Text(
                    text = "🌿",
                    fontSize = 120.sp
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            AnimatedVisibility(
                visible = startAnimation,
                enter = fadeIn()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "AgriDoc",
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 42.sp,
                            color = Color.White
                        ),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "AI-Powered Plant Disease Detection",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 18.sp
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        Text(
            text = "Empowering Modern Agriculture",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp),
            color = Color.White.copy(alpha = 0.85f),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(rememberNavController())
}
