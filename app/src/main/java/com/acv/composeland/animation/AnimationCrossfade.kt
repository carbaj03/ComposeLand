package com.acv.composeland.animation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.acv.composeland.text.TextFontStyleState


data class AnimationCrossfadeState(
    val goBack: () -> Unit
)

@Composable
fun AnimationCrossfade(
    navController: NavHostController
) {
    val state = AnimationCrossfadeState(goBack = { navController.popBackStack() })

    var letter by remember { mutableStateOf("A") }
    Crossfade(current = letter) { screen ->
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