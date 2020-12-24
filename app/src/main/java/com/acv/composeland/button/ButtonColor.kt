package com.acv.composeland.button

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonConstants
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.acv.composeland.chip.Chip
import com.acv.composeland.chip.ChipConstants
import com.acv.composeland.common.CodeScaffold

@Composable
fun ButtonColor(goBack : () -> Unit) {
    var selected by remember { mutableStateOf(-1) }
    var color by remember { mutableStateOf(Color.Black) }

    val code: String = """
      @Composable
      fun TextColor(){
        Button(
            onClick = {},
            colors = ButtonConstants.defaultButtonColors(
                backgroundColor = color
            )
        ) {
            Text("Click Me")
        }
      }
    """

    CodeScaffold(
        goBack = goBack,
        code = code
    ) {
        Button(
            onClick = {},
            colors = ButtonConstants.defaultButtonColors(
                backgroundColor = color
            )
        ) {
            Text("Click Me")
        }

        Row(modifier = Modifier.padding(8.dp)) {
            Chip(
                text = "Red",
                selected = selected == 0,
                onSelect = { color = if (it) Color.Red else Color.Black; selected = if (selected == 0) -1 else 0 },
                colors = ChipConstants.defaultOutlinedChipColors(
                    selectedBackgroundColor = Color.Red.copy(alpha = ContentAlpha.medium),
                    unselectedContentColor = Color.Red,
                    selectedContentColor = Color.White,
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Chip(
                text = "Yellow",
                selected = selected == 1,
                onSelect = { color = if (it) Color.Yellow else Color.Black; selected = if (selected == 1) -1 else 1 },
                colors = ChipConstants.defaultOutlinedChipColors(
                    selectedBackgroundColor = Color.Yellow.copy(alpha = ContentAlpha.medium),
                    unselectedContentColor = Color.Black,
                    selectedContentColor = Color.Black,
                )
            )
        }
    }
}