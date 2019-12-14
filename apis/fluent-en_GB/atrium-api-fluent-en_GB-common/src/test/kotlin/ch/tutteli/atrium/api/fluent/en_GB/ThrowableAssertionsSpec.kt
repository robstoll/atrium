package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.property

class ThrowableAssertionsSpec : ch.tutteli.atrium.specs.integration.ThrowableAssertionsSpec(
    property<Throwable, String>(Expect<Throwable>::message),
    fun1<Throwable, Expect<String>.() -> Unit>(Expect<Throwable>::message),
    fun2(Expect<Throwable>::messageContains)
) {

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<Throwable> = notImplemented()
        var a2: Expect<out Throwable> = notImplemented()

        a1.message
        a2.message

        a1 = a1.message {}
        a2 = a2.message {}

        a1.messageContains("asdf")
        a2.messageContains("asdf")
    }
}
