package com.acv.composeland.ui.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.acv.composeland.ui.common.ChipGroup
import com.acv.composeland.ui.common.CodeScaffold
import java.util.*

data class ButtonBorderState(
    val goBack: () -> Unit,
)

@Composable
fun ButtonBorder(
    navController: NavHostController
) {
    val state = ButtonBorderState(
        goBack = { navController.popBackStack() },
    )
    val a: HashMap<String, BorderStroke> = hashMapOf(
        "Out" to ButtonDefaults.outlinedBorder,
    )
    var switch by remember { mutableStateOf("Out") }

    val code: String = """
      @Composable
      fun ButtonSample(){
        var count by remember { mutableStateOf(0) }
        var switch by remember { mutableStateOf(false) }
        Button(
            onClick = { count++ },
            enabled = $switch,
        ) {
            Text("Sample")
        }
      }
    """

    CodeScaffold(
        title = "Button Border",
        goBack = state.goBack,
        code = code,
        sample = {
            ChipGroup(
                items = a.keys.toList(),
                onChange = { switch = it }
            )
        },
        options = {
            Button(
                onClick = { },
                border = a[switch]!!,
            ) {
                Text("Sample")
            }
        },
    )
}