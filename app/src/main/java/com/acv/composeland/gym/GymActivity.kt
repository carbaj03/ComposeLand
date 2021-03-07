package com.acv.composeland.gym

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.acv.composeland.ui.ComposeLandTheme
import com.acv.composeland.ui.common.ChipGroup
import com.acv.composeland.ui.screen.MainScreen
import com.google.android.material.textfield.TextInputEditText

@OptIn(ExperimentalAnimationApi::class)
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
//                            AnimatedVisibilityDemo()
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
        CustomTextField(
            placeholder = "Kg",
            keyboardType = KeyboardType.Number
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
