package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class ThrowableStackTest {

    @Test
    fun illegalStateException() {
        val stack = IllegalStateException("test").stackBacktrace
        expect(stack.first()) toStartWith "${ThrowableStackTest::class.simpleName}.illegalStateException"
        expect(stack) {
            toHaveElementsAndNone {
                this toContain "init"
            }
            toHaveElementsAndAny {
                this toContain "mocha"
            }
        }
    }

    @Test
    fun assertionError() {
        val stack = AssertionError("test").stackBacktrace
        println("heeeere:\n${stack.joinToString("\n")}\n\n")
        expect(stack.first()) toStartWith "${ThrowableStackTest::class.simpleName}.assertionError"
        expect(stack) {
            toHaveElementsAndNone {
                this toContain "init"
            }
            toHaveElementsAndAny {
                this toContain "mocha"
            }
        }
    }
}
