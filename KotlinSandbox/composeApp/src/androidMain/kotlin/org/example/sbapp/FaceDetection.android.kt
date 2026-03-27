package org.example.sbapp

import android.graphics.BitmapFactory
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.*
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

actual class PlatformFaceDetector : FaceDetector {
    private val options = FaceDetectorOptions.Builder()
        .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
        .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
        .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
        .enableTracking()
        .build()
    private val detector = FaceDetection.getClient(options)

    actual override suspend fun detectFaces(imageBytes: ByteArray): DetectedFace {
        val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        //val inputImage = InputImage.fromByteArray(imageBytes, bitmap.width, bitmap.height, 0, InputImage.IMAGE_FORMAT_BITMAP)
        val inputImage = InputImage.fromBitmap(bitmap, 0)
        return suspendCancellableCoroutine { continuation ->
            detector.process(inputImage)
                .addOnSuccessListener { faces ->
                    if (faces.isNotEmpty() && faces.size == 1) {
                        val face = faces[0]
                        continuation.resume(
                            DetectedFace(
                                face.boundingBox.left.toFloat(),
                                face.boundingBox.top.toFloat(),
                                face.boundingBox.right.toFloat(),
                                face.boundingBox.bottom.toFloat()
                            )
                        )
                    }
                }
                .addOnFailureListener {
                    DetectedFace(Float.NaN, Float.NaN, Float.NaN, Float.NaN)
                }
        }
    }
}