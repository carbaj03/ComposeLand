package com.acv.composeland.text

import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.acv.composeland.common.ChipGroup
import com.acv.composeland.common.CodeScaffold

@Composable
fun TextLetterSpacing(goBack : () -> Unit) {
    var size by remember { mutableStateOf(10f) }
    var unit by remember { mutableStateOf("sp") }

    val code: String = """
      @Composable
      fun TextColor(){
            Text(
                text = code,
                letterSpacing = $size.sp
            )
      }
    """

    CodeScaffold(
        goBack = goBack,
        code = code
    ) {
        Text(
            text = "Text sample color",
            letterSpacing = if(unit == "sp") size.sp else size.em,
        )
        StepsSliderSample(onChange = { size = it })
        ChipGroup(
            items = listOf("sp", "em"),
            onChange = { unit = it }
        )
    }
}