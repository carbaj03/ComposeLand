package com.acv.composeland.ui.text

import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontStyle
import androidx.navigation.NavHostController
import com.acv.composeland.ui.common.ChipGroup
import com.acv.composeland.ui.common.CodeScaffold

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
        title = "Text Font Style",
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