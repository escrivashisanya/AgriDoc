package com.josemaria.agridoc.models

data class ScanHistory(
    val id: String = "",
    val cropName: String = "",
    val diseaseName: String = "",
    val date: String = "",
    val confidence: String = "",
    val treatment: String = "",
    val status: String = ""
)
