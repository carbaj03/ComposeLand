package com.acv.composeland.appbar.bottom

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.acv.composeland.appbar.bottom.design.Anatomy
import com.acv.composeland.common.Body
import com.acv.composeland.common.H6

@Composable
fun Anatomy(anatomy: Anatomy) {
    H6(text = anatomy.title)
    Body(
        modifier = Modifier.padding(top = 8.dp),
        text = anatomy.description
    )

    Scaffold(
        modifier = Modifier.height(100.dp),
        bottomBar = {
            BottomAppBar() {
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Menu)
                }
                Text(text = "Title")
                // The actions should be at the end of the BottomAppBar
                Spacer(Modifier.weight(1f, true))
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Favorite)
                }
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Favorite)
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Filled.Favorite)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
    ) {}

    Scaffold(
        modifier = Modifier.height(100.dp),
        bottomBar = {
            BottomAppBar() {
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Email)
                }
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Favorite)
                }
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Build)
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Filled.Send)
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = true,
    ) {}

    Scaffold(
        modifier = Modifier.height(100.dp),
        bottomBar = {
            BottomAppBar() {
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Menu)
                }
                // The actions should be at the end of the BottomAppBar
                Spacer(Modifier.weight(1f, true))
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Favorite)
                }
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Favorite)
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Filled.Add)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
    ) {}

    Scaffold(
        modifier = Modifier.height(100.dp),
        bottomBar = {
            BottomAppBar() {
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Menu)
                }
                // The actions should be at the end of the BottomAppBar
                Spacer(Modifier.weight(1f, true))
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Email)
                }
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Notifications)
                }
            }
        },
    ) {}


    H6(text = "Floating Action Button")
    Body(
        modifier = Modifier.padding(top = 8.dp),
        text = "When present, floating action buttons (FABs) are displayed on bottom app bars in one of two ways:\n" +
                "\n" +
                "Overlap: The FAB is at a higher elevation than the bottom app bar, and has no effect on the bar’s shape.\n" +
                "Inset: The FAB is at the same elevation as the bottom app bar, and the bar shape transforms to let the FAB dock in a notch carved into the bottom app bar.\n" +
                "Use an inset to increase the visual prominence of a FAB or accentuate customized element shapes. Refer to the Theming section for more guidance on inset FAB's in a bottom app bar."
    )

    Scaffold(
        modifier = Modifier.height(100.dp),
        bottomBar = {
            BottomAppBar() {
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
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Filled.Add)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
    ) {}

    Scaffold(
        modifier = Modifier.height(100.dp),
        bottomBar = {
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
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Filled.Add)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
    ) {}
}