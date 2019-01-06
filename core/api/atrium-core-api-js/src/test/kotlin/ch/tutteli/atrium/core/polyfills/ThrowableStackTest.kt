package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.api.cc.infix.en_GB.any
import ch.tutteli.atrium.api.cc.infix.en_GB.contains
import ch.tutteli.atrium.api.cc.infix.en_GB.none
import ch.tutteli.atrium.api.cc.infix.en_GB.startsWith
import ch.tutteli.atrium.verbs.internal.assert
import kotlin.test.Test

class ThrowableStackTest {

    @Test
    fun illegalStateException() {
        val stack = IllegalStateException("test").stackBacktrace
        assert(stack.first()) startsWith "${ThrowableStackTest::class.simpleName}.illegalStateException"
        assert(stack) {
            none { this contains "init" }
            any { this contains "mocha" }
        }
    }

    @Test
    fun assertionError() {
        val stack = AssertionError("test").stackBacktrace
        assert(stack.first()) startsWith "${ThrowableStackTest::class.simpleName}.assertionError"
        assert(stack) {
            none { this contains "init" }
            any { this contains "mocha" }
        }
    }
}
