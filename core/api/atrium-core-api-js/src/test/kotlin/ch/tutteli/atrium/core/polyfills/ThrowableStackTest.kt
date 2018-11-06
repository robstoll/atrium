package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.api.cc.infix.en_GB.containsNot
import ch.tutteli.atrium.api.cc.infix.en_GB.startsWith
import ch.tutteli.atrium.verbs.internal.assert
import kotlin.test.Test

class ThrowableStackTest {

    @Test
    fun illegalStateException() {
        val stack = IllegalStateException("test").stack
        assert(stack.first()) startsWith "${ThrowableStackTest::class.simpleName}.illegalStateException"
        assert(stack) containsNot "init"
    }
}
