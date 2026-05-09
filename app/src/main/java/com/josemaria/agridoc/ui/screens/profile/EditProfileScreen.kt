package com.josemaria.agridoc.ui.screens.profile

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.josemaria.agridoc.ui.components.AppScaffold
import com.josemaria.agridoc.ui.theme.AgriDocTheme

@Composable
fun EditProfileScreen(
    navController: NavController
) {
    val primaryGreen = Color(0xFF2E7D32)
    val secondaryGreen = Color(0xFF66BB6A)

    val context = LocalContext.current

    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var farmName by remember { mutableStateOf("") }

    AppScaffold(
        title = "Edit Profile",
        navController = navController
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            ProfileImageSection(primaryGreen, secondaryGreen)

            ProfileTextField(fullName, { fullName = it }, "Full Name")
            ProfileTextField(email, { email = it }, "Email Address")
            ProfileTextField(phone, { phone = it }, "Phone Number")
            ProfileTextField(location, { location = it }, "Location")
            ProfileTextField(farmName, { farmName = it }, "Farm Name")

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    // TODO: Implement save logic without direct Firebase calls in UI if possible, 
                    // or ensure Firebase is initialized.
                    Toast.makeText(context, "Profile Updated", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp),
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryGreen
                )
            ) {
                Icon(Icons.Default.Save, contentDescription = null)
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Save Changes",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun ProfileImageSection(primaryGreen: Color, secondaryGreen: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(
                    Brush.linearGradient(
                        listOf(primaryGreen, secondaryGreen)
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(70.dp)
            )
            
            IconButton(
                onClick = { /* TODO: Change Image */ },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(36.dp)
                    .background(Color.White, CircleShape)
                    .border(1.dp, primaryGreen, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.CameraAlt,
                    contentDescription = null,
                    tint = primaryGreen,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun ProfileTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF2E7D32),
            focusedLabelColor = Color(0xFF2E7D32)
        ),
        singleLine = true
    )
}

@Preview(showBackground = true)
@Composable
fun EditProfileScreenPreview() {
    AgriDocTheme {
        EditProfileScreen(navController = rememberNavController())
    }
}
