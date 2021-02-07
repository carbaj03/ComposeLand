package com.acv.composeland.declarative.target


interface Composable

interface Action
data class Add(val text: String) : Action

typealias Dispatcher = (Action) -> Unit

data class App(
    val container: List<Composable>,
) : Composable


data class Row(val children: List<Composable>) : Composable
data class Column(val children: List<Composable>) : Composable
data class Button(
    val text: String,
    val action: () -> Unit,
) : Composable

data class Text(
    val text: String,
) : Composable

data class Image(
    val vector: String,
) : Composable

data class Toolbar(
    val title: String,
) : Composable

object Repo {
    private val todos: MutableList<Todo> = mutableListOf()

    fun put(todo: Todo) {
        todos.add(todo)
    }

    fun getAll() = todos
}

data class Todo(val title: String)

fun app() =
    App(
        container = listOf(
            Column(
                children = listOf(
                    Toolbar("App"),
                    Row(
                        children = listOf(
                            Image("sadf"),
                            Text("Item 1"),
                        )
                    ),
                    Row(
                        children = listOf(
                            Image("sadf"),
                            Text("Item 2"),
                        )
                    ),
                    Button(
                        text = "Add",
                        action = { },
                    )
                )
            )
        )
    )

fun main() {
    app().container.forEach {  }
}

/*
list
--list
----Toolbar
----list
------Image
------Text
----list
------Image
------Text
----Button
 */

/*
Group(1)
Group(2)
Toolbar
Group(3)
Image
Text
EndGroup(3)
Group(4)
Image
Text
EndGroup(4)
Button
EndGroup(2)
EndGroup(1)
 */