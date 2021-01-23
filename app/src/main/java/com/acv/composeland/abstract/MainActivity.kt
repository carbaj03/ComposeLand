package com.acv.composeland.abstract

import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.acv.composeland.suspend.AndroidComposeView
import com.acv.composeland.ui.screen.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        program.renderAndroid(this)
    }
}

fun Program.renderAndroid(context: ComponentActivity) {
    val parent = LinearLayout(context).apply { orientation = LinearLayout.VERTICAL }
    context.window.decorView.findViewById<ViewGroup>(android.R.id.content).run {
        if (getChildAt(0) as? AndroidComposeView == null) {
            context.window.decorView.findViewById<ViewGroup>(android.R.id.content).addView(parent)
        }
    }
    nodes.forEach { node -> node.android(parent) }
}

fun Node.android(root: ViewGroup) {
    when (this) {
        is Text -> root.addView(TextView(root.context).apply { text = this@android.text })
        is Button -> root.addView(AppCompatButton(root.context).apply { text = this@android.text })
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
    }
}