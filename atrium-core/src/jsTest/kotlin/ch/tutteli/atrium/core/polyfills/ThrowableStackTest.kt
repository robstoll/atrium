package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class ThrowableStackTest {

    @Test
    fun illegalStateException() {
        val stack = IllegalStateException("test").stackBacktrace
        expect(stack) {
            get(0).toStartWith("${ThrowableStackTest::class.simpleName}.illegalStateException")
            notToContain(
                { toContain("init") },
                { toContain("[as constructor]") }
            )

            toHaveElementsAndAny {
                toContain("mocha")
            }
        }
    }

    @Test
    fun assertionError() {
        val stack = AssertionError("test").stackBacktrace
        expect(stack) {
            get(0).toStartWith("${ThrowableStackTest::class.simpleName}.assertionError")
            toHaveElementsAndNone {
                toContain("init")
            }
            toHaveElementsAndAny {
                toContain("mocha")
            }
        }
    }
}
