// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")

package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.feature
import ch.tutteli.atrium.api.infix.en_GB.of
import ch.tutteli.atrium.domain.builders.migration.asAssert
import ch.tutteli.atrium.domain.builders.migration.asExpect
import ch.tutteli.atrium.domain.builders.utils.subAssert
import ch.tutteli.atrium.spec.integration.TestData
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory

//TODO remove with 1.0.0, no need to migrate to Spek 2
class AssertionsClassReferenceSpec : ch.tutteli.atrium.spec.integration.AssertionsSpec(
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
        val propertyImmediate: F = { asExpect().feature(TestData::description).asAssert() contains "hello" }
        val propertyLazy: F = {
            asExpect().feature(of(TestData::description, { asAssert { o contains "hello" } })).asAssert()
        }
        val return0ValueImmediate: F = { asExpect().feature(TestData::return0).asAssert() contains Values("hello") }
        val return1ValueImmediate: F = { asExpect().feature(of(TestData::return1, "a")).asAssert() contains Values("hello") }
        val return2ValueImmediate: F = { asExpect().feature(of(TestData::return2, "a", 1)).asAssert() contains Values("hello") }
        val return3ValueImmediate: F = { asExpect().feature(of(TestData::return3, "a", 1, true)).asAssert() contains Values("hello") }
        val return4ValueImmediate: F = { returnValueOf(TestData::return4, "a", 1, true, 1.2) contains Values("hello") }
        val return5ValueImmediate: F = { returnValueOf(TestData::return5, "a", 1, true, 1.2, 'b') contains Values("hello") }
        //TODO remove subAssert once https://youtrack.jetbrains.com/issue/KT-24230 is fixed
        val return0ValueLazy: F = {
            asExpect().feature(
                of(TestData::return0, { asAssert(subAssert { o contains Values("hello") }) })
            ).asAssert()
        }
        val return1ValueLazy: F = {
            asExpect().feature(
                of(TestData::return1, "a", { asAssert { o contains Values("hello") } })
            ).asAssert()
        }
        val return2ValueLazy: F = {
            asExpect().feature(
                of(TestData::return2, "a", 1, { asAssert { o contains Values("hello") } })
            ).asAssert()
        }
        val return3ValueLazy: F = {
            asExpect().feature(
                of(TestData::return3, "a", 1, true, { asAssert { o contains Values("hello") } })
            ).asAssert()
        }
        val return4ValueLazy: F = { returnValueOf(TestData::return4, "a", 1, true, 1.2) { o contains Values("hello") } }
        val return5ValueLazy: F = { returnValueOf(TestData::return5, "a", 1, true, 1.2, 'b') { o contains Values("hello") } }

        val propertyNullableDoesNotHold: F = { asExpect().feature(TestData::nullableValue).asAssert() toBe null }
        val return0ValueNullableDoesNotHold: F = { asExpect().feature(TestData::returnNullable0).asAssert() toBe null }
        val return1ValueNullableDoesNotHold: F = {
            asExpect().feature(of(TestData::returnNullable1, "a")).asAssert() toBe null
        }
        val return2ValueNullableDoesNotHold: F = {
            asExpect().feature(of(TestData::returnNullable2, "a", 1)).asAssert() toBe null
        }
        val return3ValueNullableDoesNotHold: F = {
            asExpect().feature(of(TestData::returnNullable3, "a", 1, true)).asAssert() toBe null
        }
        val return4ValueNullableDoesNotHold: F = {
            asExpect().feature(of(TestData::returnNullable4, "a", 1, true, 1.2)).asAssert() toBe null
        }
        val return5ValueNullableDoesNotHold: F = {
            asExpect().feature(of(TestData::returnNullable5, "a", 1, true, 1.2, 'b')).asAssert() toBe null
        }

        val propertyNullableHolds: F = { asExpect().feature(TestData::nullableValue).asAssert() notToBeNull {} }
        val return0ValueNullableHolds: F = { asExpect().feature(TestData::returnNullable0).asAssert() notToBeNull {} }
        val return1ValueNullableHolds: F = {
            asExpect().feature(of(TestData::returnNullable1, "a")).asAssert() notToBeNull {}
        }
        val return2ValueNullableHolds: F = {
            asExpect().feature(of(TestData::returnNullable2, "a", 1)).asAssert() notToBeNull {}
        }
        val return3ValueNullableHolds: F = {
            asExpect().feature(of(TestData::returnNullable3, "a", 1, true)).asAssert() notToBeNull {}
        }
        val return4ValueNullableHolds: F = {
            asExpect().feature(of(TestData::returnNullable4, "a", 1, true, 1.2)).asAssert() notToBeNull {}
        }
        val return5ValueNullableHolds: F = {
            asExpect().feature(of(TestData::returnNullable5, "a", 1, true, 1.2, 'b')).asAssert() notToBeNull {}
        }

        val propertyLazyWithNestedImmediate: F = {
            asExpect().feature(of(TestData::description, {
                asAssert {
                    asExpect().feature(String::length).asAssert().toBe(12)
                }
            })).asAssert()
        }
        val propertyLazyWithNestedLazy: F = {
            asExpect().feature(of(TestData::description, {
                asAssert {
                    asExpect().feature(of(String::length, { asAssert { toBe(12) } })).asAssert()
                }
            })).asAssert()
        }
    }
}

