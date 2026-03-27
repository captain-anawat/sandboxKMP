package org.example.sbapp

// commonMain
data class DetectedFace(
    val left: Float,
    val top: Float,
    val right: Float,
    val bottom: Float,
    val smilingProbability: Float? = null
)

expect class PlatformFaceDetector() : FaceDetector {
    override suspend fun detectFaces(imageBytes: ByteArray): DetectedFace
}

interface FaceDetector {
    suspend fun detectFaces(imageBytes: ByteArray): DetectedFace
}