package com.acv.composeland.declarative.counter

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.acv.composeland.declarative.Button
import com.acv.composeland.declarative.State
import com.acv.composeland.declarative.Text
import com.acv.composeland.declarative.interpreter.android
import com.acv.composeland.suspend.AndroidComposeView


class CounterAndroid : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var recompose: () -> Unit = {}

        recompose = {
            setContent {
                var count = State(0)
                Counter(
                    text = Text("$count clicks"),
                    button = Button(
                        text = "Click",
                        onClick = { count.value++; recompose() }
                    ),
                )

            }
        }
        recompose()
    }
}

fun ComponentActivity.setContent(f: () -> Counter) {
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
    f().apply {
        text.android(parent)
        button.android(parent)
    }
}