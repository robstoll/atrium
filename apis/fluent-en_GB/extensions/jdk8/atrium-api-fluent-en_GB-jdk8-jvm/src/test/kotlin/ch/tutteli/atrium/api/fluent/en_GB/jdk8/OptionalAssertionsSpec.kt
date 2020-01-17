package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.OptionalAssertionsSpec
import java.util.*

class OptionalAssertionsSpec : OptionalAssertionsSpec(
    isEmpty = fun0(Expect<Optional<Int>>::isEmpty),
    isPresentFeature = feature0<Optional<Int>, Int>(Expect<Optional<Int>>::isPresent),
    isPresent = fun1<Optional<Int>, Expect<Int>.() -> Unit>(Expect<Optional<Int>>::isPresent)
) {
    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var o1: Expect<Optional<Any>> = notImplemented()
        var o1b: Expect<Optional<Any?>> = notImplemented()

        var star: Expect<Optional<*>> = notImplemented()

        o1 = o1.isEmpty()
        o1b = o1b.isEmpty()
        star = star.isEmpty()
    }
}
