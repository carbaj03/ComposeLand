package com.acv.composeland.declarative.interpreter

import com.acv.composeland.declarative.*

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