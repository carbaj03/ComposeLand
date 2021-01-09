package com.acv.composeland.ui.appbar.bottom.design

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.acv.composeland.ui.common.Body
import com.acv.composeland.ui.common.H6
import com.acv.composeland.ui.material.Principles
import com.acv.composeland.ui.material.RelatedItems

@Composable
fun Usage(usage: Usage) {
    Log.e("Usage", usage.toString())
    RelatedItems(
        modifier = Modifier.padding(top = 8.dp),
        items = usage.related
    )
    H6(
        modifier = Modifier.padding(vertical = 8.dp),
        text = "Principles"
    )
    Principles(
        items = usage.principles
    )
    H6(
        modifier = Modifier.padding(vertical = 8.dp),
        text = "When to Use",
    )
    Body(
        text = usage.whenToUsage
    )
}