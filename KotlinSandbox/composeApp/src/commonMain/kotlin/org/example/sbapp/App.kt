package org.example.sbapp


import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.*
import kotlinsandbox.composeapp.generated.resources.*
import kotlinx.coroutines.launch
import org.example.sbapp.base.*
import org.jetbrains.compose.resources.painterResource

private val LightColors = lightColorScheme(
    primary = Color.Red,
    secondary = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
)

private val DarkColors = darkColorScheme(
    primary = Color.Red,
    secondary = Color.White,
    surface = Color.Black,
    onPrimary = Color.White,
)

@Composable
@Preview
fun App(useDarkTheme: Boolean = isSystemInDarkTheme()) {
    val navController = rememberNavController()
    AppTheme(useDarkTheme) {
        NavHost(
            navController = navController,
            startDestination = Screen.Login.name,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(400)
                ) + fadeIn(animationSpec = tween(400))
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(400)
                ) + fadeOut(animationSpec = tween(400))
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(400)
                ) + fadeIn(animationSpec = tween(400))
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(400)
                ) + fadeOut(animationSpec = tween(400))
            }
        ) {// LOGIN PAGE
            composable(route = Screen.Login.name) {
                LoginScreen(onLoginSuccess = {
                    navController.navigate(Screen.Home.name)
                })
            }

            // HOME PAGE (The one with your New Bars)
            composable(route = Screen.Home.name) {
                MainScaffoldScreen()
            }
        }
    }
}

@Composable
fun AppTheme(
    useDarkTheme: Boolean, // You can link this to system settings
    content: @Composable () -> Unit
) {
    val colors = if (useDarkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        // You can also define typography and shapes here
        content = content
    )
}

@Composable
fun MainScaffoldScreen() {
    val scope = rememberCoroutineScope()
    var faceCount by remember { mutableStateOf<String?>(null) }
    val service = remember { PlatformFaceDetector() }
    var isProcessing by remember { mutableStateOf(false) }
    Scaffold(
        topBar = { TopAppBar() },
    ) { padding ->
        Surface(
            modifier = Modifier.fillMaxSize().padding(padding),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("You are Logged In!", modifier = Modifier.padding(20.dp))

                Image(
                    painter = painterResource(Res.drawable.face),
                    contentDescription = "Local Face Image",
                    modifier = Modifier.fillMaxWidth().height(300.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (isProcessing) {
                    CircularProgressIndicator()
                    Text("Analyzing image...", modifier = Modifier.padding(top = 8.dp))
                } else {
                    Text(
                        text = if (faceCount == null) "Ready to detect" else "Found $faceCount faces",
                        style = MaterialTheme.typography.headlineMedium,
                        color = if (faceCount == null) MaterialTheme.colorScheme.primary else Color.Gray
                    )
                }

                Button(
                    onClick = {
                        scope.launch {
                            isProcessing = true
                            try {
                                // Read the local file as bytes
                                val bytes = Res.readBytes("drawable/face.jpg")

                                // Send to your PlatformFaceDetector (Android/iOS)
                                val results = service.detectFaces(bytes)
                                faceCount = "$results"
                            } catch (e: Exception) {
                                println("Error detecting: ${e.message}")
                            } finally {
                                isProcessing = false
                            }
                        }
                    },
                    enabled = !isProcessing
                ) {
                    Text("Detect Faces")
                }
            }
        }
    }
}
