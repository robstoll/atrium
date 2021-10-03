package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.TestData
import ch.tutteli.atrium.specs.notImplemented

internal typealias F = Expect<TestData>.() -> Unit

class FeatureExpectationsBoundedReferenceSpec : ch.tutteli.atrium.specs.integration.FeatureExpectationsSpec(
    propertyImmediate,
    propertyLazy,
    f0Immediate,
    f1Immediate,
    f2Immediate,
    f3Immediate,
    f4Immediate,
    f5Immediate,
    f0Lazy,
    f1Lazy,
    f2Lazy,
    f3Lazy,
    f4Lazy,
    f5Lazy,

    propertyNullableDoesNotHold,
    f0NullableDoesNotHold,
    f1NullableDoesNotHold,
    f2NullableDoesNotHold,
    f3NullableDoesNotHold,
    f4NullableDoesNotHold,
    f5NullableDoesNotHold,

    propertyNullableHolds,
    f0NullableHolds,
    f1NullableHolds,
    f2NullableHolds,
    f3NullableHolds,
    f4NullableHolds,
    f5NullableHolds,

    propertyLazyWithNestedImmediate,
    propertyLazyWithNestedLazy,

    propertyEmptyAssertionCreator,
    f0EmptyAssertionCreator,
    f1EmptyAssertionCreator,
    f2EmptyAssertionCreator,
    f3EmptyAssertionCreator,
    f4EmptyAssertionCreator,
    f5EmptyAssertionCreator,

    isAbleToEvaluateDescription = false
) {

    companion object {
        //@formatter:off
        val propertyImmediate: F = { feature { f(it::nonNullValue) }.toContain("hello" ) }
        val propertyLazy: F = { feature({ f(it::nonNullValue) }) { toContain("hello" ) } }
        val f0Immediate: F = { feature { f(it::return0) }.toContain("hello" ) }
        val f1Immediate: F = { feature { f(it::return1, "a") }.toContain("hello" ) }
        val f2Immediate: F = { feature { f(it::return2, "a", 1) }.toContain("hello" ) }
        val f3Immediate: F = { feature { f(it::return3, "a", 1, true) }.toContain("hello" ) }
        val f4Immediate: F = { feature { f(it::return4, "a", 1, true, 1.2) }.toContain("hello" ) }
        val f5Immediate: F = { feature { f(it::return5, "a", 1, true, 1.2, 'b') }.toContain("hello" ) }
        val f0Lazy: F = { feature({ f(it::return0) }) { toContain("hello" ) } }
        val f1Lazy: F = { feature({ f(it::return1, "a") }) { toContain("hello" ) } }
        val f2Lazy: F = { feature({ f(it::return2, "a", 1) }) { toContain("hello" ) } }
        val f3Lazy: F = { feature({ f(it::return3, "a", 1, true) }) { toContain("hello" ) } }
        val f4Lazy: F = { feature({ f(it::return4, "a", 1, true, 1.2) }) { toContain("hello" ) } }
        val f5Lazy: F = { feature({ f(it::return5, "a", 1, true, 1.2, 'b') }) { toContain("hello" ) } }

        val propertyNullableDoesNotHold: F = { feature { f(it::nullableValue) }.toEqual(null) }
        val f0NullableDoesNotHold: F = { feature { f(it::returnNullable0) }.toEqual(null) }
        val f1NullableDoesNotHold: F = { feature { f(it::returnNullable1, "a") }.toEqual(null) }
        val f2NullableDoesNotHold: F = { feature { f(it::returnNullable2, "a", 1) }.toEqual(null) }
        val f3NullableDoesNotHold: F = { feature { f(it::returnNullable3, "a", 1, true) }.toEqual(null) }
        val f4NullableDoesNotHold: F = { feature { f(it::returnNullable4, "a", 1, true, 1.2) }.toEqual(null) }
        val f5NullableDoesNotHold: F = { feature { f(it::returnNullable5, "a", 1, true, 1.2, 'b') }.toEqual(null) }

        val propertyNullableHolds: F = { feature { f(it::nullableValue) }.notToEqualNull { toEqual(1) } }
        val f0NullableHolds: F = { feature { f(it::returnNullable0) }.notToEqualNull { toEqual(1) } }
        val f1NullableHolds: F = { feature { f(it::returnNullable1, "a") }.notToEqualNull { toEqual(1) } }
        val f2NullableHolds: F = { feature { f(it::returnNullable2, "a", 1) }.notToEqualNull { toEqual(1) } }
        val f3NullableHolds: F = { feature { f(it::returnNullable3, "a", 1, true) }.notToEqualNull { toEqual(1) } }
        val f4NullableHolds: F = { feature { f(it::returnNullable4, "a", 1, true, 1.2) }.notToEqualNull { toEqual(1) } }
        val f5NullableHolds: F = { feature { f(it::returnNullable5, "a", 1, true, 1.2, 'b') }.notToEqualNull { toEqual(1) } }
        //@formatter:on

        val propertyLazyWithNestedImmediate: F = {
            feature({ f(it::nonNullValue) }) {
                feature { f(it::length) }.toEqual(12)
            }
        }
        val propertyLazyWithNestedLazy: F = {
            feature({ f(it::nonNullValue) }) {
                feature({ f(it::length) }) { toEqual(12) }
            }
        }

        val propertyEmptyAssertionCreator: F = { feature({ f(it::nonNullValue) }) {} }
        val f0EmptyAssertionCreator: F = { feature({ f(it::return0) }) {} }
        val f1EmptyAssertionCreator: F = { feature({ f(it::return1, "a") }) {} }
        val f2EmptyAssertionCreator: F = { feature({ f(it::return2, "a", 1) }) {} }
        val f3EmptyAssertionCreator: F = { feature({ f(it::return3, "a", 1, true) }) {} }
        val f4EmptyAssertionCreator: F = { feature({ f(it::return4, "a", 1, true, 1.2) }) {} }
        val f5EmptyAssertionCreator: F = { feature({ f(it::return5, "a", 1, true, 1.2, 'b') }) {} }
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val a1: Expect<Collection<Int>> = notImplemented()
        val a1b: Expect<Collection<Int?>> = notImplemented()

        val star: Expect<Collection<*>> = notImplemented()

        a1.feature { f(it::size) }
        a1.feature({ f(it::size) }) {}

        a1b.feature { f(it::size) }
        a1b.feature({ f(it::size) }) {}

        star.feature { f(it::size) }
        star.feature({ f(it::size) }) {}
    }
}

