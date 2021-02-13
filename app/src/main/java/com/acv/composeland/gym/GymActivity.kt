package com.acv.composeland.gym

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.acv.composeland.ui.ComposeLandTheme
import com.acv.composeland.ui.common.ChipGroup
import com.acv.composeland.ui.screen.MainScreen

class GymActivity : AppCompatActivity() {

    override fun onCreate(rememberSaveable: Bundle?) {
        super.onCreate(rememberSaveable)

        setContent {
            ComposeLandTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "Home"
                    ) {
                        composable("Home") {
                            Home { navController.navigate("Add") }
                        }
                        composable("Add") {
                            Add({ navController.popBackStack() })
                        }
                    }
                }
            }
        }
    }
}

//typealias Content = @Composable () -> Unit
//
//@Composable
//fun Navigation(content: @Composable (NavController) -> Unit) {
//
//    content(navController)
//}

@Composable
fun Add(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                backgroundColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(Icons.Filled.Menu, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
                    }
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
                    }
                },
                elevation = 0.dp
            )
        },
    ) {
        ChipGroup(
            items = listOf("sp", "em"),
            onChange = { }
        )
    }
}

@Composable
fun AddTopBar() {

}

@Composable
fun Home(
    onAdd: () -> Unit
) {
    Scaffold(
        bottomBar = { SimpleBottomAppBar() },
        floatingActionButton = {
            FloatingActionButton(onClick = { onAdd() }) {
                Icon(Icons.Filled.Add, null)
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = true,
    ) {
        ChipGroup(
            items = listOf("sp", "em"),
            onChange = { }
        )
    }
}

@Composable
fun SimpleBottomAppBar() {
    BottomAppBar(
        backgroundColor = Color.White,
        cutoutShape = RoundedCornerShape(50),
    ) {
        IconButton(onClick = { /* doSomething() */ }) {
            Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
        }
        IconButton(onClick = { /* doSomething() */ }) {
            Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
        }
    }
}
