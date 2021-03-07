package com.acv.composeland.suspend

import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable

abstract class Node {
    val children = mutableListOf<Node>()
}

class NodeApplier(root: Node) : AbstractApplier<Node>(root) {
    override fun insertBottomUp(index: Int, instance: Node) {
        current.children.add(index, instance)
    }

    override fun insertTopDown(index: Int, instance: Node) {
        current.children.add(index, instance)
    }

    override fun move(from: Int, to: Int, count: Int) {
        current.children.move(from, to, count)
    }

    override fun onClear() {
        root.children.clear()
    }

    override fun remove(index: Int, count: Int) {
        current.children.remove(index, count)
    }
}

// A function like the following could be created to create a composition provided a root Node.
@OptIn(ExperimentalComposeApi::class)
fun Node.setContent(
    parent: CompositionContext,
    content: @Composable () -> Unit
): Composition {
    return Composition(NodeApplier(this), parent).apply {
        setContent(content)
    }
}

// assuming we have Node sub-classes like "TextNode" and "GroupNode"
class TextNode : Node() {
    var text: String = ""
    var onClick: () -> Unit = {}
}
class GroupNode : Node()

// Composable equivalents could be created
@Composable fun Text(text: String, onClick: () -> Unit = {}) {
    ComposeNode<TextNode, NodeApplier>(::TextNode) {
        set(text) { this.text = it }
        set(onClick) { this.onClick = it }
    }
}

@Composable fun Group(content: @Composable () -> Unit) {
    ComposeNode<GroupNode, NodeApplier>(::GroupNode, {}, content)
}

// and then a sample tree could be composed:
fun runApp(root: GroupNode, parent: CompositionContext) {
    GroupNode().setContent(parent) {
        var count by remember { mutableStateOf(0) }
        Group {
            Text("Count: $count")
            Text("Increment") { count++ }
        }
    }
}


enum class Orientation { Vertical, Horizontal }

class Stack(var orientation: Orientation) : Node()

class Text(var text: String) : Node()

class Button(var text: String, val onClick: () -> Unit) : Node()

fun renderNodeToScreen(node: Node, s: String = "") {
    var temp = s
    println(temp + node.toString())
    temp = "$temp -${temp.hashCode()} "
    node.children.forEach {
        renderNodeToScreen(it, temp)
    }
}

fun ComponentActivity.renderAndroid(
    node: Node,
    parent: ViewGroup = AndroidComposeView(this)
) {

    window.decorView.findViewById<ViewGroup>(android.R.id.content).run {
        if (getChildAt(0) as? AndroidComposeView == null) {
            window.decorView.findViewById<ViewGroup>(android.R.id.content).addView(parent)
        }
    }

    parent.addView(
        when (node) {
            is Stack -> createLayout(node).also { l ->
                node.children.forEach {
                    renderAndroid(it, l)
                }
            }
            is Text -> TextView(this).apply { text = node.text }
            is Button -> AppCompatButton(this).apply {
                text = node.text
                setOnClickListener { node.onClick() }
            }
            else -> TextView(this).apply { text = "node.text" }
        }.also {
            Log.e("tag", node.toString())
        }
    )
}

private fun ComponentActivity.createLayout(node: Stack) =
    if (node.orientation == Orientation.Vertical)
        LinearLayout(this).apply { orientation = LinearLayout.VERTICAL }
    else
        LinearLayout(this).apply { orientation = LinearLayout.HORIZONTAL }

fun main() {
    renderNodeToScreen(Text("Hello World!"))
}