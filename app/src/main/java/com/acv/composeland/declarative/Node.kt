package com.acv.composeland.declarative

sealed class Node
class Text(val text: String) : Node()
class Button(
    val text: String,
    val onClick : () -> Unit,
) : Node()

sealed class Image : Node()
object Tools : Image()
object Default : Image()

sealed class Group : Node()
data class Row(val nodes: List<Node>) : Group()
class Column(val nodes: List<Node>) : Group()

//data class State<A>(var value : A) : Node()