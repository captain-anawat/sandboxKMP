package kt.sandbox.sbapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform