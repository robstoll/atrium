package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.domain.builders.utils.subAssert
import ch.tutteli.atrium.spec.integration.TestData

class FeatureAssertionsClassReferenceSpec : ch.tutteli.atrium.spec.integration.FeatureAssertionsSpec(
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
        val propertyImmediate: F = { property(TestData::description).contains("hello") }
        val propertyLazy: F = { property(TestData::description) { contains("hello") } }
        val return0ValueImmediate: F = { returnValueOf(TestData::return0).contains("hello") }
        val return1ValueImmediate: F = { returnValueOf(TestData::return1, "a").contains("hello") }
        val return2ValueImmediate: F = { returnValueOf(TestData::return2, "a", 1).contains("hello") }
        val return3ValueImmediate: F = { returnValueOf(TestData::return3, "a", 1, true).contains("hello") }
        val return4ValueImmediate: F = { returnValueOf(TestData::return4, "a", 1, true, 1.2).contains("hello") }
        val return5ValueImmediate: F = { returnValueOf(TestData::return5, "a", 1, true, 1.2, 'b').contains("hello") }
        //TODO remove subAssert once https://youtrack.jetbrains.com/issue/KT-24230 is fixed
        val return0ValueLazy: F = { returnValueOf(TestData::return0, subAssert { contains("hello") }) }
        val return1ValueLazy: F = { returnValueOf(TestData::return1, "a") { contains("hello") } }
        val return2ValueLazy: F = { returnValueOf(TestData::return2, "a", 1) { contains("hello") } }
        val return3ValueLazy: F = { returnValueOf(TestData::return3, "a", 1, true) { contains("hello") } }
        val return4ValueLazy: F = { returnValueOf(TestData::return4, "a", 1, true, 1.2) { contains("hello") } }
        val return5ValueLazy: F = { returnValueOf(TestData::return5, "a", 1, true, 1.2, 'b') { contains("hello") } }

        val propertyNullableDoesNotHold: F = { property(TestData::nullableValue).toBe(null) }
        val return0ValueNullableDoesNotHold: F = { returnValueOf(TestData::returnNullable0).toBe(null) }
        val return1ValueNullableDoesNotHold: F = { returnValueOf(TestData::returnNullable1, "a").toBe(null) }
        val return2ValueNullableDoesNotHold: F = { returnValueOf(TestData::returnNullable2, "a", 1).toBe(null) }
        val return3ValueNullableDoesNotHold: F = { returnValueOf(TestData::returnNullable3, "a", 1, true).toBe(null) }
        val return4ValueNullableDoesNotHold: F = { returnValueOf(TestData::returnNullable4, "a", 1, true, 1.2).toBe(null) }
        val return5ValueNullableDoesNotHold: F = { returnValueOf(TestData::returnNullable5, "a", 1, true, 1.2, 'b').toBe(null) }

        val propertyNullableHolds: F = { property(TestData::nullableValue).notToBeNull {} }
        val return0ValueNullableHolds: F = { returnValueOf(TestData::returnNullable0).notToBeNull {} }
        val return1ValueNullableHolds: F = { returnValueOf(TestData::returnNullable1, "a").notToBeNull {} }
        val return2ValueNullableHolds: F = { returnValueOf(TestData::returnNullable2, "a", 1).notToBeNull {} }
        val return3ValueNullableHolds: F = { returnValueOf(TestData::returnNullable3, "a", 1, true).notToBeNull {} }
        val return4ValueNullableHolds: F = { returnValueOf(TestData::returnNullable4, "a", 1, true, 1.2).notToBeNull {} }
        val return5ValueNullableHolds: F = { returnValueOf(TestData::returnNullable5, "a", 1, true, 1.2, 'b').notToBeNull {} }


        val propertyLazyWithNestedImmediate: F = {
            property(TestData::description) {
                property(String::length).toBe(12)
            }
        }
        val propertyLazyWithNestedLazy: F = {
            property(TestData::description) {
                property(String::length) { toBe(12) }
            }
        }
    }
}

