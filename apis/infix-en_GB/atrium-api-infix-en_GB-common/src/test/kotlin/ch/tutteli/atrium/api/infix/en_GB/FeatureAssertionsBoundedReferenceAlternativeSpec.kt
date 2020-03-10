package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.workaround.it
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.TestData
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.testutils.WithAsciiReporter

class FeatureAssertionsBoundedReferenceAlternativeSpec : ch.tutteli.atrium.specs.integration.FeatureAssertionsSpec(
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
    companion object : WithAsciiReporter() {
        //@formatter:off
        val propertyImmediate: F = { o feature { p(it::nonNullValue) } contains "hello" }
        val propertyLazy: F = { o feature of<TestData, String>({ p(it::nonNullValue) }) { o contains "hello" } }
        val f0Immediate: F = { o feature { f0(it::return0) } contains "hello" }
        val f1Immediate: F = { o feature { f1(it::return1, "a") } contains "hello" }
        val f2Immediate: F = { o feature { f2(it::return2, "a", 1) } contains "hello" }
        val f3Immediate: F = { o feature { f3(it::return3, "a", 1, true) } contains "hello" }
        val f4Immediate: F = { o feature { f4(it::return4, "a", 1, true, 1.2) } contains "hello" }
        val f5Immediate: F = { o feature { f5(it::return5, "a", 1, true, 1.2, 'b') } contains "hello" }
        val f0Lazy: F = { o feature of<TestData, String>({ f0(it::return0) }) {  o contains "hello" } }
        val f1Lazy: F = { o feature of<TestData, String>({ f1(it::return1, "a") }) {  o contains "hello" } }
        val f2Lazy: F = { o feature of<TestData, String>({ f2(it::return2, "a", 1) }) {  o contains "hello" } }
        val f3Lazy: F = { o feature of<TestData, String>({ f3(it::return3, "a", 1, true) }) {  o contains "hello" } }
        val f4Lazy: F = { o feature of<TestData, String>({ f4(it::return4, "a", 1, true, 1.2) }) {  o contains "hello" } }
        val f5Lazy: F = { o feature of<TestData, String>({ f5(it::return5, "a", 1, true, 1.2, 'b') }) {  o contains "hello" } }

        val propertyNullableDoesNotHold: F = { o feature { p(it::nullableValue) } toBe null }
        val f0NullableDoesNotHold: F = { o feature { f0(it::returnNullable0) } toBe null }
        val f1NullableDoesNotHold: F = { o feature { f1(it::returnNullable1, "a") } toBe null }
        val f2NullableDoesNotHold: F = { o feature { f2(it::returnNullable2, "a", 1) } toBe null }
        val f3NullableDoesNotHold: F = { o feature { f3(it::returnNullable3, "a", 1, true) } toBe null }
        val f4NullableDoesNotHold: F = { o feature { f4(it::returnNullable4, "a", 1, true, 1.2) } toBe null }
        val f5NullableDoesNotHold: F = { o feature { f5(it::returnNullable5, "a", 1, true, 1.2, 'b') } toBe null }

        val propertyNullableHolds: F = { o feature { p(it::nullableValue) } notToBeNull { o toBe 1 } }
        val f0NullableHolds: F = { o feature { f0(it::returnNullable0) } notToBeNull { o toBe 1 } }
        val f1NullableHolds: F = { o feature { f1(it::returnNullable1, "a") } notToBeNull { o toBe 1 } }
        val f2NullableHolds: F = { o feature { f2(it::returnNullable2, "a", 1) } notToBeNull { o toBe 1 } }
        val f3NullableHolds: F = { o feature { f3(it::returnNullable3, "a", 1, true) } notToBeNull { o toBe 1 } }
        val f4NullableHolds: F = { o feature { f4(it::returnNullable4, "a", 1, true, 1.2) } notToBeNull { o toBe 1 } }
        val f5NullableHolds: F = { o feature { f5(it::returnNullable5, "a", 1, true, 1.2, 'b') } notToBeNull { o toBe 1 } }
        //@formatter:on

        val propertyLazyWithNestedImmediate: F = {
            o feature { p(it::nonNullValue) } it {
                o feature { p(it::length) } toBe 12
            }
        }
        val propertyLazyWithNestedLazy: F = {
            o feature { p(it::nonNullValue) } it {
                o feature { p(it::length) } it { o toBe 12 }
            }
        }

        val propertyEmptyAssertionCreator: F = { o feature of<TestData, String>({ p(it::nonNullValue) }) {} }
        val f0EmptyAssertionCreator: F = { o feature of<TestData, String>({ f0(it::return0) }) {} }
        val f1EmptyAssertionCreator: F = { o feature of<TestData, String>({ f1(it::return1, "a") }) {} }
        val f2EmptyAssertionCreator: F = { o feature of<TestData, String>({ f2(it::return2, "a", 1) }) {} }
        val f3EmptyAssertionCreator: F = { o feature of<TestData, String>({ f3(it::return3, "a", 1, true) }) {} }
        val f4EmptyAssertionCreator: F = { o feature of<TestData, String>({ f4(it::return4, "a", 1, true, 1.2) }) {} }
        val f5EmptyAssertionCreator: F =
            { o feature (of<TestData, String>({ f5(it::return5, "a", 1, true, 1.2, 'b') }) {}) }
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val a1: Expect<Collection<Int>> = notImplemented()
        val a1b: Expect<Collection<Int?>> = notImplemented()

        val star: Expect<Collection<*>> = notImplemented()

        a1 feature { p(it::size) }
        a1 feature { p(it::size) } it {}

        a1b feature { p(it::size) }
        a1b feature { p(it::size) } it {}

        star feature { p(it::size) }
        star feature { p(it::size) } it {}
    }
}
