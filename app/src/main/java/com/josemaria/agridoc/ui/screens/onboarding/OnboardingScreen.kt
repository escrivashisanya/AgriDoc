package com.josemaria.agridoc.ui.screens.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.josemaria.agridoc.navigation.ROUT_LOGIN
import com.josemaria.agridoc.navigation.ROUT_ONBOARDING
import com.josemaria.agridoc.navigation.ROUT_ONBOARDING_DISEASE
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    navController: NavController
) {
    val pages = listOf(
        OnboardingPage(
            title = "AI Plant Disease Detection",
            description = "Instantly identify crop diseases by simply taking a photo using advanced artificial intelligence.",
            icon = Icons.Default.CameraAlt,
            gradient = listOf(Color(0xFF1B5E20), Color(0xFF43A047))
        ),
        OnboardingPage(
            title = "Accurate Analysis & Insights",
            description = "Receive real-time diagnosis, confidence scores, and detailed crop health analytics.",
            icon = Icons.Default.Analytics,
            gradient = listOf(Color(0xFF2E7D32), Color(0xFF66BB6A))
        ),
        OnboardingPage(
            title = "Treatment Recommendations",
            description = "Get expert treatment plans, pesticide recommendations, and prevention strategies instantly.",
            icon = Icons.Default.LocalHospital,
            gradient = listOf(Color(0xFF388E3C), Color(0xFF81C784))
        )
    )

    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()

    Scaffold(
        containerColor = Color(0xFFF7FAF7)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->
                OnboardingPageContent(page = pages[page])
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PagerIndicator(
                    pageCount = pages.size,
                    currentPage = pagerState.currentPage
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {
                        if (pagerState.currentPage == pages.lastIndex) {
                            navController.navigate(ROUT_ONBOARDING_DISEASE)
                        } else {
                            scope.launch {
                                pagerState.animateScrollToPage(
                                    pagerState.currentPage + 1
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2E7D32)
                    )
                ) {
                    Text(
                        text = if (pagerState.currentPage == pages.lastIndex)
                            "Get Started"
                        else
                            "Continue",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                TextButton(
                    onClick = {
                        navController.navigate(ROUT_LOGIN) {
                            popUpTo(ROUT_ONBOARDING) { inclusive = true }
                        }
                    }
                ) {
                    Text(
                        text = "Skip",
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

data class OnboardingPage(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val gradient: List<Color>
)

@Composable
private fun OnboardingPageContent(
    page: OnboardingPage
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier.size(280.dp),
            shape = RoundedCornerShape(40.dp),
            elevation = CardDefaults.cardElevation(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.linearGradient(page.gradient)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = page.icon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(120.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = page.title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            color = Color(0xFF1B5E20)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = page.description,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = Color.DarkGray,
            lineHeight = MaterialTheme.typography.bodyLarge.lineHeight * 1.3
        )
    }
}

@Composable
private fun PagerIndicator(
    pageCount: Int,
    currentPage: Int
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { index ->
            Box(
                modifier = Modifier
                    .size(
                        width = if (index == currentPage) 32.dp else 12.dp,
                        height = 12.dp
                    )
                    .clip(CircleShape)
                    .background(
                        if (index == currentPage)
                            Color(0xFF2E7D32)
                        else
                            Color.LightGray
                    )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen(rememberNavController())
}
