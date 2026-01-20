package com.example.funkidslearnsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.funkidslearnsapp.ui.theme.FunKidsLearnsAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FunKidsLearnsAppTheme {
                FunLearnApp() // ✅ NavHost lives here
            }
        }
    }
}
