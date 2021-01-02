package com.acv.composeland.button

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.acv.composeland.common.CodeScaffold


data class ButtonClickState(
    val goBack: () -> Unit,
)

@Composable
fun ButtonClick(
    navController: NavHostController
) {
    val state = ButtonClickState(
        goBack = { navController.popBackStack() },
    )

    var count by remember { mutableStateOf(0) }

    val code: String = """
      @Composable
      fun ButtonSample(){
        var count by remember { mutableStateOf(0) }
        Button(
            onClick = { count++ },
        ) {
            Text("$count clicks")
        }
      }
    """

    CodeScaffold(
        title = "Button Click",
        goBack = state.goBack,
        code = code,
        sample = {
            Button(
                onClick = { count++ },
            ) {
                Text("$count clicks")
            }
        },
        options = {

        }
    )

}