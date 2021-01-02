package com.acv.composeland.appbar.bottom.implementation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.acv.composeland.common.ChipGroup
import com.acv.composeland.common.CodeScaffold
import java.util.*


data class BottomAppBarContentColorState(
    val goBack: () -> Unit,
)

@Composable
fun BottomAppBarContentColor(
    navController : NavHostController
) {
    val state = BottomAppBarContentColorState(goBack = { navController.popBackStack() })
    val a: HashMap<String, Color> = hashMapOf(
        "Default" to MaterialTheme.colors.primary,
        "Magenta" to Color.Magenta,
        "Blue" to Color.Blue,
    )
    var color by remember { mutableStateOf(a.keys.elementAt(0)) }

    val code: String = """
      @Composable
      fun TextColor(){
            Text(
                text = code,
                color = Color.Blue
            )
      }
    """

    CodeScaffold(
        title = "BottomAppBarContentColor",
        goBack = state.goBack,
        code = code,
        sample = {
            BottomAppBar(
                contentColor = a[color]!!,
            ) {
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Menu)
                }
                Text(text = "Title")
                // The actions should be at the end of the BottomAppBar
                Spacer(Modifier.weight(1f, true))
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Favorite)
                }
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Favorite)
                }
            }
        },
        options = {
            ChipGroup(
                items = a.keys.toList(),
                selected = a.keys.elementAt(0),
                onChange = { color = it }
            )
        }
    )

}