package com.acv.composeland.text

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.acv.composeland.common.ChipGroup
import com.acv.composeland.common.CodeScaffold

@Composable
fun TextFontSize(goBack : () -> Unit) {
    var size by remember { mutableStateOf(10f) }
    var unit by remember { mutableStateOf("sp") }

    val code: String = """
      @Composable
      fun TextColor(){
            Text(
                text = code,
                color = $size.sp
            )
      }
    """

    CodeScaffold(
        goBack = goBack,
        code = code
    ) {
        Text(
            text = "Text sample color",
            fontSize = if(unit == "sp") size.sp else size.em,
        )
        StepsSliderSample(onChange = { size = it })
        ChipGroup(
            items = listOf("sp", "em"),
            onChange = { unit = it }
        )
    }
}

@Composable
fun StepsSliderSample(onChange: (Float) -> Unit) {
    var sliderPosition by remember { mutableStateOf(0f) }
    Text(text = sliderPosition.toString())
    Slider(
        value = sliderPosition,
        onValueChange = { sliderPosition = it },
        valueRange = 1f..10f,
        onValueChangeEnd = { onChange(sliderPosition) },
        steps = 5,
        thumbColor = MaterialTheme.colors.secondary,
        activeTrackColor = MaterialTheme.colors.secondary
    )
}