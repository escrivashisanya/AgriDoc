package com.josemaria.agridoc.data

data class DiseaseInfo(
    val treatment: String,
    val prevention: String
)

object DiseaseRepository {
    private val data = mapOf(
        "Apple___Apple_scab" to DiseaseInfo(
            treatment = "Apply fungicides like captan or mancozeb. Prune infected branches.",
            prevention = "Plant resistant varieties. Remove fallen leaves in autumn."
        ),
        "Tomato___Early_blight" to DiseaseInfo(
            treatment = "Apply copper-based fungicides. Remove lower infected leaves.",
            prevention = "Rotate crops. Avoid overhead watering. Use mulch."
        )
        // Add more disease info as needed
    )

    fun getInfo(disease: String): DiseaseInfo {
        return data[disease] ?: DiseaseInfo(
            treatment = "Consult a local agricultural expert for specific treatment.",
            prevention = "General hygiene and proper crop rotation are recommended."
        )
    }
}
