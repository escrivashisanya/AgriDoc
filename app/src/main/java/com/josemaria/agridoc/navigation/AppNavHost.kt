package com.josemaria.agridoc.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.josemaria.agridoc.ui.screens.auth.ForgotPasswordScreen
import com.josemaria.agridoc.ui.screens.auth.LoginScreen
import com.josemaria.agridoc.ui.screens.auth.RegisterScreen
import com.josemaria.agridoc.ui.screens.auth.OtpVerificationScreen
import com.josemaria.agridoc.ui.screens.auth.ChangePasswordScreen
import com.josemaria.agridoc.ui.screens.auth.MarketplaceScreen
import com.josemaria.agridoc.ui.screens.dash.DashboardScreen
import com.josemaria.agridoc.ui.screens.history.HistoryDetailsScreen
import com.josemaria.agridoc.ui.screens.history.HistoryScreen
import com.josemaria.agridoc.ui.screens.home.HomeScreen
import com.josemaria.agridoc.ui.screens.onboarding.OnboardingDiseaseDetectionScreen
import com.josemaria.agridoc.ui.screens.onboarding.OnboardingScreen
import com.josemaria.agridoc.ui.screens.onboarding.OnboardingTreatmentScreen
import com.josemaria.agridoc.ui.screens.profile.EditProfileScreen
import com.josemaria.agridoc.ui.screens.profile.ProfileScreen
import com.josemaria.agridoc.ui.screens.results.*
import com.josemaria.agridoc.ui.screens.scan.*
import com.josemaria.agridoc.ui.screens.settings.*
import com.josemaria.agridoc.ui.screens.splash.SplashScreen
import com.josemaria.agridoc.ui.screens.weather.WeatherScreen

import androidx.lifecycle.viewmodel.compose.viewModel
import com.josemaria.agridoc.ui.viewmodel.ScanViewModel

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH
) {
    val scanViewModel: ScanViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(ROUT_SPLASH) { SplashScreen(navController) }
        composable(ROUT_ONBOARDING) { OnboardingScreen(navController) }
        composable(ROUT_ONBOARDING_DISEASE) { OnboardingDiseaseDetectionScreen(navController) }
        composable(ROUT_ONBOARDING_TREATMENT) { OnboardingTreatmentScreen(navController) }
        composable(ROUT_LOGIN) { LoginScreen(navController) }
        composable(ROUT_REGISTER) { RegisterScreen(navController) }
        composable(ROUT_FORGOT_PASSWORD) { ForgotPasswordScreen(navController) }
        composable(ROUT_OTP_VERIFICATION) { OtpVerificationScreen(navController) }
        composable(ROUT_CHANGE_PASSWORD) { ChangePasswordScreen(navController) }
        
        composable(ROUT_HOME) { DashboardScreen(navController) }
        composable(ROUT_WEATHER) { WeatherScreen(navController) }
        composable(ROUT_MARKETPLACE) { MarketplaceScreen(navController) }
        
        composable(ROUT_SCAN) { ScanScreen(navController) }
        composable(ROUT_CAMERA) { CameraScreen(navController, scanViewModel) }
        composable(ROUT_PREVIEW) { PreviewScreen(navController, scanViewModel) }
        composable(ROUT_IMAGE_PREVIEW) { ImagePreviewScreen(navController, scanViewModel) }
        composable(ROUT_SCAN_LOADING) { ScanLoadingScreen(navController, scanViewModel) }
        composable(ROUT_LOADING) { LoadingScreen(navController, scanViewModel) }
        
        composable(ROUT_RESULT) { ResultScreen(navController, scanViewModel) }
        composable(ROUT_DISEASE_DETAILS) { DiseaseDetailsScreen(navController) }
        composable(ROUT_TREATMENT) { TreatmentScreen(navController) }
        composable(ROUT_PREVENTION) { PreventionScreen(navController) }
        
        composable(ROUT_HISTORY) { HistoryScreen(navController) }
        composable(ROUT_HISTORY_DETAILS) { backStackEntry ->
            val scanId = backStackEntry.arguments?.getString("scanId") ?: ""
            HistoryDetailsScreen(navController, scanId)
        }
        
        composable(ROUT_PROFILE) { ProfileScreen(navController) }
        composable(ROUT_EDIT_PROFILE) { EditProfileScreen(navController) }
        
        composable(ROUT_SETTINGS) { SettingsScreen(navController) }
        composable(ROUT_PRIVACY_POLICY) { PrivacyPolicyScreen(navController) }
        composable(ROUT_HELP_SUPPORT) { HelpSupportScreen(navController) }
        composable(ROUT_ABOUT) { AboutScreen(navController) }
    }
}
