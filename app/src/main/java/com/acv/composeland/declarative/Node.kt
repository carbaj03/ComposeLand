package com.acv.composeland.declarative

sealed class Node
class Text(val text: String) : Node()
class Button(val text: String) : Node()
sealed class Image : Node()

sealed class Group : Node()
data class Row(val nodes: List<Node>) : Group()
class Column(val nodes: List<Node>) : Group()