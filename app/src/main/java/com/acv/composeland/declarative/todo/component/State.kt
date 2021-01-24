package com.acv.composeland.declarative.todo.component

import com.acv.composeland.declarative.Button
import com.acv.composeland.declarative.Image
import com.acv.composeland.declarative.Node
import com.acv.composeland.declarative.Text

data class TodoApp(
    val title: Text,
    val items: List<TodoItem>,
    val add: Button,
) : Component {
    override val children: List<Node> = mutableListOf<Node>().apply {
        add(title)
        items.forEach { addAll(it.children) }
        add(add)
    }
}

data class TodoItem(
    val text: Text,
    val completed: Boolean,
    val image: Image,
) : Component {
    override val children: List<Node> =
        listOf(text, image)
}