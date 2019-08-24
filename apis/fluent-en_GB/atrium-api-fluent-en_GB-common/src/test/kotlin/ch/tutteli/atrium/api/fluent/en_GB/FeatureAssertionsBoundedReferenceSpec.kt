package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.TestData

internal typealias F = Expect<TestData>.() -> Unit

class FeatureAssertionsBoundedReferenceSpec : ch.tutteli.atrium.specs.integration.FeatureAssertionsSpec(
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
        val propertyImmediate: F = { feature { f(it::nonNullValue) }.contains("hello") }
        val propertyLazy: F = { feature({ f(it::nonNullValue) }) { contains("hello") } }
        val f0Immediate: F = { feature { f(it::return0) }.contains("hello") }
        val f1Immediate: F = { feature { f(it::return1, "a") }.contains("hello") }
        val f2Immediate: F = { feature { f(it::return2, "a", 1) }.contains("hello") }
        val f3Immediate: F = { feature { f(it::return3, "a", 1, true) }.contains("hello") }
        val f4Immediate: F = { feature { f(it::return4, "a", 1, true, 1.2) }.contains("hello") }
        val f5Immediate: F = { feature { f(it::return5, "a", 1, true, 1.2, 'b') }.contains("hello") }
        val f0Lazy: F = { feature({ f(it::return0) }) { contains("hello") } }
        val f1Lazy: F = { feature({ f(it::return1, "a") }) { contains("hello") } }
        val f2Lazy: F = { feature({ f(it::return2, "a", 1) }) { contains("hello") } }
        val f3Lazy: F = { feature({ f(it::return3, "a", 1, true) }) { contains("hello") } }
        val f4Lazy: F = { feature({ f(it::return4, "a", 1, true, 1.2) }) { contains("hello") } }
        val f5Lazy: F = { feature({ f(it::return5, "a", 1, true, 1.2, 'b') }) { contains("hello") } }

        val propertyNullableDoesNotHold: F = { feature { f(it::nullableValue) }.toBe(null) }
        val f0NullableDoesNotHold: F = { feature { f(it::returnNullable0) }.toBe(null) }
        val f1NullableDoesNotHold: F = { feature { f(it::returnNullable1, "a") }.toBe(null) }
        val f2NullableDoesNotHold: F = { feature { f(it::returnNullable2, "a", 1) }.toBe(null) }
        val f3NullableDoesNotHold: F = { feature { f(it::returnNullable3, "a", 1, true) }.toBe(null) }
        val f4NullableDoesNotHold: F = { feature { f(it::returnNullable4, "a", 1, true, 1.2) }.toBe(null) }
        val f5NullableDoesNotHold: F = { feature { f(it::returnNullable5, "a", 1, true, 1.2, 'b') }.toBe(null) }

        val propertyNullableHolds: F = { feature { f(it::nullableValue) }.notToBeNull { toBe(1) } }
        val f0NullableHolds: F = { feature { f(it::returnNullable0) }.notToBeNull { toBe(1) } }
        val f1NullableHolds: F = { feature { f(it::returnNullable1, "a") }.notToBeNull { toBe(1) } }
        val f2NullableHolds: F = { feature { f(it::returnNullable2, "a", 1) }.notToBeNull { toBe(1) } }
        val f3NullableHolds: F = { feature { f(it::returnNullable3, "a", 1, true) }.notToBeNull { toBe(1) } }
        val f4NullableHolds: F = { feature { f(it::returnNullable4, "a", 1, true, 1.2) }.notToBeNull { toBe(1) } }
        val f5NullableHolds: F = { feature { f(it::returnNullable5, "a", 1, true, 1.2, 'b') }.notToBeNull { toBe(1) } }
        //@formatter:on

        val propertyLazyWithNestedImmediate: F = {
            feature({ f(it::nonNullValue) }) {
                feature { f(it::length) }.toBe(12)
            }
        }
        val propertyLazyWithNestedLazy: F = {
            feature({ f(it::nonNullValue) }) {
                feature({ f(it::length) }) { toBe(12) }
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
}

