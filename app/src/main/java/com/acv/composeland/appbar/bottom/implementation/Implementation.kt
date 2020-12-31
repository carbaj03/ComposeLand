package com.acv.composeland.appbar.bottom.implementation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.acv.composeland.appbar.bottom.BottomAppBarMainState

@Composable
fun Implementation(state: BottomAppBarMainState){
    state.items.forEach { screen ->
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { screen.goToDetail() }
        ) {
            Text(text = screen.name)
        }
    }
}