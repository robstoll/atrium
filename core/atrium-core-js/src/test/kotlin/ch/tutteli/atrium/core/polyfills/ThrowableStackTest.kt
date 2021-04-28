package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import kotlin.test.Test

class ThrowableStackTest {

    @Test
    fun illegalStateException() {
        val stack = IllegalStateException("test").stackBacktrace
        expect(stack.first()) toStartWith "${ThrowableStackTest::class.simpleName}.illegalStateException"
        expect(stack) {
            toHaveNextAndNone {
                this toContain "init"
            }
            toHaveNextAndAny {
                this toContain "mocha"
            }
        }
    }

    @Test
    fun assertionError() {
        val stack = AssertionError("test").stackBacktrace
        expect(stack.first()) toStartWith "${ThrowableStackTest::class.simpleName}.assertionError"
        expect(stack) {
            toHaveNextAndNone {
                this toContain "init"
            }
            toHaveNextAndAny {
                this toContain "mocha"
            }
        }
    }
}
