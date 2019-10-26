package org.spekframework.spek2

class Calculator {
    val type = "fancy"
    fun add(a: Int, b: Int): Int {
        return a + b
    }

    @Throws(IllegalArgumentException::class)
    fun divide(a: Int, b: Int): Float {
        if (b == 0) {
            throw IllegalArgumentException("division by zero")
        }
        return (a * 1f) / b
    }

    val isFancy: Boolean
        get() = type == "fancy"
}

