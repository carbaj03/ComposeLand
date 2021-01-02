package com.acv.composeland.ui.appbar.bottom.implementation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.navigation.NavHostController
import com.acv.composeland.ui.common.Argument
import com.acv.composeland.ui.common.ChipGroup
import com.acv.composeland.ui.common.CodeScaffold
import com.acv.composeland.ui.common.codeBui
import java.util.*

data class BottomAppBarState(
    val goBack: () -> Unit,
)

@Composable
fun BottomAppBarBackground(
    navController: NavHostController
) {
    val state = BottomAppBarState(goBack = { navController.popBackStack() })
    val a: HashMap<String, Color> = hashMapOf(
        "Default" to MaterialTheme.colors.primary,
        "Magenta" to Color.Magenta,
        "Blue" to Color.Blue,
    )
    var color by remember { mutableStateOf(a.keys.elementAt(0)) }

    val code: AnnotatedString = codeBui {
        annotation(name = "Composable")
        function(name = "BottomAppBarBackground") {
            `class`(
                name = "BottomAppBar",
                Argument("backgroundColor", color),
            )
        }
    }

    CodeScaffold(
        title = "BottomAppBar",
        goBack = state.goBack,
        code = code,
        sample = {
            BottomAppBar(
                backgroundColor = a[color]!!,
            ) {
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Menu)
                }
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
        },
    )
}