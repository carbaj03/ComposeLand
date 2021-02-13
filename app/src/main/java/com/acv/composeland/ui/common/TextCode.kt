package com.acv.composeland.ui.common

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp

@Composable
fun TextCode(code: AnnotatedString) {
    val scrollState = rememberScrollState()
    Box(
        Modifier
            .fillMaxWidth()
            .background(Color.DarkGray, RoundedCornerShape(4.dp))
    ) {
        Text(
            modifier = Modifier
                .horizontalScroll(scrollState)
                .widthIn(200.dp, 800.dp)
                .padding(8.dp),
            text = code,
            color = Color.White,
        )
    }

}

@Composable
fun TextCode(code: String) {
    Text(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.LightGray)
            .padding(8.dp)
            .fillMaxWidth(),
        text = code,
        color = Color.Black
    )
}

fun AnnotatedString.Builder.replaceCode(code: String) {
    val t = code.replaceIndent()
    t.splitToSequence("\n").forEach {
        Log.e("sadf", it)
        it.split(" ").forEach {
            Log.e("sadf", it)
            when (it) {
                "@Composable" -> pushStyle(SpanStyle(color = Color.Yellow))
                "fun" -> pushStyle(SpanStyle(color = Color.Gray))
                else -> pushStyle(SpanStyle(color = Color.Red))
            }

            append("$it")
            append(" ")
        }

        append("\n")
    }

}

interface CodeBuilder {
    var l: MutableList<Code>

    operator fun invoke(): AnnotatedString

    fun varString(name: String) {
        variable(name, "String")
    }

    fun variable(name: String, type: String) {
        l.add(Var(name = name, type = type))
    }

    fun annotation(name: String) {
        l.add(Annotation(name = name))
    }

    fun function(name: String, body: CodeBuilder.() -> Unit) {
        l.add(Fun(name = name, argument = listOf()))
        l.add(Indent)
        body()
        l.add(EndIndent)
        l.add(EndFun)
    }

    fun `class`(name: String, vararg argument: Argument) {
        l.add(Class(name = name, argument = argument.toList()))
    }
}

object codeBui {
    inline operator fun invoke(crossinline f: CodeBuilder.() -> Unit): AnnotatedString =
        object : CodeBuilder {
            override var l: MutableList<Code> = mutableListOf()

            override fun invoke(): AnnotatedString {
                f(this)
                return buildAnnotatedString { evaluateCode(l) }
            }
        }()
}


sealed class Code

object Indent : Code()
object EndIndent : Code()

object Keyword : Code()
data class Var(
    val name: String,
    val type: String,
) : Code()

data class Val(
    val name: String,
    val type: String,
) : Code()

data class Annotation(
    val name: String
) : Code()

data class Param(
    val name: String,
    val type: String,
    val default: String,
) : Code()

data class Fun(
    val name: String,
    val argument: List<Param>,
) : Code()

object EndFun : Code()

data class Argument(
    val name: String,
    val value: String,
) : Code()

data class Class(
    val name: String,
    val argument: List<Argument>,
) : Code()

object EndClass : Code()

//fun code(): AnnotatedString {
//    val annotation = Annotation("Composable")
//    val f = Fun(
//        name = "TextColor",
//        argument = listOf(),
////        body = "",
//    )
//    val c = Class(
//        name = "Text",
//        argument = listOf(
//            Argument(
//                name = "text",
//                value = "code",
//            )
//        ),
//    )
//
//    return annotatedString {
//        evaluateCode(
//            listOf<Code>(
//                annotation,
//                f,
//                c,
//                EndFun,
//            )
//        )
//    }
//
//}

fun AnnotatedString.Builder.evaluateCode(it: List<Code>): Unit {
    var indentation = ""
    it.forEach {
        when (it) {
            is Annotation -> {
                pushStyle(SpanStyle(color = Color.Annotation))
                append("@${it.name}")
                pop()
            }
            is Fun -> {
                pushStyle(SpanStyle(color = Color.Keyword))
                append(indentation)
                append("fun ")
                pop()
                pushStyle(SpanStyle(color = Color.FuntionDecalration))
                append("${it.name}")
                pop()
                append("(){")
            }
            is Class -> {
                append(indentation)
                append("${it.name}(")
                if (it.argument.isNotEmpty()) {
                    append("\n")
                    it.argument.forEach {
                        indentation = indentation.plus("    ")
                        append(indentation)
                        pushStyle(SpanStyle(color = Color.NamedArgument))
                        append("${it.name}")
                        pop()
                        append(" = ")
                        pushStyle(SpanStyle(color = Color.White))
                        append("${it.value}")
                        pop()
                        append(",")
                        append("\n")
                        indentation = indentation.removeRange(0..3)
                    }
                    append(indentation)
                }
                append(")")
            }
            is Param -> {
                append("${it.name} = ${it.type}")
            }
            is Argument -> {
                append("${it.name} = ${it.value}")
            }
            is Var -> {
                append(indentation)
                pushStyle(SpanStyle(color = Color.Var))
                append("var")
                pop()
                append(" ${it.name}")
                append(" : ${it.type}")
            }
            is EndFun -> append("}")
            is EndClass -> append(")")
            is Indent -> {
                indentation = indentation.plus("    ")
            }
            is EndIndent -> {
                indentation = indentation.removeRange(0..3)
            }
        }
        if (it !is Indent && it !is EndIndent)
            append("\n")
    }
}


val Color.Companion.Orange get() = Color(0xFFFFA500)
val Color.Companion.Annotation get() = Color(0xFFBBB529)
val Color.Companion.Property get() = Color(0xFFBBB529)
val Color.Companion.Modifier get() = Color(0xFFBBB529)
val Color.Companion.FuntionDecalration get() = Color(0xFFFFC66D)
val Color.Companion.Keyword get() = Color(0xFFCC7832)
val Color.Companion.Var get() = Color(0xFFCC7832)
val Color.Companion.Val get() = Color(0xFFCC7832)
val Color.Companion.NamedArgument get() = Color(0xFF467CDA)