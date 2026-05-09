package com.josemaria.agridoc.models

data class Scan(
    val scanId: String = "",
    val cropName: String = "",
    val diseaseName: String = "",
    val confidence: String = "",
    val treatment: String = "",
    val prevention: String = "",
    val status: String = "",
    val date: String = ""
)
