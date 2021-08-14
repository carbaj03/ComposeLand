package com.acv.composeland.gym

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    text: String = "",
    placeholder: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (CustomEvent) -> Unit = {},
) {
    Log.e("CustomTextField", "recompose")
    var focus by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        if (text.isBlank()) {
            Text(placeholder)
        }

        BasicTextField(
            modifier = Modifier.onFocusChanged {
                focus = it.isFocused
                onValueChange(if (focus) WithFocus(text) else WithoutFocus)
            },
            value = text,
            visualTransformation = visualTransformation,
            onValueChange = {
                onValueChange(if (focus) WithFocus(it) else WithoutFocus)
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Default, keyboardType = keyboardType),
//            singleLine = false,
        )
    }
}