package com.acv.composeland.appbar.bottom

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
import com.acv.composeland.common.ChipGroup
import com.acv.composeland.common.CodeScaffold
import java.util.*

data class BottomAppBarState(
    val goBack: () -> Unit,
)

@Composable
fun BottomAppBarBackground(state: BottomAppBarState) {
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