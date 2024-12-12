package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.TestData
import ch.tutteli.atrium.specs.notImplemented

class FeatureExpectationsClassReferenceSpec : ch.tutteli.atrium.specs.integration.FeatureExpectationsSpec(
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

    propertyEmptyAssertionCreator,
    f0EmptyAssertionCreator,
    f1EmptyAssertionCreator,
    f2EmptyAssertionCreator,
    f3EmptyAssertionCreator,
    f4EmptyAssertionCreator,
    f5EmptyAssertionCreator,

    isAbleToEvaluateDescription = true
) {

    companion object {
        //@formatter:off
        val propertyImmediate: F = { feature(TestData::nonNullValue).toContain("hello" ) }
        val propertyLazy: F = { feature(TestData::nonNullValue) { toContain("hello" ) } }
        val return0ValueImmediate: F = { feature(TestData::return0).toContain("hello" ) }
        val return1ValueImmediate: F = { feature(TestData::return1, "a").toContain("hello" ) }
        val return2ValueImmediate: F = { feature(TestData::return2, "a", 1).toContain("hello" ) }
        val return3ValueImmediate: F = { feature(TestData::return3, "a", 1, true).toContain("hello" ) }
        val return4ValueImmediate: F = { feature(TestData::return4, "a", 1, true, 1.2).toContain("hello" ) }
        val return5ValueImmediate: F = { feature(TestData::return5, "a", 1, true, 1.2, 'b').toContain("hello" ) }
        val return0ValueLazy: F = { feature(TestData::return0) { toContain("hello" ) } }
        val return1ValueLazy: F = { feature(TestData::return1, "a") { toContain("hello" ) } }
        val return2ValueLazy: F = { feature(TestData::return2, "a", 1) { toContain("hello" ) } }
        val return3ValueLazy: F = { feature(TestData::return3, "a", 1, true) { toContain("hello" ) } }
        val return4ValueLazy: F = { feature(TestData::return4, "a", 1, true, 1.2) { toContain("hello" ) } }
        val return5ValueLazy: F = { feature(TestData::return5, "a", 1, true, 1.2, 'b') { toContain("hello" ) } }

        val propertyNullableDoesNotHold: F = { feature(TestData::nullableValue).toEqual(null) }
        val return0ValueNullableDoesNotHold: F = { feature(TestData::returnNullable0).toEqual(null) }
        val return1ValueNullableDoesNotHold: F = { feature(TestData::returnNullable1, "a").toEqual(null) }
        val return2ValueNullableDoesNotHold: F = { feature(TestData::returnNullable2, "a", 1).toEqual(null) }
        val return3ValueNullableDoesNotHold: F = { feature(TestData::returnNullable3, "a", 1, true).toEqual(null) }
        val return4ValueNullableDoesNotHold: F = { feature(TestData::returnNullable4, "a", 1, true, 1.2).toEqual(null) }
        val return5ValueNullableDoesNotHold: F = { feature(TestData::returnNullable5, "a", 1, true, 1.2, 'b').toEqual(null) }

        val propertyNullableHolds: F = { feature(TestData::nullableValue).notToEqualNull { toEqual(1) } }
        val return0ValueNullableHolds: F = { feature(TestData::returnNullable0).notToEqualNull { toEqual(1) } }
        val return1ValueNullableHolds: F = { feature(TestData::returnNullable1, "a").notToEqualNull { toEqual(1) } }
        val return2ValueNullableHolds: F = { feature(TestData::returnNullable2, "a", 1).notToEqualNull { toEqual(1) } }
        val return3ValueNullableHolds: F = { feature(TestData::returnNullable3, "a", 1, true).notToEqualNull { toEqual(1) } }
        val return4ValueNullableHolds: F = { feature(TestData::returnNullable4, "a", 1, true, 1.2).notToEqualNull { toEqual(1) } }
        val return5ValueNullableHolds: F = { feature(TestData::returnNullable5, "a", 1, true, 1.2, 'b').notToEqualNull { toEqual(1) } }
        //@formatter:on

        val propertyLazyWithNestedImmediate: F = {
            feature(TestData::nonNullValue) {
                feature(String::length).toEqual(12)
            }
        }
        val propertyLazyWithNestedLazy: F = {
            feature(TestData::nonNullValue) {
                feature(String::length) { toEqual(12) }
            }
        }

        val propertyEmptyAssertionCreator: F = { feature(TestData::nonNullValue) {} }
        val f0EmptyAssertionCreator: F = { feature(TestData::return0) {} }
        val f1EmptyAssertionCreator: F = { feature(TestData::return1, "a") {} }
        val f2EmptyAssertionCreator: F = { feature(TestData::return2, "a", 1) {} }
        val f3EmptyAssertionCreator: F = { feature(TestData::return3, "a", 1, true) {} }
        val f4EmptyAssertionCreator: F = { feature(TestData::return4, "a", 1, true, 1.2) {} }
        val f5EmptyAssertionCreator: F = { feature(TestData::return5, "a", 1, true, 1.2, 'b') {} }
    }

    @Suppress("unused")
    private fun ambiguityTest() {
        val a1: Expect<Collection<Int>> = notImplemented()
        val a1b: Expect<Collection<Int?>> = notImplemented()

        val star: Expect<Collection<*>> = notImplemented()

        a1.feature(Collection<*>::size)
        a1.feature(Collection<*>::size) {}

        a1b.feature(Collection<*>::size)
        a1b.feature(Collection<*>::size) {}

        star.feature(Collection<*>::size)
        star.feature(Collection<*>::size) {}
    }
}

