package com.acv.composeland.declarative.visible

import kotlinx.coroutines.delay

interface Service {
    suspend fun fetchData(): Array<String>
}

val service = object : Service {
    override suspend fun fetchData(): Array<String> {
        delay(5000)
        return arrayOf("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine")
    }
}