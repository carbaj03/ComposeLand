package com.acv.composeland.declarative.todo.loading



sealed class TodoApp : Composer

object Loading : TodoApp() {
    override val children: MutableList<Node> = mutableListOf(Text("Loading"))
}

data class Main(
    val title: Text,
    val items: List<TodoItem>,
    val add: Button,
) : TodoApp() {
    override val children: MutableList<Node> = mutableListOf<Node>().apply {
        add(title)
        items.forEach { addAll(it.children) }
        add(add)
    }
}

data class TodoItem(
    val text: Text,
    val completed: Checked,
    val image: Image,
) : Composer {
    override val children: MutableList<Node> =
        mutableListOf(text, image)
}