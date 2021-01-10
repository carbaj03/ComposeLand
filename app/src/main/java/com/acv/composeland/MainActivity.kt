package com.acv.composeland

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import arrow.continuations.Effect
import arrow.core.identity
import com.acv.composeland.ui.ComposeLandTheme
import com.acv.composeland.ui.screen.*
import com.acv.composeland.ui.theming.theming
import kotlinx.coroutines.*
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.startCoroutine


val a = CoroutineCompose(hashMapOf(1 to 1), aplication) + CoroutineName("Example")

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AmbientContext.current
        }


        lifecycleScope.launch(a) {
            TextView()
            Log.e("name", (this.coroutineContext[CoroutineName] as CoroutineName).name)
            Log.e("name", (this.coroutineContext[Job].toString()))
            Log.e("compose", (this.coroutineContext[CoroutineCompose] as CoroutineCompose<Int, Int>).toString())

        }
        setContent {
            ComposeLandTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    AppMain()
                }
            }
        }
    }
}


suspend fun TextView() =
    (a[CoroutineCompose] as CoroutineCompose<Int, Int>).toString()

class TextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

}


interface setContent {

//    public fun CoroutineScope.launch(
//        context: CoroutineContext = EmptyCoroutineContext,
//        start: CoroutineStart = CoroutineStart.DEFAULT,
//        block: suspend CoroutineScope.() -> Unit
//    ): Job {
//        val newContext = newCoroutineContext(context)
//        val coroutine = if (start.isLazy)
//            LazyStandaloneCoroutine(newContext, block) else
//            StandaloneCoroutine(newContext, active = true)
//        coroutine.start(start, coroutine, block)
//        return coroutine
//    }
//
//    operator fun <A> invoke(f: suspend setContent.() -> A): A {
//        val c = UnsafeContinuation<A>()
//        f.startCoroutine(this, c)
//        return c.result!!
//    }
}


data class CoroutineCompose<A, B>(
    /**
     * User-defined coroutine name.
     */
    val name: HashMap<A, B>,
    val context: Context,
) : AbstractCoroutineContextElement(Key) {
    /**
     * Key for [CoroutineName] instance in the coroutine context.
     */
    companion object Key : CoroutineContext.Key<CoroutineCompose<*, *>>

    /**
     * Returns a string representation of the object.
     */
    override fun toString(): String = "CoroutineCompose($name)"
}


@Composable
fun AppMain() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainScreen.Main.route
    ) {
        main(navController)
        material(navController)
        navigation(navController)
        text(navController)
        button(navController)
        bottomAppBar(navController)
        animation(navController)
        color(navController)
        theming(navController = navController)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeLandTheme {
//        AppMain()
    }
}