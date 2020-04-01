package ch.tutteli.atrium.api.infix.en_GB.jdk8

import ch.tutteli.atrium.api.infix.en_GB.Empty
import ch.tutteli.atrium.api.infix.en_GB.Present
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.feature0
import ch.tutteli.atrium.specs.fun0
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.integration.OptionalAssertionsSpec
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.testutils.WithAsciiReporter
import java.util.*

class OptionalAssertionsSpec : OptionalAssertionsSpec(
    fun0(Companion::toBeEmpty),
    feature0(Companion::toBePresentFeature),
    fun1(Companion::toBePresent)
) {
    companion object : WithAsciiReporter() {
        private fun toBeEmpty(expect: Expect<Optional<Int>>) = expect toBe Empty
        private fun toBePresent(expect: Expect<Optional<Int>>, assertionCreator: Expect<Int>.() -> Unit) =
            expect toBe present(assertionCreator)
        private fun toBePresentFeature(expect: Expect<Optional<Int>>) = expect toBe Present
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var o1: Expect<Optional<Any>> = notImplemented()
        var o1b: Expect<Optional<Any?>> = notImplemented()

        var star: Expect<Optional<*>> = notImplemented()

        o1 = o1 toBe Empty
        o1b = o1b toBe Empty
        star = star toBe Empty
        o1 toBe Present
        o1b toBe Present
        o1 toBe present {}
        o1b toBe present {}
    }
}
