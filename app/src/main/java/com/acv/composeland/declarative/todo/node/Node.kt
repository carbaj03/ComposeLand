package com.acv.composeland.declarative.todo.node


//all nodes need to implement the children field but some not need it
interface Node {
    val children: MutableList<Node>
}

data class Text(
    val text: String
) : Node {
    override val children: MutableList<Node> = mutableListOf()
}

data class Button(
    val text: String,
    val onClick: () -> Unit,
) : Node {
    override val children: MutableList<Node> = mutableListOf()
}

sealed class Image : Node {
    override val children: MutableList<Node> = mutableListOf()
}

object Tools : Image()
object Default : Image()

sealed class Group(override val children: MutableList<Node>) : Node
data class Row(override val children: MutableList<Node>) : Group(children)
data class Column(override val children: MutableList<Node>) : Group(children)