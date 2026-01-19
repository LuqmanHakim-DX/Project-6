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
import com.example.funkidslearnsapp.screens.NumberQuizScreen
import com.example.funkidslearnsapp.screens.ColorGameScreen
import com.example.funkidslearnsapp.screens.LetterGameScreen


@Composable
fun FunLearnApp() {

    val navController = rememberNavController()
    val context = LocalContext.current
    var showExitDialog by remember { mutableStateOf(false) }

    BackHandler {
        showExitDialog = true
    }

    NavHost(
        navController = navController,
        startDestination = Routes.StartUp
    ) {

        composable(Routes.StartUp) {
            StartupScreen {
                navController.navigate(Routes.GameMenu) {
                    popUpTo(Routes.StartUp) { inclusive = true }
                }
            }
        }

        composable(Routes.GameMenu) {
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
                onWin = { navController.navigate(Routes.Win) },
                onLose = { navController.navigate(Routes.Lose) },
                onPause = { navController.navigate(Routes.Pause) }
            )
        }

        // 🎨 COLOR GAME
        composable(Routes.COLOR_GAME) {
            ColorGameScreen(
                onWin = { navController.navigate(Routes.Win) },
                onLose = { navController.navigate(Routes.Lose) },
                onPause = { navController.navigate(Routes.Pause) }
            )
        }

        // 🔢 NUMBER GAME
        composable(Routes.NumberQuiz) {
            NumberQuizScreen(
                onWin = { navController.navigate(Routes.Win) },
                onLose = { navController.navigate(Routes.Lose) },
                onPause = { navController.navigate(Routes.Pause) }
            )
        }

        composable(Routes.Win) {
            WinScreen {
                navController.popBackStack(Routes.GameMenu, false)
            }
        }

        composable(Routes.Lose) {
            LoseScreen {
                navController.popBackStack(Routes.GameMenu, false)
            }
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
