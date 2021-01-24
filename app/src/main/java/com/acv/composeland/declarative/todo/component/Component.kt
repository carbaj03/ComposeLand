package com.acv.composeland.declarative.todo.component

import com.acv.composeland.declarative.Node

//Each time that i want to add a new component i need to define his children
interface Component {
    val children: List<Node>
}