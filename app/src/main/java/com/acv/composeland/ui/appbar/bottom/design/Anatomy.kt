package com.acv.composeland.ui.appbar.bottom

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
import com.acv.composeland.ui.appbar.bottom.design.Anatomy
import com.acv.composeland.ui.common.Body
import com.acv.composeland.ui.common.H6

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
                    Icon(Icons.Filled.Menu, null)
                }
                Text(text = "Title")
                // The actions should be at the end of the BottomAppBar
                Spacer(Modifier.weight(1f, true))
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Favorite, null)
                }
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Favorite, null)
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Filled.Favorite, null)
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
                    Icon(Icons.Filled.Email, null)
                }
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Favorite, null)
                }
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Build, null)
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Filled.Send, null)
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
                    Icon(Icons.Filled.Menu, null)
                }
                // The actions should be at the end of the BottomAppBar
                Spacer(Modifier.weight(1f, true))
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Favorite, null)
                }
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Favorite, null)
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Filled.Add, null)
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
                    Icon(Icons.Filled.Menu, null)
                }
                // The actions should be at the end of the BottomAppBar
                Spacer(Modifier.weight(1f, true))
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Email, null)
                }
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Notifications, null)
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
                    Icon(Icons.Filled.Menu, null)
                }
                // The actions should be at the end of the BottomAppBar
                Spacer(Modifier.weight(1f, true))
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Search, null)
                }
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Menu, null)
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Filled.Add, null)
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
                    Icon(Icons.Filled.Menu, null)
                }
                // The actions should be at the end of the BottomAppBar
                Spacer(Modifier.weight(1f, true))
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Search, null)
                }
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Menu, null)
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Filled.Add, null)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
    ) {}
}