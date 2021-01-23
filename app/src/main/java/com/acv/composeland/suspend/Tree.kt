package com.acv.composeland.suspend

data class TodoItem(
    val completed: Boolean,
    val title: String,
)

fun todoItemRepository(): List<TodoItem> =
    listOf(TodoItem(true, "buy"))

fun TodoApp(items: List<TodoItem>): Node =
    Stack(Orientation.Vertical).apply {
        for (item in items) {
            children.add(Stack(Orientation.Horizontal).apply {
                children.add(Text(if (item.completed) "x" else " "))
                children.add(Text(item.title))
            })
        }
    }

fun main() {
    val items = todoItemRepository()

    renderNodeToScreen(TodoApp(items))

    renderNodeToScreen(compose { TodoApp(items) })
}

interface Composer {
    // add node as a child to the current Node, execute
    // `content` with `node` as the current Node
    fun emit(node: Node, content: () -> Unit = {})
}

// naive implementation. feel free to skip.
class ComposerImpl(root: Node) : Composer {
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
}

//fun Composer.TodoApp(items: List<TodoItem>) {
//    emit(Stack(Orientation.Vertical)) {
//        for (item in items) {
//            emit(Stack(Orientation.Horizontal)) {
//                emit(Text(if (item.completed) "x" else " "))
//                emit(Text(item.title))
//            }
//        }
//    }
//}

fun compose(content: Composer.() -> Unit): Node =
    Stack(Orientation.Vertical).also {
        ComposerImpl(it).apply(content)
    }


fun Composer.TodoItem(item: TodoItem) {
    emit(Stack(Orientation.Horizontal)) {
        emit(Text(if (item.completed) "x" else " "))
        emit(Text(item.title))
    }
}

fun Composer.TodoApp(items: List<TodoItem>) {
    emit(Stack(Orientation.Vertical)) {
        for (item in items) {
            TodoItem(item)
        }
    }
}
