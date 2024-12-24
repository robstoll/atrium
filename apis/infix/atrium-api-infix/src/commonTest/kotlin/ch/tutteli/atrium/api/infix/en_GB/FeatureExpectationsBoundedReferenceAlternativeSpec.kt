package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.TestData
import ch.tutteli.atrium.specs.notImplemented

class FeatureExpectationsBoundedReferenceAlternativeSpec : ch.tutteli.atrium.specs.integration.FeatureExpectationsSpec(
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

    //TODO remove type parameters for `of` with Kotiln 1.4 including parentheses (make the calls infix again
    companion object {
        //@formatter:off
        val propertyImmediate: F = { (its feature { p(it::nonNullValue) }).toContain("hello") }
        val propertyLazy: F = { its feature of<TestData, String>({ p(it::nonNullValue) }) { it toContain "hello" } }
        val f0Immediate: F = { (its feature { f0(it::return0) }).toContain("hello") }
        val f1Immediate: F = { (its feature { f1(it::return1, "a") }).toContain("hello") }
        val f2Immediate: F = { (its feature { f2(it::return2, "a", 1) }).toContain("hello") }
        val f3Immediate: F = { (its feature { f3(it::return3, "a", 1, true) }).toContain("hello") }
        val f4Immediate: F = { (its feature { f4(it::return4, "a", 1, true, 1.2) }).toContain("hello") }
        val f5Immediate: F = { (its feature { f5(it::return5, "a", 1, true, 1.2, 'b') }).toContain("hello") }
        val f0Lazy: F = { its feature of<TestData, String>({ f0(it::return0) }) {  it toContain "hello" } }
        val f1Lazy: F = { its feature of<TestData, String>({ f1(it::return1, "a") }) {  it toContain "hello" } }
        val f2Lazy: F = { its feature of<TestData, String>({ f2(it::return2, "a", 1) }) {  it toContain "hello" } }
        val f3Lazy: F = { its feature of<TestData, String>({ f3(it::return3, "a", 1, true) }) {  it toContain "hello" } }
        val f4Lazy: F = { its feature of<TestData, String>({ f4(it::return4, "a", 1, true, 1.2) }) {  it toContain "hello" } }
        val f5Lazy: F = { its feature of<TestData, String>({ f5(it::return5, "a", 1, true, 1.2, 'b') }) {  it toContain "hello" } }

        val propertyNullableDoesNotHold: F = { its feature { p(it::nullableValue) } toEqual null }
        val f0NullableDoesNotHold: F = { its feature { f0(it::returnNullable0) } toEqual null }
        val f1NullableDoesNotHold: F = { its feature { f1(it::returnNullable1, "a") } toEqual null }
        val f2NullableDoesNotHold: F = { its feature { f2(it::returnNullable2, "a", 1) } toEqual null }
        val f3NullableDoesNotHold: F = { its feature { f3(it::returnNullable3, "a", 1, true) } toEqual null }
        val f4NullableDoesNotHold: F = { its feature { f4(it::returnNullable4, "a", 1, true, 1.2) } toEqual null }
        val f5NullableDoesNotHold: F = { its feature { f5(it::returnNullable5, "a", 1, true, 1.2, 'b') } toEqual null }

        val propertyNullableHolds: F = { its feature { p(it::nullableValue) } notToEqualNull { it toEqual 1 } }
        val f0NullableHolds: F = { its feature { f0(it::returnNullable0) } notToEqualNull { it toEqual 1 } }
        val f1NullableHolds: F = { its feature { f1(it::returnNullable1, "a") } notToEqualNull { it toEqual 1 } }
        val f2NullableHolds: F = { its feature { f2(it::returnNullable2, "a", 1) } notToEqualNull { it toEqual 1 } }
        val f3NullableHolds: F = { its feature { f3(it::returnNullable3, "a", 1, true) } notToEqualNull { it toEqual 1 } }
        val f4NullableHolds: F = { its feature { f4(it::returnNullable4, "a", 1, true, 1.2) } notToEqualNull { it toEqual 1 } }
        val f5NullableHolds: F = { its feature { f5(it::returnNullable5, "a", 1, true, 1.2, 'b') } notToEqualNull { it toEqual 1 } }
        //@formatter:on

        val propertyLazyWithNestedImmediate: F = {
            its feature of({ p(it::nonNullValue) }) {
                feature { p(it::length) } toEqual 12
            }
        }
        val propertyLazyWithNestedLazy: F = {
            it feature of({ p(it::nonNullValue) }) {
                it feature of({ p(it::length) }) { this toEqual 12 }
            }
        }

        val propertyEmptyAssertionCreator: F = { its feature of<TestData, String>({ p(it::nonNullValue) }) {} }
        val f0EmptyAssertionCreator: F = { its feature of<TestData, String>({ f0(it::return0) }) {} }
        val f1EmptyAssertionCreator: F = { its feature of<TestData, String>({ f1(it::return1, "a") }) {} }
        val f2EmptyAssertionCreator: F = { its feature of<TestData, String>({ f2(it::return2, "a", 1) }) {} }
        val f3EmptyAssertionCreator: F = { its feature of<TestData, String>({ f3(it::return3, "a", 1, true) }) {} }
        val f4EmptyAssertionCreator: F = { its feature of<TestData, String>({ f4(it::return4, "a", 1, true, 1.2) }) {} }
        val f5EmptyAssertionCreator: F =
            { its feature (of<TestData, String>({ f5(it::return5, "a", 1, true, 1.2, 'b') }) {}) }
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val a1: Expect<Collection<Int>> = notImplemented()
        val a1b: Expect<Collection<Int?>> = notImplemented()

        val star: Expect<Collection<*>> = notImplemented()

        a1 feature { p(it::size) }
        a1 feature of({ p(it::size) }) {}

        a1b feature { p(it::size) }
        a1b feature of({ p(it::size) }) {}

        star feature { p(it::size) }
        star feature of({ p(it::size) }) {}
    }
}
