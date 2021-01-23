package com.acv.composeland.abstract


sealed class Node
class Text(val text: String) : Node()
class Button(val text: String) : Node()
sealed class Image : Node()

sealed class Group : Node()
data class Row(val nodes: List<Node>) : Group()
class Column(val nodes: List<Node>) : Group()

data class Program(
    val nodes: List<Node>
)

data class ProgramState(
    val first: String = "Hello",
    val second: String = "World",
    val third: String = "Hola",
    val forth: String = "Mundo",
    val accept: String = "Accept",
    val cancel: String = "Cancel",
)

fun ProgramState.program() =
    Program(
        nodes = listOf(
            Column(
                nodes = listOf(
                    Row(
                        nodes = listOf(
                            Text(first),
                            Text(second),
                        )
                    ),
                    Row(
                        nodes = listOf(
                            Text(third),
                            Text(forth),
                        )
                    ),
                )
            ),
            Button(accept),
            Button(cancel),
        )
    )

fun main() {
    ProgramState().program().renderLog()
}

fun Program.renderLog() {
    nodes.forEach { node -> node.log() }
}

fun Node.log(indent: String = "") {
    when (this) {
        is Text -> println("${indent}Text: $text")
        is Button -> println("${indent}Button: $text")
        is Row -> {
            println("${indent}Row{")
            nodes.forEach { node -> node.log(indent = "$indent-") }
            println("}")
        }
        is Column -> {
            println("${indent}Column{")
            nodes.forEach { node -> node.log("$indent-") }
            println("}")
        }
    }
}