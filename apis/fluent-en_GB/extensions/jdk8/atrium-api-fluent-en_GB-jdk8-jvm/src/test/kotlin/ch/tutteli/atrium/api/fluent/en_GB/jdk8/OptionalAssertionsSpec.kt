package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun0
import ch.tutteli.atrium.specs.integration.OptionalAssertionsSpec
import ch.tutteli.atrium.specs.notImplemented
import java.util.*

class OptionalAssertionsSpec : OptionalAssertionsSpec(
    isEmpty = fun0(Expect<Optional<Any>>::isEmpty)
) {
    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val o1: Expect<Optional<Any>> = notImplemented()
        val o2: Expect<out Optional<Any>> = notImplemented()
        val o3: Expect<out Optional<out Any>> = notImplemented()
        val o1b: Expect<Optional<Any?>> = notImplemented()
        val o2b: Expect<out Optional<Any?>> = notImplemented()
        val o3b: Expect<out Optional<out Any?>> = notImplemented()

        o1.isEmpty()
        o2.isEmpty()
        o3.isEmpty()

        o1b.isEmpty()
        o2b.isEmpty()
        o3b.isEmpty()
    }
}
