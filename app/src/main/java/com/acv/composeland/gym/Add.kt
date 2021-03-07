package com.acv.composeland.gym

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.acv.composeland.ui.chip.Chip
import com.acv.composeland.ui.common.ChipGroup

@ExperimentalAnimationApi
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Add(
    onBack: () -> Unit
) {
    Log.e("add", "recompose")
    var mode by remember { mutableStateOf(-1) }
    var value1 by remember { mutableStateOf("") }
    var value2 by remember { mutableStateOf("") }
    var value3 by remember { mutableStateOf("") }
    var predictions by remember { mutableStateOf(listOf<String>()) }
    var items by remember { mutableStateOf(mutableListOf<String>()) }

    Scaffold(
        topBar = { top(onBack) },
        scaffoldState = rememberScaffoldState(),
    ) {
        Column() {
            content(
                value1 = value1,
                onValueChange1 = {
                    when (it) {
                        is WithFocus -> {
                            predictions = listOf("Push", "Pull", "Leg")
                            items.add(it.text)
                            mode = 0
                            value1 = it.text
                        }
                        WithoutFocus -> mode = -1
                    }
                },
                value2 = value2,
                onValueChange2 = {
                    when (it) {
                        is WithFocus -> {
                            predictions = listOf("67", "70", "80")
                            items.add(it.text)
                            mode = 1
                            value2 = it.text
                        }
                        WithoutFocus -> mode = -1
                    }
                },
                value3 = value3,
                onValueChange3 = {
                    when (it) {
                        is WithFocus -> {
                            predictions = when (value1) {
                                "Leg" -> listOf("Squat", "SLDL", "Inc DB")
                                "Pull" -> listOf("Jalon", "Row", "Inc DB")
                                else -> listOf("Bench Press", "OHP", "Inc DB")
                            }
                            items.add(it.text)
                            mode = 2
                            value3 = it.text
                        }
                        WithoutFocus -> mode = -1
                    }
                },
                onPrediction = {
                    when (mode) {
                        0 -> value1 = it
                        1 -> value2 = it
                        2 -> value3 = it
                    }
                },
                items = items
            )
            Spacer(modifier = Modifier.weight(1f))
            ChipGroup(
                items = predictions,
                onChange = {
                    when (mode) {
                        0 -> value1 = it
                        1 -> value2 = it
                        2 -> value3 = it
                    }
                }
            )

            if (mode == -1) {
                bottom()
            } else {
                bottomText(
                    clear = {
                        when (mode) {
                            0 -> value1 = ""
                            1 -> value2 = ""
                        }
                    },
                    done = {
                        when (mode) {
                            0 -> value1 = value1.capitalize()
                            1 -> value2 = value2.capitalize()
                        }
                    },
                )
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
private fun content(
    items: List<String>,
    value1: String,
    value2: String,
    value3: String,
    onValueChange1: (CustomEvent) -> Unit,
    onValueChange2: (CustomEvent) -> Unit,
    onValueChange3: (CustomEvent) -> Unit,
    onPrediction: (String) -> Unit,
) {
    Log.e("content", "recompose")
    val transformation = remember { Type(listOf("Push", "Pull", "Leg")) }
    ConstraintLayout(
        Modifier.padding(20.dp).verticalScroll(rememberScrollState())
    ) {
        val (chips, title, kg, routine, row, space) = createRefs()

        LazyRow(
            modifier = Modifier.constrainAs(chips) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            },
            content = {
                item {
                    items.forEach {
                        var expanded by remember { mutableStateOf(false) }
                        Chip(
                            text = it,
                            onSelect = { expanded = it },
                            expand = {
                                Card(modifier = Modifier, elevation = 8.dp) {
                                    Row {
                                        Text(modifier = Modifier.padding(20.dp), text = "example")
                                        IconButton(onClick = { expanded = false }) {
                                            Icon(Icons.Filled.Clear, contentDescription = "Localized description")
                                        }
                                    }
                                }
                            },
                            expanded = expanded,
                        )
                    }
                }
            }
        )

        CustomTextField(
            modifier = Modifier.constrainAs(title) {
                start.linkTo(parent.start)
                top.linkTo(chips.bottom)
            },
            text = value1,
            placeholder = "Title",
            visualTransformation = transformation,
            onValueChange = onValueChange1,
        )

        CustomTextField(
            modifier = Modifier.constrainAs(kg) {
                start.linkTo(title.end)
                top.linkTo(chips.bottom)
            },
            placeholder = "Kg",
            keyboardType = KeyboardType.Number,
            onValueChange = onValueChange2,
            text = value2,
        )

        CustomTextField(
            modifier = Modifier.constrainAs(routine) {
                start.linkTo(parent.start)
                top.linkTo(title.bottom)
            },
            placeholder = "Routine",
            keyboardType = KeyboardType.Text,
            onValueChange = onValueChange3,
            text = value3,
        )
    }
}

@Composable
private fun top(onBack: () -> Unit) {
    Log.e("top", "recompose")
    TopAppBar(
        title = { },
        backgroundColor = Color.White,
        navigationIcon = {
            IconButton(onClick = { onBack() }) {
                Icon(Icons.Filled.Menu, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
            }
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
            }
        },
        elevation = 0.dp
    )
}

@Composable
private fun bottom() {
    Log.e("bottom", "recompose")
    BottomAppBar(
        backgroundColor = Color.White,
        cutoutShape = RoundedCornerShape(50),
    ) {
        IconButton(onClick = { /* doSomething() */ }) {
            Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
        }
        IconButton(onClick = { /* doSomething() */ }) {
            Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
        }
    }
}

@Composable
private fun bottomText(
    clear: () -> Unit,
    done: () -> Unit
) {
    Log.e("bottomText", "recompose")
    BottomAppBar(
        backgroundColor = Color.White,
        cutoutShape = RoundedCornerShape(50),
    ) {
        IconButton(onClick = { clear() }) {
            Icon(Icons.Filled.Clear, contentDescription = "Localized description")
        }
        IconButton(onClick = { done() }) {
            Icon(Icons.Filled.Done, contentDescription = "Localized description")
        }
    }
}

sealed class CustomEvent
data class WithFocus(val text: String) : CustomEvent()
object WithoutFocus : CustomEvent()


