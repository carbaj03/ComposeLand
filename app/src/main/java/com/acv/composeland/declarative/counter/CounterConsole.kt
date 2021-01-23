package com.acv.composeland.declarative.counter

import com.acv.composeland.declarative.Button
import com.acv.composeland.declarative.Text
import com.acv.composeland.declarative.interpreter.log
import java.io.BufferedReader
import java.io.InputStreamReader


fun main() {
    var recompose: () -> Unit = {}
    var count = 0
    recompose = {
        setContent {
            Counter(
                text = Text("$count clicks"),
                button = Button(
                    text = "Click",
                    onClick = { count++; recompose() }
                ),
            )

        }
    }
    recompose()
}

fun setContent(f: () -> Counter) {
    f().apply {
        text.log()
        button.log()
        val reader = BufferedReader(InputStreamReader(System.`in`))
        reader.readLine()
        button.onClick()
    }
}