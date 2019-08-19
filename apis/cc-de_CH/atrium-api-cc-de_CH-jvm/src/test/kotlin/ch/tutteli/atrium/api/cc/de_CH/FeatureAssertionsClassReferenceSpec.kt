@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
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
        val propertyImmediate: F = { property(TestData::description).enthaelt("hello") }
        val propertyLazy: F = { property(TestData::description) { enthaelt("hello") } }
        val return0ValueImmediate: F = { rueckgabewertVon(TestData::return0).enthaelt("hello") }
        val return1ValueImmediate: F = { rueckgabewertVon(TestData::return1, "a").enthaelt("hello") }
        val return2ValueImmediate: F = { rueckgabewertVon(TestData::return2, "a", 1).enthaelt("hello") }
        val return3ValueImmediate: F = { rueckgabewertVon(TestData::return3, "a", 1, true).enthaelt("hello") }
        val return4ValueImmediate: F = { rueckgabewertVon(TestData::return4, "a", 1, true, 1.2).enthaelt("hello") }
        val return5ValueImmediate: F = { rueckgabewertVon(TestData::return5, "a", 1, true, 1.2, 'b').enthaelt("hello") }
        //TODO remove subAssert once https://youtrack.jetbrains.com/issue/KT-24230 is fixed
        val return0ValueLazy: F = { rueckgabewertVon(TestData::return0, subAssert { enthaelt("hello") }) }
        val return1ValueLazy: F = { rueckgabewertVon(TestData::return1, "a") { enthaelt("hello") } }
        val return2ValueLazy: F = { rueckgabewertVon(TestData::return2, "a", 1) { enthaelt("hello") } }
        val return3ValueLazy: F = { rueckgabewertVon(TestData::return3, "a", 1, true) { enthaelt("hello") } }
        val return4ValueLazy: F = { rueckgabewertVon(TestData::return4, "a", 1, true, 1.2) { enthaelt("hello") } }
        val return5ValueLazy: F = { rueckgabewertVon(TestData::return5, "a", 1, true, 1.2, 'b') { enthaelt("hello") } }

        val propertyNullableDoesNotHold: F = { property(TestData::nullableValue).ist(null) }
        val return0ValueNullableDoesNotHold: F = { rueckgabewertVon(TestData::returnNullable0).ist(null) }
        val return1ValueNullableDoesNotHold: F = { rueckgabewertVon(TestData::returnNullable1, "a").ist(null) }
        val return2ValueNullableDoesNotHold: F = { rueckgabewertVon(TestData::returnNullable2, "a", 1).ist(null) }
        val return3ValueNullableDoesNotHold: F = { rueckgabewertVon(TestData::returnNullable3, "a", 1, true).ist(null) }
        val return4ValueNullableDoesNotHold: F = { rueckgabewertVon(TestData::returnNullable4, "a", 1, true, 1.2).ist(null) }
        val return5ValueNullableDoesNotHold: F = { rueckgabewertVon(TestData::returnNullable5, "a", 1, true, 1.2, 'b').ist(null) }

        val propertyNullableHolds: F = { property(TestData::nullableValue).istNichtNull {} }
        val return0ValueNullableHolds: F = { rueckgabewertVon(TestData::returnNullable0).istNichtNull {} }
        val return1ValueNullableHolds: F = { rueckgabewertVon(TestData::returnNullable1, "a").istNichtNull {} }
        val return2ValueNullableHolds: F = { rueckgabewertVon(TestData::returnNullable2, "a", 1).istNichtNull {} }
        val return3ValueNullableHolds: F = { rueckgabewertVon(TestData::returnNullable3, "a", 1, true).istNichtNull {} }
        val return4ValueNullableHolds: F = { rueckgabewertVon(TestData::returnNullable4, "a", 1, true, 1.2).istNichtNull {} }
        val return5ValueNullableHolds: F = { rueckgabewertVon(TestData::returnNullable5, "a", 1, true, 1.2, 'b').istNichtNull {} }

        val propertyLazyWithNestedImmediate: F = {
            property(TestData::description) {
                property(String::length).ist(12)
            }
        }
        val propertyLazyWithNestedLazy: F = {
            property(TestData::description) {
                property(String::length) { ist(12) }
            }
        }
    }
}

