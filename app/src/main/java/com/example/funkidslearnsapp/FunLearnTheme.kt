package com.example.funkidslearnsapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.example.funkidslearnsapp.ui.theme.Purple40
import com.example.funkidslearnsapp.ui.theme.PurpleGrey40
import com.example.funkidslearnsapp.ui.theme.Typography

private val ColorSet = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
)

@Composable
fun FunLearnTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ColorSet,
        typography = Typography,
        content = content
    )
}
