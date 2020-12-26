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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.acv.composeland.common.ChipGroup
import com.acv.composeland.common.CodeScaffold
import java.util.*

data class BottomAppBarElevationState(
    val goBack: () -> Unit,
)

@Composable
fun BottomAppBarElevation(
    state: BottomAppBarElevationState
) {
    val a: HashMap<String, Dp> = hashMapOf(
        "Default" to 8.dp,
        "4" to 4.dp,
        "12" to 12.dp,
    )
    var color by remember { mutableStateOf(a.keys.elementAt(0)) }

    val code: String = """
      @Composable
      fun TextColor(){
           BottomAppBar(
                backgroundColor = MaterialTheme.colors.surface,
                elevation = a[color]!!,
            )
      }
    """

    CodeScaffold(
        goBack = state.goBack,
        code = code,
        sample = {
            BottomAppBar(
                backgroundColor = MaterialTheme.colors.surface,
                elevation = a[color]!!,
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