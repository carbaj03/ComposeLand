package com.acv.composeland.declarative.helloworld

import com.acv.composeland.declarative.Text
import com.acv.composeland.declarative.interpreter.log


fun main() {
    setContent {
        HelloWorld(
            text = Text("Hello World")
        )
    }
}

fun setContent(f: () -> HelloWorld) {
    f().text.log()
}