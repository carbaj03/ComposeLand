package com.acv.composeland.text

import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontStyle
import androidx.navigation.NavHostController
import com.acv.composeland.common.ChipGroup
import com.acv.composeland.common.CodeScaffold

data class TextFontStyleState(
    val goBack: () -> Unit
)

@Composable
fun TextFontStyle(
    navController: NavHostController
) {
    val state = TextFontStyleState(goBack = { navController.popBackStack() })

    var style by remember { mutableStateOf(FontStyle.Normal) }

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
            Text(
                text = "Text sample color",
                fontStyle = style,
            )
        },
        options = {
            ChipGroup(
                items = listOf("Normal", "Italic"),
                onChange = { style = if (it == "Normal") FontStyle.Normal else FontStyle.Italic }
            )
        }
    )
}