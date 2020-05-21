package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.api.infix.en_GB.any
import ch.tutteli.atrium.api.infix.en_GB.contains
import ch.tutteli.atrium.api.infix.en_GB.none
import ch.tutteli.atrium.api.infix.en_GB.startsWith
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class ThrowableStackTest {

    @Test
    fun illegalStateException() {
        val stack = IllegalStateException("test").stackBacktrace
        expect(stack.first()) startsWith "${ThrowableStackTest::class.simpleName}.illegalStateException"
        expect(stack) {
            none { this contains "init" }
            any { this contains "mocha" }
        }
    }

    @Test
    fun assertionError() {
        val stack = AssertionError("test").stackBacktrace
        expect(stack.first()) startsWith "${ThrowableStackTest::class.simpleName}.assertionError"
        expect(stack) {
            none { this contains "init" }
            any { this contains "mocha" }
        }
    }
}
