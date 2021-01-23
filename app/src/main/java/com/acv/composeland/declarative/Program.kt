package com.acv.composeland.declarative

import com.acv.composeland.declarative.interpreter.log


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
            Button(accept, {}),
            Button(cancel,{} ),
        )
    )

fun main() {
    ProgramState().program().renderLog()
}

fun Program.renderLog() {
    nodes.forEach { node -> node.log() }
}