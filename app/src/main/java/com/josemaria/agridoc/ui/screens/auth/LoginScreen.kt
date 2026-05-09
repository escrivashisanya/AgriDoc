package com.josemaria.agridoc.ui.screens.auth

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.josemaria.agridoc.navigation.*

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val primaryGreen = Color(0xFF1B5E20)
    val secondaryGreen = Color(0xFF4CAF50)
    val backgroundColor = Color(0xFFF7FFF8)

    val auth = FirebaseAuth.getInstance()
    val database = FirebaseDatabase.getInstance().reference

    val context = LocalContext.current

    Scaffold(
        containerColor = backgroundColor
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
        ) {

            // ======= (UNCHANGED UI ABOVE) =======

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(primaryGreen, secondaryGreen)
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Surface(
                        modifier = Modifier.size(110.dp),
                        shape = CircleShape,
                        color = Color.White.copy(alpha = 0.15f)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text("🌿", fontSize = 52.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Welcome Back",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )

                    Text(
                        text = "AgriDoc",
                        color = Color.White.copy(alpha = 0.95f),
                        fontSize = 18.sp
                    )
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-35).dp)
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(30.dp),
                elevation = CardDefaults.cardElevation(12.dp)
            ) {

                Column(
                    modifier = Modifier.padding(28.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {

                    Text(
                        text = "Login to Continue",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = primaryGreen,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email Address") },
                        leadingIcon = { Icon(Icons.Default.Email, null) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        leadingIcon = { Icon(Icons.Default.Lock, null) },
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    if (passwordVisible)
                                        Icons.Default.Visibility
                                    else
                                        Icons.Default.VisibilityOff,
                                    null
                                )
                            }
                        },
                        visualTransformation = if (passwordVisible)
                            VisualTransformation.None
                        else PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        singleLine = true
                    )

                    Text(
                        text = "Forgot Password?",
                        color = primaryGreen,
                        modifier = Modifier
                            .align(Alignment.End)
                            .clickable {
                                navController.navigate(ROUT_FORGOT_PASSWORD)
                            }
                    )

                    // 🔥 LOGIN BUTTON WITH FIREBASE
                    Button(
                        onClick = {

                            if (email.isEmpty() || password.isEmpty()) {
                                Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
                            } else {

                                auth.signInWithEmailAndPassword(email, password)
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {

                                            val userId = auth.currentUser?.uid

                                            // Optional: fetch user data
                                            userId?.let {
                                                database.child("users").child(it)
                                                    .get()
                                                    .addOnSuccessListener {
                                                        // You can use this data later
                                                    }
                                            }

                                            Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                                            navController.navigate(ROUT_HOME)

                                        } else {
                                            Toast.makeText(
                                                context,
                                                "Error: ${task.exception?.message}",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                    }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(58.dp),
                        shape = RoundedCornerShape(18.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = primaryGreen
                        )
                    ) {
                        Text("Login", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text("Don't have an account? ")

                        Text(
                            text = "Register",
                            color = primaryGreen,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable {
                                navController.navigate(ROUT_REGISTER)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(rememberNavController())
}