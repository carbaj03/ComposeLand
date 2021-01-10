package com.acv.composeland.ui.arrow

import arrow.continuations.Effect
import arrow.core.identity
import kotlinx.coroutines.flow.*


sealed class Either<out E, out A>
data class Left<out E>(val e: E) : Either<E, Nothing>()
data class Right<out A>(val a: A) : Either<Nothing, A>()

inline fun <E, A, B> Either<E, A>.fold(ifLeft: (E) -> B, ifRight: (A) -> B): B =
    when (this) {
        is Left -> ifLeft(e)
        is Right -> ifRight(a)
    }


//Either effect
fun interface EitherEffect<E, A> : Effect<Either<E, A>> {
    suspend operator fun <B> Either<E, B>.invoke(): B =
        fold({ e -> control().shift(Left(e)) }, ::identity)

    suspend operator fun <B> B?.invoke(e: E): B =
        this ?: control().shift(Left(e))
}

suspend fun <E, A> either(f: suspend EitherEffect<E, *>.() -> A): Either<E, A> =
    Effect.suspended(eff = { EitherEffect { it } }, f = f, just = { Right(it) })


//Nullable Effect
fun interface NullableEffect<A> : Effect<A?> {
    suspend operator fun <B> B?.invoke(): B =
        this ?: control().shift(null)

    suspend operator fun <E, B> Either<E, B>.invoke(): B =
        fold({  control().shift(null) }, ::identity)
}


suspend fun <A> nullable(f: suspend NullableEffect<*>.() -> A?): A? =
    Effect.suspended(eff = { NullableEffect { it } }, f = f, just = { it })

//Arrow effect
//suspend fun <E, A> arrow(f: suspend ArrowEffect<E, *>.() -> A): Either<E, A> =
//    Effect.suspended(eff = { ArrowEffect { it } }, f = f, just = { Right(it) })
//
//fun interface ArrowEffect<E, A> : Effect<Either<E, A>> {
//
//    suspend operator fun <B> B?.invoke(e: E): B =
//        this ?: control().shift(Left(e))
//
//    suspend operator fun <B> Either<E, B>.invoke(): B =
//        fold({ e -> control().shift(Left(e)) }, ::identity)
//}


suspend fun main() {
    val r1: Either<String, Int> = Right(1)
    val r2: Either<String, Int> = Right(1)
    val r3: Either<String, Int> = Right(1)
    val r4: Int? = null

    val composition: Either<String, Int> = either {
        val a = r1()
        val b = r2()
        val c = r3()
        val d = r4("fail")

        a + b + c + d
    }

    val composition2 = nullable {
        val a = r1()
        val b = r2()
        val c = r3()
        val d = r4()

        a + b + c + d
    }


    println(composition)
    println(composition2)
}