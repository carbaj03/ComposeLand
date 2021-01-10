package com.acv.composeland.compose

import arrow.continuations.Effect
import arrow.core.identity
import com.acv.composeland.*

//sealed class Either<out E, out A>
//data class Left<out E>(val e: E) : Either<E, Nothing>()
//data class Right<out A>(val a: A) : Either<Nothing, A>()
interface Compose

//the magic of inline
inline fun <E, A, B> Either<E, A>.fold(ifLeft: (E) -> B, ifRight: (A) -> B): B =
    when (this) {
        is Left -> ifLeft(e)
        is Right -> ifRight(a)
    }

fun interface ÇomposeEffect<E, A> : Effect<Either<E, A>> {
    suspend operator fun <B> Either<E, B>.invoke(): B =
        fold({ e -> control().shift(Left(e)) }, ::identity)
}

suspend fun <E, A> setContent(f : suspend EitherEffect<E, *>.() -> A): Either<E, A> =
    Effect.suspended(eff = { EitherEffect { it } }, f = f, just = { Right(it)})


suspend fun main(){
    val r1 : Either<String, Int> = Right(1)
    val r2 : Either<String, Int> = Right(1)
    val r3 : Either<String, Int> = Right(1)

    val composition : Either<String, Int> = either {
        val a = r1()
        val b = r2()
        val c = r3()
        a+b+c
    }

    println(composition)
}