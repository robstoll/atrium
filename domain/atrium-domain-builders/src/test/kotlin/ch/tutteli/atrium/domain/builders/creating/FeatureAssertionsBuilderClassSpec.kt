package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.api.cc.en_UK.contains
import ch.tutteli.atrium.api.cc.en_UK.isNotNull
import ch.tutteli.atrium.api.cc.en_UK.isNull
import ch.tutteli.atrium.api.cc.en_UK.toBe
import ch.tutteli.atrium.domain.builders.AssertionVerbFactory
import ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.property
import ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf0
import ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf1
import ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf2
import ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf3
import ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf4
import ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf5
import ch.tutteli.atrium.spec.integration.TestData

class FeatureAssertionsBuilderClassSpec : ch.tutteli.atrium.spec.integration.FeatureAssertionsSpec(
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
        val propertyImmediate: F = { property(this, TestData::description).contains("hello") }
        val propertyLazy: F = { property(this, TestData::description) { contains("hello") } }
        val return0ValueImmediate: F = { returnValueOf0(this, TestData::return0).contains("hello") }
        val return1ValueImmediate: F = { returnValueOf1(this, TestData::return1, "a").contains("hello") }
        val return2ValueImmediate: F = { returnValueOf2(this, TestData::return2, "a", 1).contains("hello") }
        val return3ValueImmediate: F = { returnValueOf3(this, TestData::return3, "a", 1, true).contains("hello") }
        val return4ValueImmediate: F = { returnValueOf4(this, TestData::return4, "a", 1, true, 1.2).contains("hello") }
        val return5ValueImmediate: F = { returnValueOf5(this, TestData::return5, "a", 1, true, 1.2, 'b').contains("hello") }
        val return0ValueLazy: F = { returnValueOf0(this, TestData::return0) { contains("hello") } }
        val return1ValueLazy: F = { returnValueOf1(this, TestData::return1, "a") { contains("hello") } }
        val return2ValueLazy: F = { returnValueOf2(this, TestData::return2, "a", 1) { contains("hello") } }
        val return3ValueLazy: F = { returnValueOf3(this, TestData::return3, "a", 1, true) { contains("hello") } }
        val return4ValueLazy: F = { returnValueOf4(this, TestData::return4, "a", 1, true, 1.2) { contains("hello") } }
        val return5ValueLazy: F = { returnValueOf5(this, TestData::return5, "a", 1, true, 1.2, 'b') { contains("hello") } }

        val propertyNullableDoesNotHold: F = { property(this, TestData::nullableValue).isNull() }
        val return0ValueNullableDoesNotHold: F = { returnValueOf0(this, TestData::returnNullable0).isNull() }
        val return1ValueNullableDoesNotHold: F = { returnValueOf1(this, TestData::returnNullable1, "a").isNull() }
        val return2ValueNullableDoesNotHold: F = { returnValueOf2(this, TestData::returnNullable2, "a", 1).isNull() }
        val return3ValueNullableDoesNotHold: F = { returnValueOf3(this, TestData::returnNullable3, "a", 1, true).isNull() }
        val return4ValueNullableDoesNotHold: F = { returnValueOf4(this, TestData::returnNullable4, "a", 1, true, 1.2).isNull() }
        val return5ValueNullableDoesNotHold: F = { returnValueOf5(this, TestData::returnNullable5, "a", 1, true, 1.2, 'b').isNull() }

        val propertyNullableHolds: F = { property(this, TestData::nullableValue).isNotNull {} }
        val return0ValueNullableHolds: F = { returnValueOf0(this, TestData::returnNullable0).isNotNull {} }
        val return1ValueNullableHolds: F = { returnValueOf1(this, TestData::returnNullable1, "a").isNotNull {} }
        val return2ValueNullableHolds: F = { returnValueOf2(this, TestData::returnNullable2, "a", 1).isNotNull {} }
        val return3ValueNullableHolds: F = { returnValueOf3(this, TestData::returnNullable3, "a", 1, true).isNotNull {} }
        val return4ValueNullableHolds: F = { returnValueOf4(this, TestData::returnNullable4, "a", 1, true, 1.2).isNotNull {} }
        val return5ValueNullableHolds: F = { returnValueOf5(this, TestData::returnNullable5, "a", 1, true, 1.2, 'b').isNotNull {} }


        val propertyLazyWithNestedImmediate: F = {
            property(this, TestData::description) {
                property(this, String::length).toBe(12)
            }
        }
        val propertyLazyWithNestedLazy: F = {
            property(this, TestData::description) {
                property(this, String::length) { toBe(12) }
            }
        }
    }
}

