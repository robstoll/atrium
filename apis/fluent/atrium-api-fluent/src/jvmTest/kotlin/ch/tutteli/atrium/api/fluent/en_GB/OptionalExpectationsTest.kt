package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import java.util.*
import kotlin.test.Test

class OptionalExpectationsTest : ch.tutteli.atrium.specs.integration.AbstractOptionalExpectationsTest(
    fun0(Expect<Optional<Int>>::toBeEmpty),
    feature0<Optional<Int>, Int>(Expect<Optional<Int>>::toBePresent),
    fun1<Optional<Int>, Expect<Int>.() -> Unit>(Expect<Optional<Int>>::toBePresent)
) {

    @Suppress("unused", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var o1: Expect<Optional<Any>> = expect(Optional.empty())
        var o1b: Expect<Optional<Any?>> = expect(Optional.empty())

        var star: Expect<Optional<*>> = expect(Optional.empty<Any>())

        o1 = o1.toBeEmpty()
        o1b = o1b.toBeEmpty()
        star = star.toBeEmpty()

        o1 = expect(Optional.of(1))
        o1.toBePresent()
        // the following is not supported on purpose as we don't know what type of the element is in such a case
        // star.toBePresent()
        o1.toBePresent { toEqual(1) }
        // the following is not supported on purpose as we don't know what type of the element is in such a case
        // star.toBePresent {}
    }
}
