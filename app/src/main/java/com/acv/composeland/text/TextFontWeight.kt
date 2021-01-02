package com.acv.composeland.text

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Black
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.ExtraBold
import androidx.compose.ui.text.font.FontWeight.Companion.ExtraLight
import androidx.compose.ui.text.font.FontWeight.Companion.Light
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.font.FontWeight.Companion.Thin
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.acv.composeland.common.ChipGroup
import com.acv.composeland.common.CodeScaffold


data class TextFontWeightState(
    val goBack: () -> Unit,
)

@Composable
fun TextFontWeight(
    navController: NavHostController
) {
    val state = TextFontWeightState(goBack = { navController.popBackStack() })

    val a = listOf(
        Thin to "Thin",
        ExtraLight to "ExtraLight",
        Light to "Light",
        Normal to "Normal",
        Medium to "Medium",
        SemiBold to "SemiBold",
        Bold to "Bold",
        ExtraBold to "ExtraBold",
        Black to "Black",
    )
    var weight by remember { mutableStateOf(a[4]) }

    val code: String = """
      @Composable
      fun TextColor(){
            Text(
                text = code,
                fontWeight = ${weight.second}
            )
      }
    """

    CodeScaffold(
        title = "Text Font Weight",
        goBack = state.goBack,
        code = code,
        options = {
            ChipGroup(
                items = a.map { it.second },
                onChange = { vl -> weight = a.first { it.second == vl } }
            )
        },
        sample = {
            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .border(
                        border = BorderStroke(2.dp, Color.Black),
                        shape = RoundedCornerShape(8.dp),
                    )
                    .background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(8.dp),
                    )
                    .padding(8.dp),
                text = "Text sample color",
                fontWeight = weight.first,
            )
        }
    )
}