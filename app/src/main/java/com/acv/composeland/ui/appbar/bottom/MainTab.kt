package com.acv.composeland.ui.appbar.bottom

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun FancyTab(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit,
    selected: Boolean,
    new: Boolean,
) {
    Tab(
        selected = selected,
        onClick = onClick,
        unselectedContentColor = Color.Gray
    ) {
        Row(
            Modifier.padding(10.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.align(Alignment.CenterVertically),
                imageVector = icon
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                style = MaterialTheme.typography.button.copy(textAlign = TextAlign.Center),
                text = title,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            if (new) {
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    style = MaterialTheme.typography.button.copy(textAlign = TextAlign.Center),
                    text = "New",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .border(
                            border = BorderStroke(1.dp, Color.Black),
                            shape = RoundedCornerShape(4.dp),
                        )
                        .padding(4.dp)
                )
            }
        }
    }
}