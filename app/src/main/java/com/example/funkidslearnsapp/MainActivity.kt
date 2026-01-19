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
import com.example.funkidslearnsapp.screens.ColorGameScreen
import com.example.funkidslearnsapp.screens.WinScreen
import com.example.funkidslearnsapp.screens.LoseScreen
import com.example.funkidslearnsapp.screens.PauseScreen
import com.example.funkidslearnsapp.screens.ColorGameScreen
import com.example.funkidslearnsapp.screens.NumberQuizScreen


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
                                    "colors" -> navController.navigate(Routes.COLOR_GAME)
                                    "numbers" -> navController.navigate(Routes.NumberQuiz)
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

                            onLose = { navController.navigate(Routes.Lose) },
                            onPause = { navController.navigate(Routes.Pause) }
                        )
                    }

                    // 🔹 Number Game
                    composable(Routes.NumberQuiz) {
                        NumberQuizScreen(

                            onLose = { navController.navigate(Routes.Lose) },
                            onPause = { navController.navigate(Routes.Pause) }
                        )
                    }

                    // 🔹 Color Game
                    composable(Routes.COLOR_GAME) {
                        ColorGameScreen(

                            onLose = { navController.navigate(Routes.Lose) },
                            onPause = { navController.navigate(Routes.Pause) }
                        )
                    }
                    // 🏆 Win Screen
                    composable(Routes.Win) {
                        WinScreen(
                            onBackToMenu = {
                                navController.popBackStack(
                                    Routes.GameMenu,
                                    inclusive = false
                                )
                            }
                        )
                    }

                    // ❌ Lose Screen
                    composable(Routes.Lose) {
                        LoseScreen(
                            onRetry = {
                                navController.popBackStack(
                                    Routes.GameMenu,
                                    inclusive = false
                                )
                            }
                        )
                    }

                    // ⏸ Pause Screen
                    composable(Routes.Pause) {
                        PauseScreen(
                            onResume = {
                                navController.popBackStack()
                            },
                            onQuit = {
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
