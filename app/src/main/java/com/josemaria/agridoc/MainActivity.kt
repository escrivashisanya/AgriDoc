package com.josemaria.agridoc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.josemaria.agridoc.navigation.AppNavHost
import com.josemaria.agridoc.ui.theme.AgriDocTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AgriDocTheme {
                AppNavHost()
            }
        }
    }
}
