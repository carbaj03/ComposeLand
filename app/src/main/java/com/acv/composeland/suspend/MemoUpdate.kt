package com.acv.composeland.suspend.memo.update

import com.acv.composeland.suspend.*

data class TodoItem(
    val completed: Boolean,
    val title: String,
)

fun todoItemRepository(completed: Boolean): List<TodoItem> =
    listOf(
        TodoItem(completed = completed, title = "buy"),
        TodoItem(completed = true, title = "sell"),
    )

fun main() {
    renderNodeToScreen(compose { TodoApp(todoItemRepository(false)) })
    println()
    renderNodeToScreen(compose { TodoApp(todoItemRepository(true)) })
}

interface Composer {
    // add node as a child to the current Node, execute
    // `content` with `node` as the current Node
    fun emit(node: Node, content: () -> Unit = {})

    // Compare each input with the previous value at this position. If any
    // have changed, return result of factory, otherwise return previous result
    fun <T> memo(vararg inputs: Any?, factory: () -> T): T

    fun <T : Node> emit(
        factory: () -> T,
        update: (T) -> Unit = {},
        block: () -> Unit = {}
    )
}

// naive implementation. feel free to skip
class ComposerImpl(root: Node) : Composer {
    private var cache = mutableListOf<Any?>()
    private var index = 0
    private val inserting get() = index == cache.size
    private fun get(): Any? = cache[index++]
    private fun set(value: Any?) {
        if (inserting) {
            index++; cache.add(value); } else cache[index++] = value
    }

    private var current: Node = root

    override fun emit(node: Node, content: () -> Unit) {
        // store current parent to restore later
        val parent = current
        parent.children.add(node)
        current = node
        // with `current` set to `node`, we execute the passed in lambda
        // in the "scope" of it, so that emitted nodes inside of this
        // lambda end up as children to `node`.
        content()
        // restore current
        current = parent
    }

    override fun <T> memo(vararg inputs: Any?, factory: () -> T): T {
        var valid = true
        // we need to make sure we check every input, every time. no short-circuiting.
        for (input in inputs) {
            // it is not valid if any of the inputs have changed from last time
            valid = !changed(input) && valid
        }
        return cache(!valid) { factory() }
    }

    override fun <T : Node> emit(
        factory: () -> T,
        update: (T) -> Unit,
        block: () -> Unit
    ) {
        val node = memo(factory = factory)
        update(node)
        emit(node, block)
    }

    private fun <T> changed(value: T): Boolean {
        // if we are inserting, we have nothing to compare against,
        // so just store it and return
        return if (inserting) {
            set(value)
            false
        } else {
            // get current item, increment index. always store new
            // value, but return true only if they don't compare
            val index = index++
            val item = cache[index]
            cache[index] = value
            item != value
        }
    }

    private fun <T> cache(update: Boolean, factory: () -> T): T {
        // if we are asked to update the value, or if it is the first time
        // the cache is consulted, we need to execute the factory, and save
        // the result
        return if (inserting || update) factory().also { set(it) }
        // otherwise, just return the value in the cache
        else get() as T
    }
}

fun compose(content: Composer.() -> Unit): Node =
    Stack(Orientation.Vertical).also {
        ComposerImpl(it).apply(content)
    }

fun Composer.TodoItem(item: TodoItem) {
    emit({ Stack(Orientation.Horizontal) }) {
        emit(
            { Text("") },
            { memo(item.completed) { it.text = if (item.completed) "x" else " " } }
        )
        emit(
            { Text("") },
            { memo(item.title) { it.text = item.title } }
        )
    }
}

fun Composer.TodoApp(items: List<TodoItem>) {
    emit({ Stack(Orientation.Vertical) }) {
        for (item in items) {
            TodoItem(item)
        }
    }
    var text = "Total: ${items.size} items"
    emit(
        { Text("") },
        { memo(text) { it.text = text } }
    )
    emit(
        { Button("as") { text += " add" } },
        { memo(text) { it.text = text } }
    )
}