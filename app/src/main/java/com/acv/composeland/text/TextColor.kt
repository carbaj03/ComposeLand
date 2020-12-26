package com.acv.composeland.text

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.acv.composeland.chip.Chip
import com.acv.composeland.chip.ChipConstants
import com.acv.composeland.common.Argument
import com.acv.composeland.common.CodeScaffold
import com.acv.composeland.common.codeBui


data class TextColorState(
    val goBack: () -> Unit
)

@Composable
fun TextColor(state: TextColorState) {

    val a = listOf(
        Color.Black to "Color.Black",
        Color.Red to "Color.Red",
        Color.Yellow to "Color.Yellow",
    )
    var selected by remember { mutableStateOf(-1) }
    var color by remember { mutableStateOf(a[0]) }

//    val code: String = """
//      @Composable
//      fun TextColor(){
//            Text(
//                text = code,
//                color = Color.Blue
//            )
//      }
//    """

    val code: AnnotatedString = codeBui {
        annotation(name = "Composable")
        function(name = "TextColor") {
            varString(name = "clicks")
            `class`(
                name = "Text",
                Argument("text", "code"),
                Argument("color", color.second),
            )
        }
    }

    CodeScaffold(
        goBack = state.goBack,
        code = code,
        sample = {
            Text(
                text = "Text sample color",
                color = color.first
            )

        },
        options = {
            Row(modifier = Modifier.padding(8.dp)) {
                Chip(
                    text = "Red",
                    selected = selected == 0,
                    onSelect = { color = if (it) a[1] else a[0]; selected = if (selected == 0) -1 else 0 },
                    colors = ChipConstants.defaultOutlinedChipColors(
                        selectedBackgroundColor = a[1].first.copy(alpha = ContentAlpha.medium),
                        unselectedContentColor = a[1].first,
                        selectedContentColor = Color.White,
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Chip(
                    text = "Yellow",
                    selected = selected == 1,
                    onSelect = { color = if (it) a[2] else a[0]; selected = if (selected == 1) -1 else 1 },
                    colors = ChipConstants.defaultOutlinedChipColors(
                        selectedBackgroundColor = a[2].first.copy(alpha = ContentAlpha.medium),
                        unselectedContentColor = Color.Black,
                        selectedContentColor = Color.Black,
                    )
                )
            }

        }
    )
}