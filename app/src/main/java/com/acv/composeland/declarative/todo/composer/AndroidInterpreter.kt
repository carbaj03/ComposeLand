package com.acv.composeland.declarative.todo.composer

import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatImageView
import com.acv.composeland.R


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
        is Checked -> root.addView(AppCompatCheckBox(root.context).apply { isChecked = boolean })
    }
}

fun Node.log(indent: String = "") {
    when (this) {
        is Text -> println("${indent}Text: $text")
        is Button -> {
            println("${indent}Button: $text")
        }
        is Row -> {
            println("${indent}Row{")
            children.forEach { node -> node.log(indent = "$indent-") }
            println("}")
        }
        is Column -> {
            println("${indent}Column{")
            children.forEach { node -> node.log("$indent-") }
            println("}")
        }
        is Checked -> println("${indent}Checked: $boolean")
        is Tools -> println("${indent}Image: tools")
        is Default -> println("${indent}Image: default")
    }
}

fun Node.html(code: String = "") : String =
    when (this) {
        is Text -> "$code<p>$text</p>"
        is Button -> "$code<button type=\"button\" onclick=\"\">$text</button>"
        is Row ->""
//            "$code<div>${children.forEach { node -> node.html(code) }}</div>"

        is Column -> ""
//            "$code<div>${children.forEach { node -> node.html(code) }}</div>"
        is Tools -> "$code<img src=\"img_chania.jpg\" alt=\"Flowers in Chania\">"
        is Default -> "$code<img src=\"img_chania.jpg\" alt=\"Flowers in Chania\">"
        is Checked ->
            "$code<input type=\"checkbox\" id=\"vehicle1\" name=\"vehicle1\" value=\"Bike\" ${
                if (boolean) {
                    "checked"
                } else {
                    ""
                }
            }>"
    }