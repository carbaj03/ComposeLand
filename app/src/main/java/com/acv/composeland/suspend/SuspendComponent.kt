package com.acv.composeland.suspend

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import arrow.core.Either
import arrow.fx.*
import com.acv.composeland.R
import com.acv.composeland.suspend.memo.update.TodoApp
import com.acv.composeland.suspend.memo.update.compose
import com.acv.composeland.suspend.memo.update.todoItemRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.launch
import kotlin.coroutines.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(rememberSaveable: Bundle?) {
        super.onCreate(rememberSaveable)

//        myContent {
//            Log()
////            launchLog()
//        }
////
//        setContent {
//        }

        val a = compose { TodoApp(todoItemRepository(false)) }
        renderNodeToScreen(a)
        renderAndroid(a)
        renderAndroid(a)
    }
}

//@RestrictsSuspension
//fun interface Compose<F> {
//    fun control(): ComposeEffect<F>
//}

sealed class Composable

//suspend fun Log() {
//    composer().name[0] = ""
//    Log.e("name", composer().name.toString())
//}


//val composer = CoroutineCompose<String, Int>(hashMapOf(), "asdfs")

fun ComponentActivity.myContent(f: suspend () -> Unit) {
//    compose {
    lifecycleScope.launch(CoroutineCompose<Any>(mutableListOf(), this)) {
        f()
    }
//    }
}


fun launchLog() {
    GlobalScope.launch {
        Log.e("name", composer().context.toString())
//        Log.e("name", (kotlin.coroutines.coroutineContext[CoroutineCompose] as CoroutineCompose).context)
    }
}

//fun ComponentActivity.myContent(
//    parent: CompositionReference = Recomposer.current(),
//    content: suspend () -> Unit
//) {
//    lifecycleScope.launch(context = CoroutineCompose<Int, Int>(hashMapOf(), "name")) {
//        content()
//    }
//}

data class CoroutineCompose<A>(
    /**
     * User-defined coroutine name.
     */
    val name: MutableList<A>,
    val context: Context,
) : AbstractCoroutineContextElement(Key) {

    /**
     * Key for [CoroutineName] instance in the coroutine context.
     */
    companion object Key : CoroutineContext.Key<CoroutineCompose<*>>

    /**
     * Returns a string representation of the object.
     */
    override fun toString(): String = "CoroutineCompose($name)"
}


suspend fun SuspendComponent() {


}

/**
 * User-specified name of coroutine. This name is used in debugging mode.
 * See [newCoroutineContext][CoroutineScope.newCoroutineContext] for the description of coroutine debugging facilities.
 */
public data class CoroutineName(
    /**
     * User-defined coroutine name.
     */
    val name: String
) : AbstractCoroutineContextElement(CoroutineName) {
    /**
     * Key for [CoroutineName] instance in the coroutine context.
     */
    public companion object Key : CoroutineContext.Key<CoroutineName>

    /**
     * Returns a string representation of the object.
     */
    override fun toString(): String = "CoroutineName($name)"
}


@InternalCoroutinesApi
fun CoroutineScope.launch(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {
    val newContext = newCoroutineContext(context)
    val coroutine = StandaloneCoroutine(newContext, active = true)

    coroutine.start(start, coroutine, block)
    return coroutine
}


@InternalCoroutinesApi
private open class StandaloneCoroutine(
    parentContext: CoroutineContext,
    active: Boolean
) : AbstractCoroutine<Unit>(parentContext, active) {
    override fun handleJobException(exception: Throwable): Boolean {
        handleCoroutineException(context, exception)
        return true
    }
}