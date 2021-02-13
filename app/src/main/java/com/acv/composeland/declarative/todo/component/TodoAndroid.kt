package com.acv.composeland.declarative.todo.component

import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.acv.composeland.declarative.Button
import com.acv.composeland.declarative.Default
import com.acv.composeland.declarative.Text
import com.acv.composeland.declarative.Tools
import com.acv.composeland.declarative.interpreter.android
import com.acv.composeland.suspend.AndroidComposeView


class TodoAndroid : AppCompatActivity() {

    override fun onCreate(rememberSaveable: Bundle?) {
        super.onCreate(rememberSaveable)

        val items = mutableListOf(
            TodoItem(
                text = Text("Make app"),
                image = Tools,
                completed = true,
            ),
            TodoItem(
                text = Text("Clean the house"),
                image = Default,
                completed = true,
            )
        )

        var program: () -> Unit = {}
        program = {
            setContent {
                TodoApp(
                    title = Text("Todo App"),
                    items = items,
                    add = Button(
                        text = "Add",
                        onClick = {
                            items.add(
                                TodoItem(
                                    text = Text("New"),
                                    image = Default,
                                    completed = true,
                                )
                            )
                            program()
                        }
                    ),
                )
            }
        }
        program()
    }
}

fun ComponentActivity.setContent(f: () -> Component) {
    val parent: AndroidComposeView = window.decorView.findViewById<ViewGroup>(android.R.id.content).run {
        if (getChildAt(0) as? AndroidComposeView == null) {
            val temp = AndroidComposeView(this@setContent)
            window.decorView.findViewById<ViewGroup>(android.R.id.content).addView(temp)
            temp
        } else {
            val temp = (getChildAt(0) as AndroidComposeView)
            temp.clean()
            temp
        }
    }
    f().children.onEach { it.android(parent) }
}
