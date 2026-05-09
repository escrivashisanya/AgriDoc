package com.josemaria.agridoc.ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.josemaria.agridoc.ui.components.AppScaffold

@Composable
fun OtpVerificationScreen(navController: NavController) {
    AppScaffold(title = "Verify OTP", navController = navController) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("OTP Verification Screen (Mock)")
            Button(onClick = { navController.popBackStack() }) {
                Text("Back")
            }
        }
    }
}

@Composable
fun ChangePasswordScreen(navController: NavController) {
    AppScaffold(title = "Change Password", navController = navController) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Change Password Screen (Mock)")
            Button(onClick = { navController.popBackStack() }) {
                Text("Back")
            }
        }
    }
}

@Composable
fun MarketplaceScreen(navController: NavController) {
    AppScaffold(title = "Marketplace", navController = navController) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Marketplace Screen (Mock)")
            Button(onClick = { navController.popBackStack() }) {
                Text("Back")
            }
        }
    }
}
