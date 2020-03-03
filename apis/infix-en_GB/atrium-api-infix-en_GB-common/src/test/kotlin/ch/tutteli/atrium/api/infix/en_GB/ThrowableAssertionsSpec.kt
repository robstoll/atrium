package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.property

class ThrowableAssertionsSpec : ch.tutteli.atrium.specs.integration.ThrowableAssertionsSpec(
    property<Throwable, String>(Expect<Throwable>::message),
    fun1<Throwable, Expect<String>.() -> Unit>(Expect<Throwable>::message),
    fun2(::messageContains)
) {

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<Throwable> = notImplemented()

        a1.message
        a1 = a1 message {}
        a1 = a1 messageContains "a"
        a1 = a1 messageContains 'a'
        a1 = a1 messageContains Values("a", 1, 'b')
    }
}

private fun messageContains(expect: Expect<Throwable>, expected: Any, vararg otherExpected: Any): Expect<Throwable> =
    if (otherExpected.isEmpty()) expect messageContains expected
    else expect messageContains Values(expected, *otherExpected)
