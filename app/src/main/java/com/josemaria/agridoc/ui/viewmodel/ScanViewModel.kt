package com.josemaria.agridoc.ui.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josemaria.agridoc.data.DiseaseRepository
import com.josemaria.agridoc.ml.PlantDiseaseClassifier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ScanUiState(
    val bitmap: Bitmap? = null,
    val disease: String = "Unknown",
    val confidence: Float = 0.0f,
    val treatment: String = "No treatment available",
    val prevention: String = "No prevention tips available",
    val isLoading: Boolean = false,
    val error: String? = null
)

class ScanViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ScanUiState())
    val uiState: StateFlow<ScanUiState> = _uiState.asStateFlow()

   // private val classifier = PlantDiseaseClassifier(application)

    fun onImageCaptured(bitmap: Bitmap) {
        _uiState.update { it.copy(bitmap = bitmap) }
    }

    fun analyzeImage() {
        val bitmap = _uiState.value.bitmap ?: return

        viewModelScope.launch(Dispatchers.Default) {
            _uiState.update {
                it.copy(isLoading = true)
            }




            _uiState.update {
                it.copy(
                    disease = "Tomato Early Blight",
                    confidence = 0.95f,
                    treatment = "Apply fungicides weekly",
                    prevention = "Avoid overwatering, use resistant varieties",
                )
            }
        }
    }

    fun updateResult(disease: String, confidence: Float, treatment: String, prevention: String) {
        _uiState.update {
            it.copy(
                disease = disease,
                confidence = confidence,
                treatment = treatment,
                prevention = prevention
            )
        }
    }
}
