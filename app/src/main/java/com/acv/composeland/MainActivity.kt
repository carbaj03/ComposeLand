package com.acv.composeland

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.acv.composeland.ui.screen.*
import com.acv.composeland.ui.ComposeLandTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLandTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    AppMain()
                }
            }
        }
    }
}

@Composable
fun AppMain() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainScreen.Main.route
    ) {
        main(navController)
        material(navController)
        navigation(navController)
        text(navController)
        button(navController)
        bottomAppBar(navController)
        animation(navController)
        color(navController)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeLandTheme {
//        AppMain()
    }
}