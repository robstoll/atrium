// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")

package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.feature
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.migration.asAssert
import ch.tutteli.atrium.domain.builders.migration.asExpect
import ch.tutteli.atrium.spec.integration.TestData

internal typealias F = Assert<TestData>.() -> Unit

//TODO remove with 1.0.0, no need to migrate to Spek 2
class AssertionsBoundedReferenceSpec : ch.tutteli.atrium.spec.integration.AssertionsSpec(
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
        val propertyImmediate: F = { asExpect().feature { f(subject::description) }.asAssert() contains "hello" }
        val propertyLazy: F = { property(subject::description) { o contains "hello" } }
        val return0ValueImmediate: F = { asExpect().feature { f(subject::return0) }.asAssert() contains Values("hello") }
        val return1ValueImmediate: F = { asExpect().feature { f(subject::return1, "a") }.asAssert() contains Values("hello") }
        val return2ValueImmediate: F = { asExpect().feature { f(subject::return2, "a", 1) }.asAssert() contains Values("hello") }
        val return3ValueImmediate: F = { asExpect().feature { f(subject::return3, "a", 1, true) }.asAssert() contains Values("hello") }
        val return4ValueImmediate: F = { asExpect().feature { f(subject::return4, "a", 1, true, 1.2) }.asAssert() contains Values("hello") }
        val return5ValueImmediate: F = { asExpect().feature { f(subject::return5, "a", 1, true, 1.2, 'b') }.asAssert() contains Values("hello") }
        val return0ValueLazy: F = { returnValueOf(subject::return0) { o contains Values("hello") } }
        val return1ValueLazy: F = { returnValueOf(subject::return1, "a") { o contains Values("hello") } }
        val return2ValueLazy: F = { returnValueOf(subject::return2, "a", 1) { o contains Values("hello") } }
        val return3ValueLazy: F = { returnValueOf(subject::return3, "a", 1, true) { o contains Values("hello") } }
        val return4ValueLazy: F = { returnValueOf(subject::return4, "a", 1, true, 1.2) { o contains Values("hello") } }
        val return5ValueLazy: F = { returnValueOf(subject::return5, "a", 1, true, 1.2, 'b') { o contains Values("hello") } }

        val propertyNullableDoesNotHold: F = { asExpect().feature { f(subject::nullableValue) }.asAssert() toBe null }
        val return0ValueNullableDoesNotHold: F = { asExpect().feature { f(subject::returnNullable0) }.asAssert() toBe null }
        val return1ValueNullableDoesNotHold: F = {
            asExpect().feature { f(subject::returnNullable1, "a") }.asAssert() toBe null
        }
        val return2ValueNullableDoesNotHold: F = {
            asExpect().feature { f(subject::returnNullable2, "a", 1) }.asAssert() toBe null
        }
        val return3ValueNullableDoesNotHold: F = {
            asExpect().feature { f(subject::returnNullable3, "a", 1, true) }.asAssert() toBe null
        }
        val return4ValueNullableDoesNotHold: F = {
            asExpect().feature { f(subject::returnNullable4, "a", 1, true, 1.2) }.asAssert() toBe null
        }
        val return5ValueNullableDoesNotHold: F = {
            asExpect().feature { f(subject::returnNullable5, "a", 1, true, 1.2, 'b') }.asAssert() toBe null
        }

        val propertyNullableHolds: F = { asExpect().feature { f(subject::nullableValue) }.asAssert() notToBeNull {} }
        val return0ValueNullableHolds: F = { asExpect().feature { f(subject::returnNullable0) }.asAssert() notToBeNull {} }
        val return1ValueNullableHolds: F = {
            asExpect().feature { f(subject::returnNullable1, "a") }.asAssert() notToBeNull {}
        }
        val return2ValueNullableHolds: F = {
            asExpect().feature { f(subject::returnNullable2, "a", 1) }.asAssert() notToBeNull {}
        }
        val return3ValueNullableHolds: F = {
            asExpect().feature { f(subject::returnNullable3, "a", 1, true) }.asAssert() notToBeNull {}
        }
        val return4ValueNullableHolds: F = {
            asExpect().feature { f(subject::returnNullable4, "a", 1, true, 1.2) }.asAssert() notToBeNull {}
        }
        val return5ValueNullableHolds: F = {
            asExpect().feature { f(subject::returnNullable5, "a", 1, true, 1.2, 'b') }.asAssert() notToBeNull {}
        }

        val propertyLazyWithNestedImmediate: F = {
            property(subject::description) {
                asExpect().feature { f(subject::length) }.asAssert().toBe(12)
            }
        }
        val propertyLazyWithNestedLazy: F = {
            property(subject::description) {
                property(subject::length) { toBe(12) }
            }
        }
    }
}

