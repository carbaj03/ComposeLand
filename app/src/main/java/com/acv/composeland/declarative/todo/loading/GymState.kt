package com.acv.composeland.declarative.todo.loading

sealed class GymApp : Composer

object LoadingGym : GymApp() {
    override val children: MutableList<Node> = mutableListOf(Text("Loading"))
}

data class GymScreen(
    val title: Text,
    val items: List<TodoItem>,
    val add: Button,
) : GymApp() {
    override val children: MutableList<Node> = mutableListOf<Node>().apply {
        add(title)
        items.forEach { addAll(it.children) }
        add(add)
    }
}

data class GymItem(
    val column: Column,
    val exercise: Text,
    val muscle: Text,
    val image: Image,
) : Composer {
    override val children: MutableList<Node> =
        mutableListOf()
}