package com.acv.composeland.declarative.helloworld

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


class HelloWorldAndroid : AppCompatActivity() {

    override fun onCreate(rememberSaveable: Bundle?) {
        super.onCreate(rememberSaveable)
        setContent {
            HelloWorld(
                text = Text("Hello World")
            )
        }
    }
}

fun ComponentActivity.setContent(f: () -> HelloWorld) {
    val parent = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL }
    window.decorView.findViewById<ViewGroup>(android.R.id.content).run {
        if (getChildAt(0) as? AndroidComposeView == null) {
            window.decorView.findViewById<ViewGroup>(android.R.id.content).addView(parent)
        }
    }
    f().text.android(parent)
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