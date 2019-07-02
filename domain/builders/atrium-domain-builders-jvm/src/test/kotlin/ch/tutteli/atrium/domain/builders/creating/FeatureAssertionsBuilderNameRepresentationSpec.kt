package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.api.cc.en_GB.contains
import ch.tutteli.atrium.api.cc.en_GB.notToBeNull
import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.property
import ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf0
import ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf1
import ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf2
import ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf3
import ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf4
import ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf5
import ch.tutteli.atrium.reporting.translating.Untranslatable


class FeatureAssertionsBuilderNameRepresentationSpec : ch.tutteli.atrium.spec.integration.FeatureAssertionsSpec(
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
    propertyLazyWithNestedLazy,
    representationProvider()
) {

    @Suppress("DEPRECATION" /* TODO #40 creating feature assertions will change anyway, thus not fixing the usages of `subject` */)
    companion object {
        val representationProvider =  { "own representation" }
        val propertyImmediate: F = { property(this, { subject.description }, representationProvider, Untranslatable("description")).contains("hello") }
        val propertyLazy: F = { property(this, { subject.description }, representationProvider, Untranslatable("description")) { contains("hello") } }
        val return0ValueImmediate: F = { returnValueOf0(this, { subject.return0() }, representationProvider, "return0").contains("hello") }
        val return1ValueImmediate: F = { returnValueOf1(this, {a1 -> subject.return1(a1) }, "a", representationProvider, "return1").contains("hello") }
        val return2ValueImmediate: F = { returnValueOf2(this, {a1, a2 -> subject.return2(a1, a2) }, "a", 1, representationProvider, "return2").contains("hello") }
        val return3ValueImmediate: F = { returnValueOf3(this, {a1, a2, a3 -> subject.return3(a1, a2, a3) }, "a", 1, true, representationProvider, "return3").contains("hello") }
        val return4ValueImmediate: F = { returnValueOf4(this, {a1, a2, a3, a4 -> subject.return4(a1, a2, a3, a4) }, "a", 1, true, 1.2, representationProvider, "return4").contains("hello") }
        val return5ValueImmediate: F = { returnValueOf5(this, {a1, a2, a3, a4, a5 -> subject.return5(a1, a2, a3, a4, a5) }, "a", 1, true, 1.2, 'b', representationProvider, "return5").contains("hello") }
        val return0ValueLazy: F = { returnValueOf0(this, { subject.return0() }, representationProvider, "return0") { contains("hello") } }
        val return1ValueLazy: F = { returnValueOf1(this, {a1 -> subject.return1(a1) }, "a", representationProvider, "return1") { contains("hello") } }
        val return2ValueLazy: F = { returnValueOf2(this, {a1, a2 -> subject.return2(a1, a2) }, "a", 1, representationProvider, "return2") { contains("hello") } }
        val return3ValueLazy: F = { returnValueOf3(this, {a1, a2, a3 -> subject.return3(a1, a2, a3) }, "a", 1, true, representationProvider, "return3") { contains("hello") } }
        val return4ValueLazy: F = { returnValueOf4(this, {a1, a2, a3, a4 -> subject.return4(a1, a2, a3, a4) }, "a", 1, true, 1.2, representationProvider, "return4") { contains("hello") } }
        val return5ValueLazy: F = { returnValueOf5(this, {a1, a2, a3, a4, a5 -> subject.return5(a1, a2, a3, a4, a5) }, "a", 1, true, 1.2, 'b', representationProvider, "return5") { contains("hello") } }

        //TODO get rid of l if https://youtrack.jetbrains.com/issue/KT-23768 is fixed
        val propertyNullableDoesNotHold: F = { val l = { subject.nullableValue }; property(this, l, representationProvider, Untranslatable("nullableValue")).toBe(null) }
        val return0ValueNullableDoesNotHold: F = { val l = { subject.returnNullable0() }; returnValueOf0(this, l, representationProvider, "returnNullable0").toBe(null) }
        val return1ValueNullableDoesNotHold: F = { val l: (String) -> Int? = {a1 -> subject.returnNullable1(a1) }; returnValueOf1(this, l, "a", representationProvider, "returnNullable1").toBe(null) }
        val return2ValueNullableDoesNotHold: F = { val l: (String, Int) -> Int? = {a1, a2 -> subject.returnNullable2(a1, a2) }; returnValueOf2(this, l, "a", 1, representationProvider, "returnNullable2").toBe(null) }
        val return3ValueNullableDoesNotHold: F = { val l: (String, Int, Boolean) -> Int? = {a1, a2, a3 -> subject.returnNullable3(a1, a2, a3) }; returnValueOf3(this, l, "a", 1, true, representationProvider, "returnNullable3").toBe(null) }
        val return4ValueNullableDoesNotHold: F = { val l: (String, Int, Boolean, Double) -> Int? = {a1, a2, a3, a4 -> subject.returnNullable4(a1, a2, a3, a4) }; returnValueOf4(this, l, "a", 1, true, 1.2, representationProvider, "returnNullable4").toBe(null) }
        val return5ValueNullableDoesNotHold: F = { val l: (String, Int, Boolean, Double, Char) -> Int? = {a1, a2, a3, a4, a5 -> subject.returnNullable5(a1, a2, a3, a4, a5) }; returnValueOf5(this, l, "a", 1, true, 1.2, 'b', representationProvider, "returnNullable5").toBe(null) }

        //TODO get rid of l if https://youtrack.jetbrains.com/issue/KT-23768 is fixed
        val propertyNullableHolds: F = { val l = { subject.nullableValue }; property(this, l, representationProvider, Untranslatable("nullableValue")).notToBeNull {} }
        val return0ValueNullableHolds: F = { val l = { subject.returnNullable0() }; returnValueOf0(this, l, representationProvider, "returnNullable0").notToBeNull {} }
        val return1ValueNullableHolds: F = { val l: (String) -> Int? = {a1 -> subject.returnNullable1(a1) }; returnValueOf1(this, l, "a", representationProvider, "returnNullable1").notToBeNull {} }
        val return2ValueNullableHolds: F = { val l: (String, Int) -> Int? = {a1, a2 -> subject.returnNullable2(a1, a2) }; returnValueOf2(this, l, "a", 1, representationProvider, "returnNullable2").notToBeNull {} }
        val return3ValueNullableHolds: F = { val l: (String, Int, Boolean) -> Int? = {a1, a2, a3 -> subject.returnNullable3(a1, a2, a3) }; returnValueOf3(this, l, "a", 1, true, representationProvider, "returnNullable3").notToBeNull {} }
        val return4ValueNullableHolds: F = { val l: (String, Int, Boolean, Double) -> Int? = {a1, a2, a3, a4 -> subject.returnNullable4(a1, a2, a3, a4) }; returnValueOf4(this, l, "a", 1, true, 1.2, representationProvider, "returnNullable4").notToBeNull {} }
        val return5ValueNullableHolds: F = { val l: (String, Int, Boolean, Double, Char) -> Int? = {a1, a2, a3, a4, a5 -> subject.returnNullable5(a1, a2, a3, a4, a5) }; returnValueOf5(this, l, "a", 1, true, 1.2, 'b', representationProvider, "returnNullable5").notToBeNull {} }


        val propertyLazyWithNestedImmediate: F = {
            property(this, { subject.description }, representationProvider, Untranslatable("description")) {
                property(this, String::length).toBe(12)
            }
        }
        val propertyLazyWithNestedLazy: F = {
            property(this, { subject.description }, representationProvider, Untranslatable("description")) {
                property(this, String::length) { toBe(12) }
            }
        }
    }
}
