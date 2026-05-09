package com.josemaria.agridoc.ml

import android.content.Context
import android.graphics.Bitmap
import org.tensorflow.lite.Interpreter
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.channels.FileChannel

class PlantDiseaseClassifier(
    context: Context
) {
    private val interpreter: Interpreter
    private val labels: List<String>

    init {
        val modelBuffer = loadModelFile(context, "plant_disease_model.tflite")
        interpreter = Interpreter(modelBuffer)

        labels = context.assets
            .open("labels.txt")
            .bufferedReader()
            .readLines()
    }

    private fun loadModelFile(context: Context, modelName: String): ByteBuffer {
        val fileDescriptor = context.assets.openFd(modelName)
        val inputStream = fileDescriptor.createInputStream()
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    fun classify(bitmap: Bitmap): Pair<String, Float> {
        val resized = Bitmap.createScaledBitmap(
            bitmap,
            224,
            224,
            true
        )

        val input = ByteBuffer.allocateDirect(
            4 * 224 * 224 * 3
        ).apply {
            order(ByteOrder.nativeOrder())
        }

        for (y in 0 until 224) {
            for (x in 0 until 224) {
                val pixel = resized.getPixel(x, y)

                // Normalize pixel values to [0, 1]
                input.putFloat(((pixel shr 16 and 0xFF) / 255f))
                input.putFloat(((pixel shr 8 and 0xFF) / 255f))
                input.putFloat(((pixel and 0xFF) / 255f))
            }
        }

        val output = Array(1) {
            FloatArray(labels.size)
        }

        input.rewind()
        interpreter.run(input, output)

        val bestIndex = output[0]
            .indices
            .maxByOrNull { output[0][it] } ?: -1

        return if (bestIndex != -1) {
            labels[bestIndex] to output[0][bestIndex]
        } else {
            "Unknown" to 0f
        }
    }
}
