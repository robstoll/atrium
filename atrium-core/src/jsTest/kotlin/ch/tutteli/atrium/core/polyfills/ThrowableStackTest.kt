package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class ThrowableStackTest {

    @Test
    fun illegalStateException() {
        val stack = IllegalStateException("test").stackBacktrace
        expect(stack) {
            it get 0 toStartWith "${ThrowableStackTest::class.simpleName}.illegalStateException"
            it notToContain o the entries(
                { it toContain "init" },
                { it toContain "[as constructor]" }
            )

            toHaveElementsAndAny {
                this toContain "mocha"
            }
        }
    }

    @Test
    fun assertionError() {
        val stack = AssertionError("test").stackBacktrace
        expect(stack){
            it get(0) toStartWith "${ThrowableStackTest::class.simpleName}.assertionError"
            it toHaveElementsAndNone {
                this toContain "init"
            }
            it toHaveElementsAndAny {
                this toContain "mocha"
            }
        }
    }
}
