package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.TestData
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.testutils.WithAsciiReporter

class FeatureAssertionsClassReferenceSpec : ch.tutteli.atrium.specs.integration.FeatureAssertionsSpec(
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
    companion object : WithAsciiReporter() {
        //@formatter:off
        val propertyImmediate: F = { it feature TestData::nonNullValue contains "hello" }
        val propertyLazy: F = { it feature of(TestData::nonNullValue) { it contains "hello" } }
        val return0ValueImmediate: F = { it feature TestData::return0 contains "hello" }
        val return1ValueImmediate: F = { it feature of(TestData::return1, "a") contains "hello" }
        val return2ValueImmediate: F = { it feature of(TestData::return2, "a", 1) contains "hello" }
        val return3ValueImmediate: F = { it feature of(TestData::return3, "a", 1, true) contains "hello" }
        val return4ValueImmediate: F = { it feature of(TestData::return4, "a", 1, true, 1.2) contains "hello" }
        val return5ValueImmediate: F = { it feature of(TestData::return5, "a", 1, true, 1.2, 'b') contains "hello" }
        val return0ValueLazy: F = { it feature of(TestData::return0) { contains("hello") } }
        val return1ValueLazy: F = { it feature of(TestData::return1, "a") { contains("hello") } }
        val return2ValueLazy: F = { it feature of(TestData::return2, "a", 1) { contains("hello") } }
        val return3ValueLazy: F = { it feature of(TestData::return3, "a", 1, true) { contains("hello") } }
        val return4ValueLazy: F = { it feature of(TestData::return4, "a", 1, true, 1.2) { contains("hello") } }
        val return5ValueLazy: F = { it feature of(TestData::return5, "a", 1, true, 1.2, 'b') { contains("hello") } }

        val propertyNullableDoesNotHold: F = { it feature TestData::nullableValue toBe null }
        val return0ValueNullableDoesNotHold: F = { it feature TestData::returnNullable0 toBe null }
        val return1ValueNullableDoesNotHold: F = { it feature of(TestData::returnNullable1, "a") toBe null  }
        val return2ValueNullableDoesNotHold: F = { it feature of(TestData::returnNullable2, "a", 1) toBe null  }
        val return3ValueNullableDoesNotHold: F = { it feature of(TestData::returnNullable3, "a", 1, true) toBe null  }
        val return4ValueNullableDoesNotHold: F = { it feature of(TestData::returnNullable4, "a", 1, true, 1.2) toBe null  }
        val return5ValueNullableDoesNotHold: F = { it feature of(TestData::returnNullable5, "a", 1, true, 1.2, 'b') toBe null  }

        val propertyNullableHolds: F = { it feature TestData::nullableValue notToBeNull { it toBe 1 } }
        val return0ValueNullableHolds: F = { it feature TestData::returnNullable0 notToBeNull { it toBe 1 } }
        val return1ValueNullableHolds: F = { it feature of(TestData::returnNullable1, "a") notToBeNull { it toBe 1 } }
        val return2ValueNullableHolds: F = { it feature of(TestData::returnNullable2, "a", 1) notToBeNull { it toBe 1 } }
        val return3ValueNullableHolds: F = { it feature of(TestData::returnNullable3, "a", 1, true) notToBeNull { it toBe 1 } }
        val return4ValueNullableHolds: F = { it feature of(TestData::returnNullable4, "a", 1, true, 1.2) notToBeNull { it toBe 1 } }
        val return5ValueNullableHolds: F = { it feature of(TestData::returnNullable5, "a", 1, true, 1.2, 'b') notToBeNull { it toBe 1 } }
        //@formatter:on

        val propertyLazyWithNestedImmediate: F = {
            it feature of(TestData::nonNullValue) {
                it feature String::length toBe 12
            }
        }
        val propertyLazyWithNestedLazy: F = {
            it feature of(TestData::nonNullValue) {
                it feature of(String::length) { it toBe (12) }
            }
        }

        val propertyEmptyAssertionCreator: F = { it feature of(TestData::nonNullValue) {} }
        val f0EmptyAssertionCreator: F = { it feature of(TestData::return0) {} }
        val f1EmptyAssertionCreator: F = { it feature of(TestData::return1, "a") {} }
        val f2EmptyAssertionCreator: F = { it feature of(TestData::return2, "a", 1) {} }
        val f3EmptyAssertionCreator: F = { it feature of(TestData::return3, "a", 1, true) {} }
        val f4EmptyAssertionCreator: F = { it feature of(TestData::return4, "a", 1, true, 1.2) {} }
        val f5EmptyAssertionCreator: F = { it feature of(TestData::return5, "a", 1, true, 1.2, 'b') {} }
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

