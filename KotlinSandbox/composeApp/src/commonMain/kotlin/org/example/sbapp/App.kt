package org.example.sbapp


import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.*
import org.example.sbapp.base.*

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
    Scaffold(
        topBar = { TopAppBar() },
    ) { padding ->
        Surface(
            modifier = Modifier.fillMaxSize().padding(padding),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("You are Logged In!", modifier = Modifier.padding(20.dp))
            }
        }
    }
}
