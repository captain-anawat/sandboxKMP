package org.example.sbapp

actual class PlatformFaceDetector : FaceDetector {
    actual override suspend fun detectFaces(
        imageBytes: ByteArray
    ): DetectedFace {
        TODO("Not yet implemented")
    }
}