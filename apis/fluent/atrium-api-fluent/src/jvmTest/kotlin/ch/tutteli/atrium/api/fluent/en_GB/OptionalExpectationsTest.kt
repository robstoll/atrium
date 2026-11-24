package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.feature0
import ch.tutteli.atrium.specs.fun0
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.integration.AbstractOptionalExpectationsTest
import java.util.*
import kotlin.test.Test

class OptionalExpectationsTest : AbstractOptionalExpectationsTest(
    fun0(Expect<Optional<Int>>::toBeEmpty),
    feature0(Expect<Optional<Int>>::toBePresent),
    fun1(Expect<Optional<Int>>::toBePresent)
) {

    @Suppress("AssignedValueIsNeverRead", "UNUSED_VARIABLE", "UNUSED_VALUE", "unused")
    @Test
    fun ambiguityTest() {
        var o1: Expect<Optional<Int>> = expect(Optional.empty())
        var o1b: Expect<Optional<Any>> = expect(Optional.empty())

        var star: Expect<Optional<*>> = expect(Optional.empty<Any>())

        o1 = o1.toBeEmpty()
        o1b = o1b.toBeEmpty()
        star = star.toBeEmpty()

        o1 = expect(Optional.of(1))
        val i1: Expect<Int> = o1.toBePresent()
        // the following is not supported on purpose as we don't know what type of the element is in such a case
        // star.toBePresent()
        o1 = o1.toBePresent { toEqual(1) }
        // the following is not supported on purpose as we don't know what type of the element is in such a case
        // star.toBePresent {}
    }
}
