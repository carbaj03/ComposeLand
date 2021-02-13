package com.acv.composeland.declarative.counter

import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import com.acv.composeland.R
import com.acv.composeland.declarative.*
import com.acv.composeland.suspend.AndroidComposeView


class CounterAndroid : AppCompatActivity() {

    override fun onCreate(rememberSaveable: Bundle?) {
        super.onCreate(rememberSaveable)
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

fun Node.android(root: ViewGroup) {
    when (this) {
        is Text -> root.addView(TextView(root.context).apply { text = this@android.text })
        is Button -> root.addView(AppCompatButton(root.context).apply {
            text = this@android.text
            setOnClickListener { this@android.onClick() }
        })
        is Row -> {
            root.addView(LinearLayout(root.context).apply {
                orientation = LinearLayout.HORIZONTAL
                nodes.forEach { node -> node.android(this) }
            })
        }
        is Column -> {
            root.addView(LinearLayout(root.context).apply {
                orientation = LinearLayout.VERTICAL
                nodes.forEach { node -> node.android(this) }
            })
        }
        is Tools -> root.addView(AppCompatImageView(root.context).apply { setImageResource(R.drawable.ic_click) })
        is Default -> root.addView(AppCompatImageView(root.context).apply { setImageResource(R.drawable.ic_click) })
    }
}