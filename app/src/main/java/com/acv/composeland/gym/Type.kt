package com.acv.composeland.gym

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle

class Type(val items: List<String>) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val prefix = text.text
        val result = items.firstOrNull { it.startsWith(prefix, true) }

        return when {
            prefix.isEmpty() || result == null -> TransformedText(text, OffsetMapping.Identity)
            else -> TransformedText(
                buildAnnotatedString {
                    append(prefix)
                    withStyle(SpanStyle(Color.Gray)) {
                        append(result.substring(prefix.length))
                    }
                },
                object : OffsetMapping {
                    override fun originalToTransformed(offset: Int): Int = offset
                    override fun transformedToOriginal(offset: Int): Int =
                        offset.coerceAtMost(prefix.length)
                }
            )
        }
    }

}