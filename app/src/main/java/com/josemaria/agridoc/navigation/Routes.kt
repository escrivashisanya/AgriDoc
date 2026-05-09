package com.josemaria.agridoc.navigation

const val ROUT_SPLASH = "splash"
const val ROUT_ONBOARDING = "onboarding"
const val ROUT_ONBOARDING_DISEASE = "onboarding_disease"
const val ROUT_ONBOARDING_TREATMENT = "onboarding_treatment"

// Auth Routes
const val ROUT_LOGIN = "login"
const val ROUT_REGISTER = "register"
const val ROUT_FORGOT_PASSWORD = "forgot_password"
const val ROUT_OTP_VERIFICATION = "otp_verification"
const val ROUT_CHANGE_PASSWORD = "change_password"

// Main Routes
const val ROUT_HOME = "home"
const val ROUT_NOTIFICATIONS = "notifications"
const val ROUT_MARKETPLACE = "marketplace"

// Scan Routes
const val ROUT_SCAN = "scan"
const val ROUT_CAMERA = "camera"
const val ROUT_PREVIEW = "preview"
const val ROUT_IMAGE_PREVIEW = "image_preview"
const val ROUT_SCAN_LOADING = "scan_loading"
const val ROUT_LOADING = "loading"

// Result Routes
const val ROUT_RESULT = "result"
const val ROUT_DISEASE_DETAILS = "disease_details"
const val ROUT_TREATMENT = "treatment"
const val ROUT_PREVENTION = "prevention"

// History Routes
const val ROUT_HISTORY = "history"
const val ROUT_HISTORY_DETAILS = "history_details/{scanId}"

// Profile Routes
const val ROUT_PROFILE = "profile"
const val ROUT_EDIT_PROFILE = "edit_profile"

// Settings Routes
const val ROUT_SETTINGS = "settings"
const val ROUT_ABOUT = "about"
const val ROUT_PRIVACY_POLICY = "privacy_policy"
const val ROUT_HELP_SUPPORT = "help_support"

// Other Routes
const val ROUT_WEATHER = "weather"

object Screen {
    const val Splash = ROUT_SPLASH
    const val Onboarding = ROUT_ONBOARDING
    const val OnboardingDisease = ROUT_ONBOARDING_DISEASE
    const val OnboardingTreatment = ROUT_ONBOARDING_TREATMENT
    const val Login = ROUT_LOGIN
    const val Home = ROUT_HOME
    const val Scan = ROUT_SCAN
    const val History = ROUT_HISTORY
    const val Profile = ROUT_PROFILE
}
