package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.OptionalExpectationsSpec
import java.util.*

class OptionalExpectationsSpec : OptionalExpectationsSpec(
    fun0(Expect<Optional<Int>>::toBeEmpty),
    feature0<Optional<Int>, Int>(Expect<Optional<Int>>::toBePresent),
    fun1<Optional<Int>, Expect<Int>.() -> Unit>(Expect<Optional<Int>>::toBePresent)
) {
    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var o1: Expect<Optional<Any>> = notImplemented()
        var o1b: Expect<Optional<Any?>> = notImplemented()

        var star: Expect<Optional<*>> = notImplemented()

        o1 = o1.toBeEmpty()
        o1b = o1b.toBeEmpty()
        star = star.toBeEmpty()
        o1.toBePresent()
        o1b.toBePresent()
        // the following is not supported on purpose as we don't know what type of the element is in such a case
        // star.toBePresent()
        o1.toBePresent {}
        o1b.toBePresent {}
        // the following is not supported on purpose as we don't know what type of the element is in such a case
        // star.toBePresent {}
    }
}
