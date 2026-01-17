package com.example.funkidslearnsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.funkidslearnsapp.Routes
import com.example.funkidslearnsapp.screens.GameMenuScreen
import com.example.funkidslearnsapp.screens.LetterGameScreen
import com.example.funkidslearnsapp.screens.StartupScreen
import com.example.funkidslearnsapp.ui.theme.FunKidsLearnsAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FunKidsLearnsAppTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Routes.StartUp
                ) {

                    // 🔹 Startup
                    composable(Routes.StartUp) {
                        StartupScreen(
                            onContinue = {
                                navController.navigate(Routes.GameMenu)
                            }
                        )
                    }

                    // 🔹 Game Menu
                    composable(Routes.GameMenu) {
                        GameMenuScreen(
                            onStartGame = { gameType ->
                                when (gameType) {
                                    "letters" -> navController.navigate(Routes.LETTER_GAME)
                                }
                            },
                            onSettings = {
                                // TODO later
                            }
                        )
                    }

                    // 🔹 Letter Game
                    composable(Routes.LETTER_GAME) {
                        LetterGameScreen(
                            onBackToMenu = {
                                navController.popBackStack(
                                    Routes.GameMenu,
                                    inclusive = false
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}
