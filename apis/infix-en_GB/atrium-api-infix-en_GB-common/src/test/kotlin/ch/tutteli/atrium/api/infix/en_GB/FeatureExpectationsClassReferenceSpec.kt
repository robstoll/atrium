package ch.tutteli.atrium.api.infix.en_GB

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

    //TODO remove type parameters for `of` with Kotiln 1.4 including parentheses (make the calls infix again
    companion object {
        //@formatter:off
        val propertyImmediate: F = { its feature TestData::nonNullValue contains "hello" }
        val propertyLazy: F = { its feature of(TestData::nonNullValue) { it contains "hello" } }
        val return0ValueImmediate: F = { its feature TestData::return0 contains "hello" }
        val return1ValueImmediate: F = { its feature of(TestData::return1, "a") contains "hello" }
        val return2ValueImmediate: F = { its feature of(TestData::return2, "a", 1) contains "hello" }
        val return3ValueImmediate: F = { its feature of(TestData::return3, "a", 1, true) contains "hello" }
        val return4ValueImmediate: F = { its feature of(TestData::return4, "a", 1, true, 1.2) contains "hello" }
        val return5ValueImmediate: F = { its feature of(TestData::return5, "a", 1, true, 1.2, 'b') contains "hello" }
        val return0ValueLazy: F = { its feature of(TestData::return0) { contains("hello") } }
        val return1ValueLazy: F = { its feature of(TestData::return1, "a") { contains("hello") } }
        val return2ValueLazy: F = { its feature of(TestData::return2, "a", 1) { contains("hello") } }
        val return3ValueLazy: F = { its feature of(TestData::return3, "a", 1, true) { contains("hello") } }
        val return4ValueLazy: F = { its feature of(TestData::return4, "a", 1, true, 1.2) { contains("hello") } }
        val return5ValueLazy: F = { its feature of(TestData::return5, "a", 1, true, 1.2, 'b') { contains("hello") } }

        val propertyNullableDoesNotHold: F = { its feature TestData::nullableValue toBe null }
        val return0ValueNullableDoesNotHold: F = { its feature TestData::returnNullable0 toBe null }
        val return1ValueNullableDoesNotHold: F = { its feature of(TestData::returnNullable1, "a") toBe null  }
        val return2ValueNullableDoesNotHold: F = { its feature of(TestData::returnNullable2, "a", 1) toBe null  }
        val return3ValueNullableDoesNotHold: F = { its feature of(TestData::returnNullable3, "a", 1, true) toBe null  }
        val return4ValueNullableDoesNotHold: F = { its feature of(TestData::returnNullable4, "a", 1, true, 1.2) toBe null  }
        val return5ValueNullableDoesNotHold: F = { its feature of(TestData::returnNullable5, "a", 1, true, 1.2, 'b') toBe null  }

        val propertyNullableHolds: F = { its feature TestData::nullableValue notToBeNull { it toBe 1 } }
        val return0ValueNullableHolds: F = { its feature TestData::returnNullable0 notToBeNull { it toBe 1 } }
        val return1ValueNullableHolds: F = { its feature of(TestData::returnNullable1, "a") notToBeNull { it toBe 1 } }
        val return2ValueNullableHolds: F = { its feature of(TestData::returnNullable2, "a", 1) notToBeNull { it toBe 1 } }
        val return3ValueNullableHolds: F = { its feature of(TestData::returnNullable3, "a", 1, true) notToBeNull { it toBe 1 } }
        val return4ValueNullableHolds: F = { its feature of(TestData::returnNullable4, "a", 1, true, 1.2) notToBeNull { it toBe 1 } }
        val return5ValueNullableHolds: F = { its feature of(TestData::returnNullable5, "a", 1, true, 1.2, 'b') notToBeNull { it toBe 1 } }
        //@formatter:on

        val propertyLazyWithNestedImmediate: F = {
            its feature of(TestData::nonNullValue) {
                its feature String::length toBe 12
            }
        }
        val propertyLazyWithNestedLazy: F = {
            its feature of(TestData::nonNullValue) {
                its feature of(String::length) { it toBe (12) }
            }
        }

        val propertyEmptyAssertionCreator: F = { its feature of(TestData::nonNullValue) {} }
        val f0EmptyAssertionCreator: F = { its feature of(TestData::return0) {} }
        val f1EmptyAssertionCreator: F = { its feature of(TestData::return1, "a") {} }
        val f2EmptyAssertionCreator: F = { its feature of(TestData::return2, "a", 1) {} }
        val f3EmptyAssertionCreator: F = { its feature of(TestData::return3, "a", 1, true) {} }
        val f4EmptyAssertionCreator: F = { its feature of(TestData::return4, "a", 1, true, 1.2) {} }
        val f5EmptyAssertionCreator: F = { its feature of(TestData::return5, "a", 1, true, 1.2, 'b') {} }
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val a1: Expect<Collection<Int>> = notImplemented()
        val a1b: Expect<Collection<Int?>> = notImplemented()

        val star: Expect<Collection<*>> = notImplemented()

        a1 feature Collection<*>::size
        a1 feature of(Collection<*>::size) {}

        a1b feature Collection<*>::size
        a1b feature of(Collection<*>::size) {}

        star feature Collection<*>::size
        star feature of(Collection<*>::size) {}
    }
}

