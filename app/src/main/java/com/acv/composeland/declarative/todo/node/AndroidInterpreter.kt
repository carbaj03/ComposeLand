package com.acv.composeland.declarative.todo.node

import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import com.acv.composeland.R


//each time i add a node i need to change this implementation
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
                children.forEach { node -> node.android(this) }
            })
        }
        is Column -> {
            root.addView(LinearLayout(root.context).apply {
                orientation = LinearLayout.VERTICAL
                children.forEach { node -> node.android(this) }
            })
        }
        is Tools -> root.addView(AppCompatImageView(root.context).apply { setImageResource(R.drawable.ic_click) })
        is Default -> root.addView(AppCompatImageView(root.context).apply { setImageResource(R.drawable.ic_click) })
        is TodoApp -> root.addView(LinearLayout(root.context).apply {
            orientation = LinearLayout.VERTICAL
            children.forEach { node -> node.android(this) }
        })
        is TodoItem -> root.addView(LinearLayout(root.context).apply {
            orientation = LinearLayout.VERTICAL
            children.forEach { node -> node.android(this) }
        })
    }
}