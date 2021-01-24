package com.acv.composeland.declarative.todo.composer

import com.acv.composeland.declarative.todo.loading.Node

interface Composer {
    val children: MutableList<Node>
}