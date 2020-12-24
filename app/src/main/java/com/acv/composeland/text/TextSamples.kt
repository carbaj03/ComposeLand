package com.acv.composeland.text

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.acv.composeland.text.TextScreen.Main

data class TextDependencies(
    val title: String
)

@Composable
fun TextSamples(textDependencies: TextDependencies) {
    val navController = rememberNavController()
    Main(navController = navController).screen()
}