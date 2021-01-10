package com.acv.composeland.suspend

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.lifecycleScope
import arrow.Kind
import arrow.core.Either
import arrow.fx.*
import arrow.fx.typeclasses.Disposable
import arrow.typeclasses.Continuation
import kotlinx.coroutines.*
import kotlinx.coroutines.launch
import kotlin.coroutines.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            this.coroutineContext[kotlinx.coroutines.CoroutineName]

        }
//        setContent {
//
//        }
//
//        IO.unsafeCancellableRun()
    }

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


@Suppress(
    "UNCHECKED_CAST",
    "NOTHING_TO_INLINE"
)
inline fun IO.Companion.unsafeCancellableRun(): IOUnsafeCancellableRun =
    unsafeCancellableRun_singleton

/**
 * cached extension
 */
@PublishedApi()
internal val unsafeCancellableRun_singleton: IOUnsafeCancellableRun = object :
    IOUnsafeCancellableRun {}

suspend fun <A> unsafe.runNonBlockingCancellable(
    onCancel: OnCancel,
    fa: Function0<Kind<ForIO, A>>,
    cb: Function1<Either<Throwable, A>, Unit>
): Function0<Unit> =
    IO.unsafeCancellableRun().run {
        this@runNonBlockingCancellable.runNonBlockingCancellable(onCancel, fa, cb)
    }

interface IOUnsafeCancellableRun : UnsafeCancellableRun<ForIO> {
    override suspend fun <A> runBlocking(fa: () -> Kind<ForIO, A>): A = fa().fix().unsafeRunSync()

    override suspend fun <A> runNonBlocking(fa: () -> Kind<ForIO, A>, cb: (Either<Throwable, A>) -> Unit) =
        fa().fix().unsafeRunAsync(cb)

    override suspend fun <A> runNonBlockingCancellable(onCancel: OnCancel, fa: () -> Kind<ForIO, A>, cb: (Either<Throwable, A>) -> Unit): Disposable =
        fa().fix().unsafeRunAsyncCancellable(onCancel, cb)
}


@RestrictsSuspension
object unsafe {

    operator fun <A> invoke(f: suspend unsafe.() -> A): A {
        val c = UnsafeContinuation<A>()
        f.startCoroutine(this, c)
        return c.result!!
    }
}

private class UnsafeContinuation<A> : Continuation<A> {
    var result: A? = null

    override fun resume(value: A) {
        result = value
    }

    override fun resumeWithException(exception: Throwable) {
        throw exception
    }

    override val context: CoroutineContext = EmptyCoroutineContext
}

@Deprecated(IODeprecation)
interface UnsafeCancellableRun<F> : UnsafeRun<F> {
    suspend fun <A> runNonBlockingCancellable(onCancel: OnCancel, fa: () -> Kind<F, A>, cb: (Either<Throwable, A>) -> Unit): Disposable
}

@Deprecated(IODeprecation)
interface UnsafeRun<F> {
    suspend fun <A> runBlocking(fa: () -> Kind<F, A>): A
    suspend fun <A> runNonBlocking(fa: () -> Kind<F, A>, cb: (Either<Throwable, A>) -> Unit): Unit
}
