package com.acv.composeland.declarative.todo.loading



sealed class Node

data class Text(
    val text: String
) : Node()

data class Button(
    val text: String,
    val onClick: () -> Unit,
) : Node()

data class Checked(
    val boolean: Boolean
) : Node()

sealed class Image : Node()

object Tools : Image()
object Default : Image()

sealed class Group(open val children: MutableList<Node>) : Node()
data class Row(override val children: MutableList<Node>) : Group(children)
data class Column(override val children: MutableList<Node>) : Group(children)