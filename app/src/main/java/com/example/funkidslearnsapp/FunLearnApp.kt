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
import com.example.funkidslearnsapp.screens.GameplayScreen
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
import androidx.lifecycle.viewmodel.compose.viewModel



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
            StartupScreen(
                onContinue = {
                    navController.navigate(Routes.GameMenu) {
                        popUpTo(Routes.StartUp) { inclusive = true }
                    }
                }
            )
        }


        composable(Routes.GameMenu) {
            GameMenuScreen(
                onStartGame = { gameId ->
                    when (gameId) {
                        "numbers" -> navController.navigate(Routes.NumberQuiz)
                        else -> navController.navigate("${Routes.Gameplay}/$gameId")
                    }
                },
                onSettings = {
                    navController.navigate(Routes.Settings)
                }
            )
        }

        composable(
            route = "${Routes.Gameplay}/{gameId}",
            arguments = listOf(navArgument("gameId") { type = NavType.StringType })
        ) { backStackEntry ->

            val gameId = backStackEntry.arguments?.getString("gameId") ?: "1"

            GameplayScreen(
                gameId = gameId,
                onWin = { navController.navigate(Routes.Win) },
                onLose = { navController.navigate(Routes.Lose) },
                onPause = { navController.navigate(Routes.Pause) }
            )
        }

        composable(Routes.Win) {
            WinScreen(
                onBackToMenu = {
                    navController.popBackStack(Routes.GameMenu, false)
                }
            )
        }

        composable(Routes.Lose) {
            LoseScreen(
                onRetry = {
                    navController.popBackStack()
                    navController.navigate(Routes.GameMenu)
                }
            )
        }

        composable(Routes.Settings) {
            SettingsScreen(
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.Pause) {
            PauseScreen(
                onResume = { navController.popBackStack() },
                onQuit = { navController.navigate(Routes.GameMenu) }
            )
        }

        composable(Routes.NumberQuiz) {
            NumberQuizScreen(
                onWin = { navController.navigate(Routes.Win) },
                onLose = { navController.navigate(Routes.Lose) }
            )
        }

        composable(Routes.Letters) {
            LettersScreen(onBack = { navController.popBackStack() })
        }

        composable(Routes.Numbers) {
            NumbersScreen(onBack = { navController.popBackStack() })
        }

        composable(Routes.Shapes) {
            ShapesScreen(onBack = { navController.popBackStack() })
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


@Composable
fun LettersScreen(onBack: () -> Unit) {
    SimpleScreen("Letters Game", onBack)
}

@Composable
fun NumbersScreen(onBack: () -> Unit) {
    SimpleScreen("Numbers Game", onBack)
}

@Composable
fun ShapesScreen(onBack: () -> Unit) {
    SimpleScreen("Shapes Game", onBack)
}

@Composable
fun SimpleScreen(title: String, onBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(title, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onBack) {
            Text("Back")
        }
    }
}