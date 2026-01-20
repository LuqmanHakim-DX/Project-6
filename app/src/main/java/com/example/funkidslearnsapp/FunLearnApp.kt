package com.example.funkidslearnsapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.funkidslearnsapp.screens.GameMenuScreen
import com.example.funkidslearnsapp.screens.LoseScreen
import com.example.funkidslearnsapp.screens.PauseScreen
import com.example.funkidslearnsapp.screens.SettingsScreen
import com.example.funkidslearnsapp.screens.StartupScreen
import com.example.funkidslearnsapp.screens.WinScreen
import androidx.activity.compose.BackHandler
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import android.app.Activity
import com.example.funkidslearnsapp.data.HighScoreManager
import com.example.funkidslearnsapp.firebase.FirebaseAuthHelper
import com.example.funkidslearnsapp.screens.NumberQuizScreen
import com.example.funkidslearnsapp.screens.ColorGameScreen
import com.example.funkidslearnsapp.screens.LeaderboardScreen
import com.example.funkidslearnsapp.screens.LetterGameScreen


@Composable
fun FunLearnApp() {

    val navController = rememberNavController()
    val context = LocalContext.current
    var showExitDialog by remember { mutableStateOf(false) }

    val startDestination = if (FirebaseAuthHelper.getCurrentUser() != null) {
        Routes.GameMenu
    } else {
        Routes.StartUp
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(Routes.StartUp) {
            StartupScreen {
                navController.navigate(Routes.GameMenu) {
                    popUpTo(Routes.StartUp) { inclusive = true }
                }
            }
        }

        composable(Routes.GameMenu) {

            BackHandler {
                showExitDialog = true
            }

            GameMenuScreen(
                onStartGame = { route ->
                    navController.navigate(route)
                },
                onSettings = {
                    navController.navigate(Routes.Settings)
                }
            )
        }


        // 🔤 LETTER GAME
        composable(Routes.LETTER_GAME) {
            LetterGameScreen(
                onWin = { score -> navController.navigate("win/letter/$score") },
                onLose = { score -> navController.navigate("lose/letter/$score") },
                onPause = { navController.navigate(Routes.Pause) }
            )
        }

        // 🎨 COLOR GAME
        composable(Routes.COLOR_GAME) {
            ColorGameScreen(
                onWin = { score -> navController.navigate("win/color/$score") },
                onLose = { score -> navController.navigate("lose/color/$score") },
                onPause = { navController.navigate(Routes.Pause) }
            )
        }

        // 🔢 NUMBER GAME
        composable(Routes.NumberQuiz) {
            NumberQuizScreen(
                onWin = { score -> navController.navigate("win/number/$score") },
                onLose = { score -> navController.navigate("lose/number/$score") },
                onPause = { navController.navigate(Routes.Pause) }
            )
        }

        // 🏆 WIN SCREEN WITH SCORE KEY
        composable(
            route = "win/{game}/{score}",
            arguments = listOf(
                navArgument("game") { type = NavType.StringType },
                navArgument("score") { type = NavType.IntType }
            )
        ) { backStackEntry ->

            val game = backStackEntry.arguments?.getString("game") ?: "letter"
            val score = backStackEntry.arguments?.getInt("score") ?: 0

            val key = when (game) {
                "letter" -> HighScoreManager.LETTER_HIGH
                "number" -> HighScoreManager.NUMBER_HIGH
                "color" -> HighScoreManager.COLOR_HIGH
                else -> HighScoreManager.LETTER_HIGH
            }

            WinScreen(
                score = score,
                gameKey = key,
                gameId = game,
                onBackToMenu = {
                    navController.popBackStack(Routes.GameMenu, false)
                }
            )
        }

        composable(
            route = "lose/{game}/{score}",
            arguments = listOf(
                navArgument("game") { type = NavType.StringType },
                navArgument("score") { type = NavType.IntType }
            )
        ) { backStackEntry ->

            val game = backStackEntry.arguments?.getString("game") ?: "letter"
            val score = backStackEntry.arguments?.getInt("score") ?: 0

            val key = when (game) {
                "letter" -> HighScoreManager.LETTER_HIGH
                "number" -> HighScoreManager.NUMBER_HIGH
                "color" -> HighScoreManager.COLOR_HIGH
                else -> HighScoreManager.LETTER_HIGH
            }

            LoseScreen(
                score = score,
                gameKey = key,
                gameId = game,
                onRetry = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.Pause) {
            PauseScreen(
                onResume = { navController.popBackStack() },
                onQuit = {
                    navController.popBackStack(Routes.GameMenu, false)
                }
            )
        }

        composable(Routes.Settings) {
            SettingsScreen {
                navController.popBackStack()
            }
        }

        composable(Routes.LEADERBOARD) {
            LeaderboardScreen {
                navController.popBackStack()
            }
        }

    }

    if (showExitDialog) {
        AlertDialog(
            onDismissRequest = { showExitDialog = false },
            title = { Text("Quit the app?") },
            text = { Text("Are you sure you want to exit Fun Kids Learn?") },
            confirmButton = {
                TextButton(onClick = { (context as Activity).finish() }) {
                    Text("Yes")
                }
            },
            dismissButton = {
                TextButton(onClick = { showExitDialog = false }) {
                    Text("No")
                }
            }
        )
    }
}
