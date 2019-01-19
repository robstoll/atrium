package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.integration.TestData

internal typealias F = Assert<TestData>.() -> Unit

class FeatureAssertionsBoundedReferenceSpec : ch.tutteli.atrium.spec.integration.FeatureAssertionsSpec(
    AssertionVerbFactory,
    propertyImmediate,
    propertyLazy,
    return0ValueImmediate,
    return1ValueImmediate,
    return2ValueImmediate,
    return3ValueImmediate,
    return4ValueImmediate,
    return5ValueImmediate,
    return0ValueLazy,
    return1ValueLazy,
    return2ValueLazy,
    return3ValueLazy,
    return4ValueLazy,
    return5ValueLazy,

    propertyNullableDoesNotHold,
    return0ValueNullableDoesNotHold,
    return1ValueNullableDoesNotHold,
    return2ValueNullableDoesNotHold,
    return3ValueNullableDoesNotHold,
    return4ValueNullableDoesNotHold,
    return5ValueNullableDoesNotHold,

    propertyNullableHolds,
    return0ValueNullableHolds,
    return1ValueNullableHolds,
    return2ValueNullableHolds,
    return3ValueNullableHolds,
    return4ValueNullableHolds,
    return5ValueNullableHolds,

    propertyLazyWithNestedImmediate,
    propertyLazyWithNestedLazy
) {

    companion object {
        val propertyImmediate: F = { property(subject::description) contains "hello" }
        val propertyLazy: F = { property(subject::description) { o contains "hello" } }
        val return0ValueImmediate: F = { returnValueOf(subject::return0) contains Values("hello") }
        val return1ValueImmediate: F = { returnValueOf(subject::return1, "a") contains Values("hello") }
        val return2ValueImmediate: F = { returnValueOf(subject::return2, "a", 1) contains Values("hello") }
        val return3ValueImmediate: F = { returnValueOf(subject::return3, "a", 1, true) contains Values("hello") }
        val return4ValueImmediate: F = { returnValueOf(subject::return4, "a", 1, true, 1.2) contains Values("hello") }
        val return5ValueImmediate: F = { returnValueOf(subject::return5, "a", 1, true, 1.2, 'b') contains Values("hello") }
        val return0ValueLazy: F = { returnValueOf(subject::return0) { o contains Values("hello") } }
        val return1ValueLazy: F = { returnValueOf(subject::return1, "a") { o contains Values("hello") } }
        val return2ValueLazy: F = { returnValueOf(subject::return2, "a", 1) { o contains Values("hello") } }
        val return3ValueLazy: F = { returnValueOf(subject::return3, "a", 1, true) { o contains Values("hello") } }
        val return4ValueLazy: F = { returnValueOf(subject::return4, "a", 1, true, 1.2) { o contains Values("hello") } }
        val return5ValueLazy: F = { returnValueOf(subject::return5, "a", 1, true, 1.2, 'b') { o contains Values("hello") } }

        val propertyNullableDoesNotHold: F = { property(subject::nullableValue) toBe null }
        val return0ValueNullableDoesNotHold: F = { returnValueOf(subject::returnNullable0) toBe null }
        val return1ValueNullableDoesNotHold: F = { returnValueOf(subject::returnNullable1, "a") toBe null }
        val return2ValueNullableDoesNotHold: F = { returnValueOf(subject::returnNullable2, "a", 1) toBe null }
        val return3ValueNullableDoesNotHold: F = { returnValueOf(subject::returnNullable3, "a", 1, true) toBe null }
        val return4ValueNullableDoesNotHold: F = { returnValueOf(subject::returnNullable4, "a", 1, true, 1.2) toBe null }
        val return5ValueNullableDoesNotHold: F = { returnValueOf(subject::returnNullable5, "a", 1, true, 1.2, 'b') toBe null }

        val propertyNullableHolds: F = { property(subject::nullableValue) notToBeNull {} }
        val return0ValueNullableHolds: F = { returnValueOf(subject::returnNullable0) notToBeNull {} }
        val return1ValueNullableHolds: F = { returnValueOf(subject::returnNullable1, "a") notToBeNull {} }
        val return2ValueNullableHolds: F = { returnValueOf(subject::returnNullable2, "a", 1) notToBeNull {} }
        val return3ValueNullableHolds: F = { returnValueOf(subject::returnNullable3, "a", 1, true) notToBeNull {} }
        val return4ValueNullableHolds: F = { returnValueOf(subject::returnNullable4, "a", 1, true, 1.2) notToBeNull {} }
        val return5ValueNullableHolds: F = { returnValueOf(subject::returnNullable5, "a", 1, true, 1.2, 'b') notToBeNull {} }

        val propertyLazyWithNestedImmediate: F = {
            property(subject::description) {
                property(subject::length).toBe(12)
            }
        }
        val propertyLazyWithNestedLazy: F = {
            property(subject::description) {
                property(subject::length) { toBe(12) }
            }
        }
    }
}

