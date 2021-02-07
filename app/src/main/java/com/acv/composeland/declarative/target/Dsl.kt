//package com.acv.composeland.declarative.target.dsl
//
//import android.view.ViewGroup
//import android.widget.LinearLayout
//import android.widget.TextView
//
//interface Element {
//    fun render(interpreter: Interpreter)
//}
//
//@DslMarker
//annotation class Composable
//
//@Composable
//abstract class Node(val name: String) : Element {
//    val children = arrayListOf<Element>()
//    val attributes = hashMapOf<String, String>()
//
//    private fun <T : Element> emit(tag: T, init: T.() -> Unit): T {
//        tag.init()
//        children.add(tag)
//        return tag
//    }
//
//    override fun render(interpreter: Interpreter) {
//        interpreter.render(this)
//    }
//
//    fun stack(
//        orientation: Orientation,
//        init: Stack.() -> Unit
//    ): Stack =
//        emit(Stack(orientation), init)
//
//    fun text(text: String): Text =
//        emit(Text(text), { })
//
//    fun button(text: String, onClick: () -> Unit): Button =
//        emit(Button(text, onClick), { })
//
//    fun image(url: String): Image =
//        emit(Image(url), { })
//
//    fun a(href: String, init: A.() -> Unit) {
//        val a = emit(A(), init)
//        a.href = href
//    }
//}
//
//
//class Composer : Node("Composer")
//
//enum class Orientation {
//    Vertical, Horizontal
//}
//
//class Stack(val orientation: Orientation) : Node("Stack : $orientation")
//class Text(val text: String) : Node("text : $text")
//class Button(val text: String, val action: () -> Unit) : Node("button: $text")
//class Image(val url: String) : Node("image : $url")
//
//class A : Node("a") {
//    var href: String
//        get() = attributes["href"]!!
//        set(value) {
//            attributes["href"] = value
//        }
//}
//
//fun setContent(init: Composer.() -> Unit): Composer {
//    val html = Composer()
//    html.init()
//    return html
//}
//
//fun main() {
//    val b = Web(StringBuilder(), "")
//    setContent {
//        stack(Orientation.Vertical) {
//            button("next", {})
//            stack(Orientation.Horizontal) {
//                image("url\\:")
//                text("Hello")
//            }
//        }
//    }.render(b)
//    print(b.toString())
//}
//
//interface Interpreter {
//    fun render(node: Node)
//}
//
//object Android : Interpreter {
//
//    override fun render(node: Node) {
//
//    }
//
//    override fun render(composable: Composable) {
//
//        fun Node.android(root: ViewGroup) {
//            when (this) {
//                is Text -> root.addView(TextView(root.context).apply { text = this@android.text })
//                is Button -> root.addView(AppCompatButton(root.context).apply {
//                    text = this@android.text
//                    setOnClickListener { this@android.onClick() }
//                })
//                is Row -> {
//                    root.addView(LinearLayout(root.context).apply {
//                        orientation = LinearLayout.HORIZONTAL
//                        nodes.forEach { node -> node.android(this) }
//                    })
//                }
//                is Column -> {
//                    root.addView(LinearLayout(root.context).apply {
//                        orientation = LinearLayout.VERTICAL
//                        nodes.forEach { node -> node.android(this) }
//                    })
//                }
//                is Tools -> root.addView(AppCompatImageView(root.context).apply { setImageResource(R.drawable.ic_click) })
//                is Default -> root.addView(AppCompatImageView(root.context).apply { setImageResource(R.drawable.ic_click) })
//            }
//        }
//    }
//}
//
//data class Web(val builder: StringBuilder, val indent: String) : Interpreter {
//    override fun render(node: Node) {
//        builder.append("$indent<${node.name}${renderAttributes(node)}>\n")
//        for (c in node.children) {
//            c.render(copy(indent = "$indent  "))
//        }
//        builder.append("$indent</${node.name}>\n")
//    }
//
//    private fun renderAttributes(node: Node): String {
//        val builder = StringBuilder()
//        for ((attr, value) in node.attributes) {
//            builder.append(" $attr=\"$value\"")
//        }
//        return builder.toString()
//    }
//
//    override fun toString(): String {
//        return builder.toString()
//    }
//}