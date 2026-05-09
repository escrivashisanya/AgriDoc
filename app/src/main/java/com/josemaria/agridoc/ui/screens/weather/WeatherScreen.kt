package com.josemaria.agridoc.ui.screens.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    navController: NavController
) {
    val primaryGreen = Color(0xFF1B5E20)
    val secondaryGreen = Color(0xFF4CAF50)
    val backgroundColor = Color(0xFFF7FFF8)

    Scaffold(
        containerColor = backgroundColor,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Weather Forecast",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = primaryGreen
                )
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {

            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            listOf(primaryGreen, secondaryGreen)
                        )
                    )
                    .padding(24.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Nairobi, Kenya",
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "☀️",
                        fontSize = 72.sp
                    )

                    Text(
                        text = "27°C",
                        color = Color.White,
                        fontSize = 54.sp,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Text(
                        text = "Sunny",
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 20.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Weather Stats
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                WeatherStatCard(
                    modifier = Modifier.weight(1f),
                    icon = "💧",
                    title = "Humidity",
                    value = "65%"
                )

                WeatherStatCard(
                    modifier = Modifier.weight(1f),
                    icon = "🌬️",
                    title = "Wind",
                    value = "12 km/h"
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                WeatherStatCard(
                    modifier = Modifier.weight(1f),
                    icon = "🌡️",
                    title = "Feels Like",
                    value = "29°C"
                )

                WeatherStatCard(
                    modifier = Modifier.weight(1f),
                    icon = "☔",
                    title = "Rain Chance",
                    value = "15%"
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Farming Recommendation
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Agriculture,
                            contentDescription = null,
                            tint = primaryGreen
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = "Farming Recommendation",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = primaryGreen
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Excellent conditions for crop spraying and irrigation today. Low rain probability and moderate wind make it ideal for field operations.",
                        fontSize = 15.sp,
                        lineHeight = 24.sp,
                        color = Color.DarkGray
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 7 Day Forecast
            Text(
                text = "7-Day Forecast",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = primaryGreen,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            repeat(7) { index ->
                ForecastItem(
                    day = listOf(
                        "Monday",
                        "Tuesday",
                        "Wednesday",
                        "Thursday",
                        "Friday",
                        "Saturday",
                        "Sunday"
                    )[index],
                    temperature = "${24 + index}°C",
                    condition = if (index % 2 == 0) "Sunny" else "Cloudy"
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun WeatherStatCard(
    modifier: Modifier = Modifier,
    icon: String,
    title: String,
    value: String
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = icon,
                fontSize = 32.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                color = Color.Gray,
                fontSize = 14.sp
            )

            Text(
                text = value,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun ForecastItem(
    day: String,
    temperature: String,
    condition: String
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
            Text(
                text = if (condition == "Sunny") "☀️" else "☁️",
                fontSize = 28.sp
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = day,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Text(
                    text = condition,
                    color = Color.Gray
                )
            }

            Text(
                text = temperature,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFF1B5E20)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherScreenPreview() {
    WeatherScreen(rememberNavController())
}
