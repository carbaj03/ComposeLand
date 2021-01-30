package com.acv.composeland.ui.chip

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.acv.composeland.ui.common.CodeScaffold

data class ChipColorState(
    val goBack: () -> Unit,
)

@Composable
fun ChipColor(
    navController: NavHostController
) {
    val state = ChipColorState(goBack = { navController.popBackStack() })

    val code: String = """
      @Composable
      fun TextColor(){
            Text(
                text = code,
                color = Color.Blue
            )
      }
    """
    var color by remember { mutableStateOf(Color.Black) }

    CodeScaffold(
        title = "Chip Color",
        goBack = state.goBack,
        code = code
    ) {
        Text(
            text = "Text sample color",
            color = color
        )
        var switch by remember { mutableStateOf(false) }
        var selected by remember { mutableStateOf(0) }
        Row {
            Chip(
                left = { if (switch) Icon(modifier = it, imageVector = Icons.Filled.Favorite, contentDescription = null) else null },
                text = "Red",
                enabled = switch,
                selected = selected == 0,
                onSelect = { color = if (it) Color.Red else Color.Black; selected = 0 },
                colors = ChipConstants.defaultOutlinedChipColors(
                    selectedBackgroundColor = Color.Red.copy(alpha = ContentAlpha.medium),
                    unselectedContentColor = Color.Red,
                    selectedContentColor = Color.White,
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Chip(
                left = { if (switch) Icon(modifier = it, imageVector = Icons.Filled.Favorite, contentDescription = null) else null },
                text = "Yellow",
                enabled = switch,
                selected = selected == 1,
                onSelect = { color = if (it) Color.Yellow else Color.Black; selected = 1 },
                colors = ChipConstants.defaultOutlinedChipColors(
                    selectedBackgroundColor = Color.Yellow.copy(alpha = ContentAlpha.medium),
                    unselectedContentColor = Color.Yellow,
                    selectedContentColor = Color.Black,
                )
            )

        }

        Switch(checked = switch, onCheckedChange = { switch = !switch })
    }
}