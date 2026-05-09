package com.josemaria.agridoc.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.josemaria.agridoc.navigation.Screen
import com.josemaria.agridoc.ui.theme.PrimaryGreen
import com.josemaria.agridoc.ui.theme.SurfaceWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    title: String,
    navController: NavController,
    actions: @Composable RowScope.() -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        title,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                actions = actions,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrimaryGreen
                )
            )
        },
        floatingActionButton = floatingActionButton,
        bottomBar = {
            NavigationBar(
                containerColor = SurfaceWhite
            ) {
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Screen.Home) },
                    icon = { Icon(Icons.Default.Home, null) },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Screen.Scan) },
                    icon = { Icon(Icons.Default.CameraAlt, null) },
                    label = { Text("Scan") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Screen.History) },
                    icon = { Icon(Icons.Default.History, null) },
                    label = { Text("History") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Screen.Profile) },
                    icon = { Icon(Icons.Default.Person, null) },
                    label = { Text("Profile") }
                )
            }
        }
    ) { padding ->
        content(padding)
    }
}
