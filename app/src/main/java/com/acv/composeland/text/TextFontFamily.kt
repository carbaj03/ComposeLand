package com.acv.composeland.text

import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.NavHostController
import com.acv.composeland.common.ChipGroup
import com.acv.composeland.common.CodeScaffold

data class TextFontFamilyState(
    val goBack: () -> Unit,
)

@Composable
fun TextFontFamily(
    navController: NavHostController
) {
    val state = TextFontFamilyState( goBack = { navController.popBackStack() })

    val a = listOf(
        FontFamily.Default to "Default",
        FontFamily.Cursive to "Cursive",
        FontFamily.SansSerif to "SansSerif",
        FontFamily.Serif to "Serif",
        FontFamily.Monospace to "Monospace"
    )
    var family by remember { mutableStateOf(a[0]) }

    val code: String = """
      @Composable
      fun TextColor(){
            Text(
                text = code,
                color = ${family.second}
            )
      }
    """

    CodeScaffold(
        title = "Text Font Family",
        goBack = state.goBack,
        code = code,
        options = {
            ChipGroup(
                items = a.map { it.second },
                onChange = { vl -> family = a.first { it.second == vl } }
            )
        },
        sample = {
            Text(
                text = "Text sample color",
                fontFamily = family.first,
            )
        }
    )
}