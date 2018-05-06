package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
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
        val propertyImmediate: F = { property(subject::description).enthaelt("hello") }
        val propertyLazy: F = { property(subject::description) { enthaelt("hello") } }
        val return0ValueImmediate: F = { rueckgabewertVon(subject::return0).enthaelt("hello") }
        val return1ValueImmediate: F = { rueckgabewertVon(subject::return1, "a").enthaelt("hello") }
        val return2ValueImmediate: F = { rueckgabewertVon(subject::return2, "a", 1).enthaelt("hello") }
        val return3ValueImmediate: F = { rueckgabewertVon(subject::return3, "a", 1, true).enthaelt("hello") }
        val return4ValueImmediate: F = { rueckgabewertVon(subject::return4, "a", 1, true, 1.2).enthaelt("hello") }
        val return5ValueImmediate: F = { rueckgabewertVon(subject::return5, "a", 1, true, 1.2, 'b').enthaelt("hello") }
        val return0ValueLazy: F = { rueckgabewertVon(subject::return0) { enthaelt("hello") } }
        val return1ValueLazy: F = { rueckgabewertVon(subject::return1, "a") { enthaelt("hello") } }
        val return2ValueLazy: F = { rueckgabewertVon(subject::return2, "a", 1) { enthaelt("hello") } }
        val return3ValueLazy: F = { rueckgabewertVon(subject::return3, "a", 1, true) { enthaelt("hello") } }
        val return4ValueLazy: F = { rueckgabewertVon(subject::return4, "a", 1, true, 1.2) { enthaelt("hello") } }
        val return5ValueLazy: F = { rueckgabewertVon(subject::return5, "a", 1, true, 1.2, 'b') { enthaelt("hello") } }

        val propertyNullableDoesNotHold: F = { property(subject::nullableValue).ist(null) }
        val return0ValueNullableDoesNotHold: F = { rueckgabewertVon(subject::returnNullable0).ist(null) }
        val return1ValueNullableDoesNotHold: F = { rueckgabewertVon(subject::returnNullable1, "a").ist(null) }
        val return2ValueNullableDoesNotHold: F = { rueckgabewertVon(subject::returnNullable2, "a", 1).ist(null) }
        val return3ValueNullableDoesNotHold: F = { rueckgabewertVon(subject::returnNullable3, "a", 1, true).ist(null) }
        val return4ValueNullableDoesNotHold: F = { rueckgabewertVon(subject::returnNullable4, "a", 1, true, 1.2).ist(null) }
        val return5ValueNullableDoesNotHold: F = { rueckgabewertVon(subject::returnNullable5, "a", 1, true, 1.2, 'b').ist(null) }

        val propertyNullableHolds: F = { property(subject::nullableValue).istNichtNull {} }
        val return0ValueNullableHolds: F = { rueckgabewertVon(subject::returnNullable0).istNichtNull {} }
        val return1ValueNullableHolds: F = { rueckgabewertVon(subject::returnNullable1, "a").istNichtNull {} }
        val return2ValueNullableHolds: F = { rueckgabewertVon(subject::returnNullable2, "a", 1).istNichtNull {} }
        val return3ValueNullableHolds: F = { rueckgabewertVon(subject::returnNullable3, "a", 1, true).istNichtNull {} }
        val return4ValueNullableHolds: F = { rueckgabewertVon(subject::returnNullable4, "a", 1, true, 1.2).istNichtNull {} }
        val return5ValueNullableHolds: F = { rueckgabewertVon(subject::returnNullable5, "a", 1, true, 1.2, 'b').istNichtNull {} }

        val propertyLazyWithNestedImmediate: F = {
            property(subject::description) {
                property(subject::length).ist(12)
            }
        }
        val propertyLazyWithNestedLazy: F = {
            property(subject::description) {
                property(subject::length) { ist(12) }
            }
        }
    }
}

