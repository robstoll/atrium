package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.api.cc.en_UK.contains
import ch.tutteli.atrium.api.cc.en_UK.isNotNull
import ch.tutteli.atrium.api.cc.en_UK.isNull
import ch.tutteli.atrium.api.cc.en_UK.toBe
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.AssertionVerbFactory
import ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.property
import ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf0
import ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf1
import ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf2
import ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf3
import ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf4
import ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf5
import ch.tutteli.atrium.spec.integration.TestData

internal typealias F = Assert<TestData>.() -> Unit

class FeatureAssertionsBuilderNameSpec : ch.tutteli.atrium.spec.integration.FeatureAssertionsSpec(
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
        val propertyImmediate: F = { property(this, "description", { subject.description }).contains("hello") }
        val propertyLazy: F = { property(this, "description", { subject.description }) { contains("hello") } }
        val return0ValueImmediate: F = { returnValueOf0(this, "return0", { subject.return0() }).contains("hello") }
        val return1ValueImmediate: F = { returnValueOf1(this, "return1", {a1 -> subject.return1(a1) }, "a").contains("hello") }
        val return2ValueImmediate: F = { returnValueOf2(this, "return2", {a1, a2 -> subject.return2(a1, a2) }, "a", 1).contains("hello") }
        val return3ValueImmediate: F = { returnValueOf3(this, "return3", {a1, a2, a3 -> subject.return3(a1, a2, a3) }, "a", 1, true).contains("hello") }
        val return4ValueImmediate: F = { returnValueOf4(this, "return4", {a1, a2, a3, a4 -> subject.return4(a1, a2, a3, a4) }, "a", 1, true, 1.2).contains("hello") }
        val return5ValueImmediate: F = { returnValueOf5(this, "return5", {a1, a2, a3, a4, a5 -> subject.return5(a1, a2, a3, a4, a5) }, "a", 1, true, 1.2, 'b').contains("hello") }
        val return0ValueLazy: F = { returnValueOf0(this, "return0", { subject.return0() }) { contains("hello") } }
        val return1ValueLazy: F = { returnValueOf1(this, "return1", {a1 -> subject.return1(a1) }, "a") { contains("hello") } }
        val return2ValueLazy: F = { returnValueOf2(this, "return2", {a1, a2 -> subject.return2(a1, a2) }, "a", 1) { contains("hello") } }
        val return3ValueLazy: F = { returnValueOf3(this, "return3", {a1, a2, a3 -> subject.return3(a1, a2, a3) }, "a", 1, true) { contains("hello") } }
        val return4ValueLazy: F = { returnValueOf4(this, "return4", {a1, a2, a3, a4 -> subject.return4(a1, a2, a3, a4) }, "a", 1, true, 1.2) { contains("hello") } }
        val return5ValueLazy: F = { returnValueOf5(this, "return5", {a1, a2, a3, a4, a5 -> subject.return5(a1, a2, a3, a4, a5) }, "a", 1, true, 1.2, 'b') { contains("hello") } }

        //TODO get rid of l if https://youtrack.jetbrains.com/issue/KT-23768 is fixed
        val propertyNullableDoesNotHold: F = { val l = { subject.nullableValue }; property(this, "nullableValue", l).isNull() }
        val return0ValueNullableDoesNotHold: F = { val l = { subject.returnNullable0() }; returnValueOf0(this, "returnNullable0", l).isNull() }
        val return1ValueNullableDoesNotHold: F = { val l: (String) -> Int? = {a1 -> subject.returnNullable1(a1) }; returnValueOf1(this, "returnNullable1", l, "a").isNull() }
        val return2ValueNullableDoesNotHold: F = { val l: (String, Int) -> Int? = {a1, a2 -> subject.returnNullable2(a1, a2) }; returnValueOf2(this, "returnNullable2", l, "a", 1).isNull() }
        val return3ValueNullableDoesNotHold: F = { val l: (String, Int, Boolean) -> Int? = {a1, a2, a3 -> subject.returnNullable3(a1, a2, a3) }; returnValueOf3(this, "returnNullable3", l, "a", 1, true).isNull() }
        val return4ValueNullableDoesNotHold: F = { val l: (String, Int, Boolean, Double) -> Int? = {a1, a2, a3, a4 -> subject.returnNullable4(a1, a2, a3, a4) }; returnValueOf4(this, "returnNullable4", l, "a", 1, true, 1.2).isNull() }
        val return5ValueNullableDoesNotHold: F = { val l: (String, Int, Boolean, Double, Char) -> Int? = {a1, a2, a3, a4, a5 -> subject.returnNullable5(a1, a2, a3, a4, a5) }; returnValueOf5(this, "returnNullable5", l, "a", 1, true, 1.2, 'b').isNull() }

        //TODO get rid of l if https://youtrack.jetbrains.com/issue/KT-23768 is fixed
        val propertyNullableHolds: F = { val l = { subject.nullableValue }; property(this, "nullableValue", l).isNotNull {} }
        val return0ValueNullableHolds: F = { val l = { subject.returnNullable0() }; returnValueOf0(this, "returnNullable0", l).isNotNull {} }
        val return1ValueNullableHolds: F = { val l: (String) -> Int? = {a1 -> subject.returnNullable1(a1) }; returnValueOf1(this, "returnNullable1", l, "a").isNotNull {} }
        val return2ValueNullableHolds: F = { val l: (String, Int) -> Int? = {a1, a2 -> subject.returnNullable2(a1, a2) }; returnValueOf2(this, "returnNullable2", l, "a", 1).isNotNull {} }
        val return3ValueNullableHolds: F = { val l: (String, Int, Boolean) -> Int? = {a1, a2, a3 -> subject.returnNullable3(a1, a2, a3) }; returnValueOf3(this, "returnNullable3", l, "a", 1, true).isNotNull {} }
        val return4ValueNullableHolds: F = { val l: (String, Int, Boolean, Double) -> Int? = {a1, a2, a3, a4 -> subject.returnNullable4(a1, a2, a3, a4) }; returnValueOf4(this, "returnNullable4", l, "a", 1, true, 1.2).isNotNull {} }
        val return5ValueNullableHolds: F = { val l: (String, Int, Boolean, Double, Char) -> Int? = {a1, a2, a3, a4, a5 -> subject.returnNullable5(a1, a2, a3, a4, a5) }; returnValueOf5(this, "returnNullable5", l, "a", 1, true, 1.2, 'b').isNotNull {} }


        val propertyLazyWithNestedImmediate: F = {
            property(this, "description", { subject.description }) {
                property(this, String::length).toBe(12)
            }
        }
        val propertyLazyWithNestedLazy: F = {
            property(this, "description", { subject.description }) {
                property(this, String::length) { toBe(12) }
            }
        }
    }
}

