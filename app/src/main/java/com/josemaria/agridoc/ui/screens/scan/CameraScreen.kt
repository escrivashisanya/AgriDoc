package com.josemaria.agridoc.ui.screens.scan

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.josemaria.agridoc.navigation.ROUT_PREVIEW
import com.josemaria.agridoc.ui.viewmodel.ScanViewModel
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Composable
fun CameraScreen(
    navController: NavController,
    viewModel: ScanViewModel
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var hasPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { granted ->
            hasPermission = granted
        }

    LaunchedEffect(Unit) {
        if (!hasPermission) {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    if (!hasPermission) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Camera permission required")
        }
        return
    }

    val imageCapture = remember {
        ImageCapture.Builder().build()
    }

    val executor: ExecutorService = remember {
        Executors.newSingleThreadExecutor()
    }

    DisposableEffect(Unit) {
        onDispose {
            executor.shutdown()
        }
    }

    CameraContent(
        onCaptureClick = {
            capturePhoto(
                imageCapture = imageCapture,
                executor = executor,
                onImageCaptured = { bitmap ->
                    viewModel.onImageCaptured(bitmap)
                    navController.navigate(ROUT_PREVIEW)
                }
            )
        },
        previewViewFactory = { ctx ->
            val previewView = PreviewView(ctx)

            val cameraProviderFuture =
                ProcessCameraProvider.getInstance(ctx)

            cameraProviderFuture.addListener({
                val cameraProvider =
                    cameraProviderFuture.get()

                val preview = androidx.camera.core.Preview.Builder()
                    .build()
                    .also {
                        it.surfaceProvider =
                            previewView.surfaceProvider
                    }

                cameraProvider.unbindAll()

                try {
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        CameraSelector.DEFAULT_BACK_CAMERA,
                        preview,
                        imageCapture
                    )
                } catch (exc: Exception) {
                    // Log errors
                }
            }, ContextCompat.getMainExecutor(ctx))

            previewView
        }
    )
}

@Composable
fun CameraContent(
    onCaptureClick: () -> Unit,
    previewViewFactory: (Context) -> PreviewView = { ctx -> PreviewView(ctx) }
) {
    Box(modifier = Modifier.fillMaxSize()) {

        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = previewViewFactory
        )

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
                .size(80.dp),
            shape = CircleShape,
            containerColor = MaterialTheme.colorScheme.primary,
            onClick = onCaptureClick
        ) {
            Icon(
                imageVector = Icons.Default.Camera,
                contentDescription = "Capture",
                modifier = Modifier.size(36.dp)
            )
        }
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
fun CameraScreenPreview() {
    CameraContent(onCaptureClick = {})
}

private fun capturePhoto(
    imageCapture: ImageCapture,
    executor: ExecutorService,
    onImageCaptured: (Bitmap) -> Unit
) {
    imageCapture.takePicture(
        executor,
        object : ImageCapture.OnImageCapturedCallback() {
            override fun onCaptureSuccess(image: ImageProxy) {
                val bitmap = image.toBitmap()
                image.close()
                onImageCaptured(bitmap)
            }

            override fun onError(exception: ImageCaptureException) {
                // Log error
            }
        }
    )
}

private fun ImageProxy.toBitmap(): Bitmap {
    val buffer = planes[0].buffer
    val bytes = ByteArray(buffer.remaining())
    buffer.get(bytes)
    val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    
    // Rotate bitmap if necessary
    val matrix = Matrix()
    matrix.postRotate(imageInfo.rotationDegrees.toFloat())
    
    return Bitmap.createBitmap(
        bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true
    )
}
