package com.acv.composeland.ui.animation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController


data class AnimationCrossfadeState(
    val goBack: () -> Unit
)

@Composable
fun AnimationCrossfade(
    navController: NavHostController
) {
    val state = AnimationCrossfadeState(goBack = { navController.popBackStack() })

    var letter: String by remember { mutableStateOf("A") }
    Crossfade(targetState = letter) { screen ->
        when (screen) {
            "A" -> {
                Column {
                    Text("Page A")
                    Button(onClick = { letter = "B" }) {
                        Text("Page B")
                    }
                }
            }
            "B" -> {
                Column {
                    Text("Page B")
                    Button(onClick = { letter = "A" }) {
                        Text("Page A")
                    }
                }
            }
        }
    }

}