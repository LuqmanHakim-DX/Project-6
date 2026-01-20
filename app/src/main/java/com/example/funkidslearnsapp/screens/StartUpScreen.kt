package com.example.funkidslearnsapp.screens

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.funkidslearnsapp.firebase.FirebaseAuthHelper
import kotlinx.coroutines.launch

@Composable
fun StartupScreen(
    onContinue: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Fun Kids Learn")

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = {
            scope.launch {
                try {
                    val result = FirebaseAuthHelper.signInWithGoogle(context as Activity)
                    if (result.isSuccess) {
                        onContinue()
                    } else {
                        Toast.makeText(context, "Sign-in failed: ${result.exceptionOrNull()?.localizedMessage}", Toast.LENGTH_LONG).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(context, "An error occurred: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
                }
            }
        }) {
            Text("Sign in with Google")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            FirebaseAuthHelper.signInAnonymously()
            onContinue()
        }) {
            Text("Continue as Guest")
        }
    }
}
