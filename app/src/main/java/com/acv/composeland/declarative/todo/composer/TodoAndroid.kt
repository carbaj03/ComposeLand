package com.acv.composeland.declarative.todo.composer

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.acv.composeland.suspend.AndroidComposeView


sealed class Event {
    object Loading : Event()
    object Initial : Event()
    object Add : Event()
}

data class State(
    val title: String,
    val items: List<TodoItem>,
    val add: String,
)

fun program(
    state: State,
    update: (Event, State) -> Unit
): TodoApp {
    return Main(
        title = Text(state.title),
        items = state.items,
        add = Button(
            text = state.add,
            onClick = { update(Event.Add, state) }
        ),
    )
}

typealias Recompose = (event: Event, state: State) -> Unit

class TodoAndroid : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var recompose: Recompose = { _, _ -> }
        recompose = { event, state ->
            setContent {
                event.sideEffects(state, recompose)
                state.reduce(event, recompose)
            }
        }
        recompose(
            Event.Loading,
            State(
                title = "Todo App",
                items = emptyList(),
                add = "Add",
            )
        )
    }
}

fun Event.sideEffects(state: State, recompose: Recompose) {
    when (this) {
        Event.Loading -> {

            recompose(Event.Initial, state)
        }
    }
}

fun State.reduce(
    event: Event,
    recompose: Recompose
): TodoApp {

    return when (event) {
        is Event.Initial ->
            program(
                state = copy(
                    items = listOf(
                        TodoItem(
                            text = Text("Make app"),
                            image = Tools,
                            completed = Checked(true),
                        ),
                        TodoItem(
                            text = Text("Clean the house"),
                            image = Default,
                            completed = Checked(true),
                        )
                    )
                ),
                update = recompose
            )
        is Event.Add ->
            program(
                state = copy(
                    items = items.plus(
                        TodoItem(
                            text = Text("New"),
                            image = Default,
                            completed = Checked(true),
                        )
                    )
                ),
                update = recompose
            )
        Event.Loading -> Loading
    }
}


fun <A> A.setContent(f: () -> Composer) {
    Log.e("comntent", "saf")
    when (this) {
        is ComponentActivity -> android(f)
        is String -> html(f)
        else -> log(f)
    }
}

private fun ComponentActivity.android(f: () -> Composer) {
    val parent: AndroidComposeView = window.decorView.findViewById<ViewGroup>(android.R.id.content).run {
        if (getChildAt(0) as? AndroidComposeView == null) {
            val temp = AndroidComposeView(this@android)
            window.decorView.findViewById<ViewGroup>(android.R.id.content).addView(temp)
            temp
        } else {
            val temp = (getChildAt(0) as AndroidComposeView)
            temp.clean()
            temp
        }
    }

    f().children.onEach {
        it.android(parent)
    }
}

private fun log(f: () -> Composer) {
    f().children.onEach {
        it.log()
    }
}

private fun html(f: () -> Composer) {
    f().children.onEach {
        Log.e("log", it.html())
    }
}