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
        val propertyImmediate: F = { o feature TestData::nonNullValue contains "hello" }
        val propertyLazy: F = { o feature of(TestData::nonNullValue) { o contains "hello" } }
        val return0ValueImmediate: F = { o feature TestData::return0 contains "hello" }
        val return1ValueImmediate: F = { o feature of(TestData::return1, "a") contains "hello" }
        val return2ValueImmediate: F = { o feature of(TestData::return2, "a", 1) contains "hello" }
        val return3ValueImmediate: F = { o feature of(TestData::return3, "a", 1, true) contains "hello" }
        val return4ValueImmediate: F = { o feature of(TestData::return4, "a", 1, true, 1.2) contains "hello" }
        val return5ValueImmediate: F = { o feature of(TestData::return5, "a", 1, true, 1.2, 'b') contains "hello" }
        val return0ValueLazy: F = { o feature of(TestData::return0) { contains("hello") } }
        val return1ValueLazy: F = { o feature of(TestData::return1, "a") { contains("hello") } }
        val return2ValueLazy: F = { o feature of(TestData::return2, "a", 1) { contains("hello") } }
        val return3ValueLazy: F = { o feature of(TestData::return3, "a", 1, true) { contains("hello") } }
        val return4ValueLazy: F = { o feature of(TestData::return4, "a", 1, true, 1.2) { contains("hello") } }
        val return5ValueLazy: F = { o feature of(TestData::return5, "a", 1, true, 1.2, 'b') { contains("hello") } }

        val propertyNullableDoesNotHold: F = { o feature TestData::nullableValue toBe null }
        val return0ValueNullableDoesNotHold: F = { o feature TestData::returnNullable0 toBe null }
        val return1ValueNullableDoesNotHold: F = { o feature of(TestData::returnNullable1, "a") toBe null  }
        val return2ValueNullableDoesNotHold: F = { o feature of(TestData::returnNullable2, "a", 1) toBe null  }
        val return3ValueNullableDoesNotHold: F = { o feature of(TestData::returnNullable3, "a", 1, true) toBe null  }
        val return4ValueNullableDoesNotHold: F = { o feature of(TestData::returnNullable4, "a", 1, true, 1.2) toBe null  }
        val return5ValueNullableDoesNotHold: F = { o feature of(TestData::returnNullable5, "a", 1, true, 1.2, 'b') toBe null  }

        val propertyNullableHolds: F = { o feature TestData::nullableValue notToBeNull { o toBe 1 } }
        val return0ValueNullableHolds: F = { o feature TestData::returnNullable0 notToBeNull { o toBe 1 } }
        val return1ValueNullableHolds: F = { o feature of(TestData::returnNullable1, "a") notToBeNull { o toBe 1 } }
        val return2ValueNullableHolds: F = { o feature of(TestData::returnNullable2, "a", 1) notToBeNull { o toBe 1 } }
        val return3ValueNullableHolds: F = { o feature of(TestData::returnNullable3, "a", 1, true) notToBeNull { o toBe 1 } }
        val return4ValueNullableHolds: F = { o feature of(TestData::returnNullable4, "a", 1, true, 1.2) notToBeNull { o toBe 1 } }
        val return5ValueNullableHolds: F = { o feature of(TestData::returnNullable5, "a", 1, true, 1.2, 'b') notToBeNull { o toBe 1 } }
        //@formatter:on

        val propertyLazyWithNestedImmediate: F = {
            o feature of(TestData::nonNullValue) {
                o feature String::length toBe 12
            }
        }
        val propertyLazyWithNestedLazy: F = {
            o feature of(TestData::nonNullValue) {
                o feature of(String::length) { o toBe (12) }
            }
        }

        val propertyEmptyAssertionCreator: F = { o feature of(TestData::nonNullValue) {} }
        val f0EmptyAssertionCreator: F = { o feature of(TestData::return0) {} }
        val f1EmptyAssertionCreator: F = { o feature of(TestData::return1, "a") {} }
        val f2EmptyAssertionCreator: F = { o feature of(TestData::return2, "a", 1) {} }
        val f3EmptyAssertionCreator: F = { o feature of(TestData::return3, "a", 1, true) {} }
        val f4EmptyAssertionCreator: F = { o feature of(TestData::return4, "a", 1, true, 1.2) {} }
        val f5EmptyAssertionCreator: F = { o feature of(TestData::return5, "a", 1, true, 1.2, 'b') {} }
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

