package com.josemaria.agridoc.models

data class User(
    val userId: String = "",
    val fullName: String = "",
    val email: String = "",
    val phone: String = "",
    val location: String = "",
    val farmName: String = ""
)
