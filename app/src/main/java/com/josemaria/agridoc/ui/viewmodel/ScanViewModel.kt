package com.josemaria.agridoc.ui.viewmodel

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
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

class ScanViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(ScanUiState())
    val uiState: StateFlow<ScanUiState> = _uiState.asStateFlow()

    private val classifier = PlantDiseaseClassifier(application)

    fun onImageCaptured(bitmap: Bitmap) {
        _uiState.update { it.copy(bitmap = bitmap) }
    }

    fun analyzeImage() {
        val bitmap = _uiState.value.bitmap ?: return

        viewModelScope.launch(Dispatchers.Default) {
            _uiState.update {
                it.copy(isLoading = true)
            }

            val (disease, confidence) = classifier.classify(bitmap)
            val info = DiseaseRepository.getInfo(disease)

            _uiState.update {
                it.copy(
                    isLoading = false,
                    disease = disease,
                    confidence = confidence,
                    treatment = info.treatment,
                    prevention = info.prevention
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
