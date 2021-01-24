package com.acv.composeland.declarative.todo.node

data class TodoApp(
    val title: Text,
    val items: List<TodoItem>,
    val add: Button,
) : Node {
    override val children: MutableList<Node> = mutableListOf<Node>().apply {
        add(title)
        items.forEach { addAll(it.children) }
        add(add)
    }
}

data class TodoItem(
    val text: Text,
    val completed: Boolean,
    val image: Image,
) : Node {
    override val children: MutableList<Node> =
        mutableListOf(text, image)
}