package com.acv.composeland.declarative.target.compose


interface Composable
interface Item : Composable
interface Group : Composable {
    val children: Compose.() -> Unit
}

data class App(override val children: Compose.() -> Unit) : Group

data class Row(override val children: Compose.() -> Unit) : Group
data class Column(override val children: Compose.() -> Unit) : Group
data class Button(
    val text: String,
    val action: () -> Unit,
) : Item

data class Text(
    val text: String,
) : Item

data class Image(
    val vector: String,
) : Item


fun Compose.Toolbar() = Toolbar("")
data class Toolbar(
    val title: String,
) : Item

object Repo {
    private val todos: MutableList<Todo> = mutableListOf()

    fun put(todo: Todo) {
        todos.add(todo)
    }

    fun getAll() = todos
}

data class Todo(val title: String)

interface Compose {
    val children: List<Composable>

    fun emit(composable: Composable)
}

val a = object : Compose {
    override val children: List<Composable> = emptyList()

    override fun emit(composable: Composable) {
        children
    }

}

fun app() =
    App {
        Column {
            Toolbar("")
            Row {
                Image("")
                Text("")
            }
        }
    }


fun main() {
}