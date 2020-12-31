package com.acv.composeland.appbar.bottom.design

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.acv.composeland.common.Body
import com.acv.composeland.common.H6

@Composable
fun Behaviour(behaviour: Behaviour) {
    H6(text = behaviour.layout.title)
    Body(
        modifier = Modifier.padding(top = 8.dp),
        text = behaviour.layout.description
    )

    val position = remember { mutableStateOf(FabPosition.Center) }
    Scaffold(
        modifier = Modifier.height(100.dp),
        bottomBar = {
            if (position.value == FabPosition.End) {
                BottomAppBar(cutoutShape = RoundedCornerShape(50)) {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.Menu)
                    }
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.Search)
                    }
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.Menu)
                    }
                }
            } else {
                BottomAppBar(cutoutShape = RoundedCornerShape(50)) {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.Menu)
                    }
                    // The actions should be at the end of the BottomAppBar
                    Spacer(Modifier.weight(1f, true))
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.Search)
                    }
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.Menu)
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { position.value = if (position.value == FabPosition.End) FabPosition.Center else FabPosition.End }) {
                Icon(Icons.Filled.Add)
            }
        },
        floatingActionButtonPosition = position.value,
        isFloatingActionButtonDocked = true,
    ) {}

    H6(text = behaviour.scolling.title)
    Body(
        modifier = Modifier.padding(top = 8.dp),
        text = behaviour.scolling.description
    )
}