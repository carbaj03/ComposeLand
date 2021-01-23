package com.acv.composeland.suspend

import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.RestrictsSuspension
import kotlin.coroutines.coroutineContext

//@Suppress("ClassName")
//object compose {
//    private val s = object : ComposeEffect<Int> {
//        override val scope: CoroutineContext =
//            CoroutineCompose(mutableListOf(), "")
//
//        override val composer: CoroutineCompose<String>
//            get() = scope as CoroutineCompose<String>
//    }
//
//    operator fun <A> invoke(func: ComposeEffect<*>.() -> A): A =
//        func(s)
//}

@RestrictsSuspension
interface ComposeEffect<B> {
    val scope: CoroutineContext
    val composer: CoroutineCompose<String>
//    operator fun B.invoke(): B
}



suspend fun composer(): CoroutineCompose<String> =
    coroutineContext[CoroutineCompose] as CoroutineCompose<String>
