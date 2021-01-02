package com.acv.composeland.ui.button

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.acv.composeland.ui.chip.Chip
import com.acv.composeland.ui.chip.ChipConstants
import com.acv.composeland.ui.common.Argument
import com.acv.composeland.ui.common.CodeScaffold
import com.acv.composeland.ui.common.codeBui

data class ButtonColorState(
    val goBack: () -> Unit,
)

@Composable
fun ButtonColor(
    navController: NavHostController
) {
    val state = ButtonColorState(
        goBack = { navController.popBackStack() },
    )
    var selected by remember { mutableStateOf(-1) }
    var color by remember { mutableStateOf(Color.Black) }

//    val code: String = """
//      @Composable
//      fun TextColor(){
//        Button(
//            onClick = {},
//            colors = ButtonConstants.defaultButtonColors(
//                backgroundColor = color
//            )
//        ) {
//            Text("Click Me")
//        }
//      }
//    """

    val code: AnnotatedString = codeBui {
        annotation(name = "Composable")
        function(name = "TextColor") {
            varString(name = "clicks")
            `class`(
                name = "Button",
                Argument("onClick", "code"),
                Argument("colors", """ButtonConstants.defaultButtonColors(
                            backgroundColor = color"
                        )"""),
            )
        }
    }

    CodeScaffold(
        title = "Button Color",
        goBack = state.goBack,
        code = code,
        sample = {
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = color
                )
            ) {
                Text("Click Me")
            }
        },
        options = {
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

        },
    )
}