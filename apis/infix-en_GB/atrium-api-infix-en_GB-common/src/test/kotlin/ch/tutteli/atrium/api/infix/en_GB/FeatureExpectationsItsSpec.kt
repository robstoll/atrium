package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.specs.integration.TestData

class FeatureExpectationsItsSpec : ch.tutteli.atrium.specs.integration.FeatureExpectationsSpec(
    propertyImmediate,
    propertyLazy,
    f0Immediate,
    f1Immediate,
    f2Immediate,
    f3Immediate,
    f4Immediate,
    f5Immediate,
    f0Lazy,
    f1Lazy,
    f2Lazy,
    f3Lazy,
    f4Lazy,
    f5Lazy,

    propertyNullableDoesNotHold,
    f0NullableDoesNotHold,
    f1NullableDoesNotHold,
    f2NullableDoesNotHold,
    f3NullableDoesNotHold,
    f4NullableDoesNotHold,
    f5NullableDoesNotHold,

    propertyNullableHolds,
    f0NullableHolds,
    f1NullableHolds,
    f2NullableHolds,
    f3NullableHolds,
    f4NullableHolds,
    f5NullableHolds,

    propertyLazyWithNestedImmediate,
    propertyLazyWithNestedLazy,

    propertyEmptyAssertionCreator,
    f0EmptyAssertionCreator,
    f1EmptyAssertionCreator,
    f2EmptyAssertionCreator,
    f3EmptyAssertionCreator,
    f4EmptyAssertionCreator,
    f5EmptyAssertionCreator,

    isAbleToEvaluateDescription = true,
    return0ImmediateFeatureInfo = "its.definedIn(FeatureExpectationsItsSpec.kt:${offset})",
    return1ImmediateFeatureInfo = "its.definedIn(FeatureExpectationsItsSpec.kt:${offset + 1})",
    return2ImmediateFeatureInfo = "its.definedIn(FeatureExpectationsItsSpec.kt:${offset + 2})",
    return3ImmediateFeatureInfo = "its.definedIn(FeatureExpectationsItsSpec.kt:${offset + 3})",
    return4ImmediateFeatureInfo = "its.definedIn(FeatureExpectationsItsSpec.kt:${offset + 4})",
    return5ImmediateFeatureInfo = "its.definedIn(FeatureExpectationsItsSpec.kt:${offset + 5})",
    return0LazyFeatureInfo = "its.definedIn(FeatureExpectationsItsSpec.kt:${offset + 6})",
    return1LazyFeatureInfo = "its.definedIn(FeatureExpectationsItsSpec.kt:${offset + 7})",
    return2LazyFeatureInfo = "its.definedIn(FeatureExpectationsItsSpec.kt:${offset + 8})",
    return3LazyFeatureInfo = "its.definedIn(FeatureExpectationsItsSpec.kt:${offset + 9})",
    return4LazyFeatureInfo = "its.definedIn(FeatureExpectationsItsSpec.kt:${offset + 10})",
    return5LazyFeatureInfo = "its.definedIn(FeatureExpectationsItsSpec.kt:${offset + 11})",

    return0NullableFeatureInfo = "its.definedIn(FeatureExpectationsItsSpec.kt:${offset + 14})",
    return1NullableFeatureInfo = "its.definedIn(FeatureExpectationsItsSpec.kt:${offset + 15})",
    return2NullableFeatureInfo = "its.definedIn(FeatureExpectationsItsSpec.kt:${offset + 16})",
    return3NullableFeatureInfo = "its.definedIn(FeatureExpectationsItsSpec.kt:${offset + 17})",
    return4NullableFeatureInfo = "its.definedIn(FeatureExpectationsItsSpec.kt:${offset + 18})",
    return5NullableFeatureInfo = "its.definedIn(FeatureExpectationsItsSpec.kt:${offset + 19})",

    lazyWithNestedImmediateFeatureInfo =  "its.definedIn(FeatureExpectationsItsSpec.kt:${offset + 32})",
    lazyWithNestedLazyFeatureInfo =  "its.definedIn(FeatureExpectationsItsSpec.kt:${offset + 37})"
) {

    //TODO remove type parameters for `feature` with Kotiln 1.4 including parentheses (make the calls infix again
    companion object {
        //@formatter:off
        const val offset = 79
        val propertyImmediate: F = { this its { nonNullValue } contains("hello") }
        val propertyLazy: F = { its (feature<TestData, String>({ nonNullValue }) { contains("hello") }) }
        val f0Immediate: F = { this its { return0() } contains("hello") }
        val f1Immediate: F = { this its { return1("a") } contains("hello") }
        val f2Immediate: F = { this its { return2("a", 1) } contains("hello") }
        val f3Immediate: F = { this its { return3("a", 1, true) } contains("hello") }
        val f4Immediate: F = { this its { return4("a", 1, true, 1.2) } contains("hello") }
        val f5Immediate: F = { this its { return5("a", 1, true, 1.2, 'b') } contains("hello") }
        val f0Lazy: F = { this its feature<TestData, String>({ return0() }) { contains("hello") } }
        val f1Lazy: F = { this its feature<TestData, String>({ return1("a") }) { contains("hello") } }
        val f2Lazy: F = { this its feature<TestData, String>({ return2("a", 1) }) { contains("hello") } }
        val f3Lazy: F = { this its feature<TestData, String>({ return3("a", 1, true) }) { contains("hello") } }
        val f4Lazy: F = { this its feature<TestData, String>({ return4("a", 1, true, 1.2) }) { contains("hello") } }
        val f5Lazy: F = { this its feature<TestData, String>({ return5("a", 1, true, 1.2, 'b') }) { contains("hello") } }

        val propertyNullableDoesNotHold: F = { its { nullableValue }.toBe(null) }
        val f0NullableDoesNotHold: F = { its { returnNullable0() }.toBe(null) }
        val f1NullableDoesNotHold: F = { its { returnNullable1("a") }.toBe(null) }
        val f2NullableDoesNotHold: F = { its { returnNullable2("a", 1) }.toBe(null) }
        val f3NullableDoesNotHold: F = { its { returnNullable3("a", 1, true) }.toBe(null) }
        val f4NullableDoesNotHold: F = { its{ returnNullable4("a", 1, true, 1.2) }.toBe(null) }
        val f5NullableDoesNotHold: F = { its { returnNullable5("a", 1, true, 1.2, 'b') }.toBe(null) }

        val propertyNullableHolds: F = { its { nullableValue }.notToBeNull { toBe(1) } }
        val f0NullableHolds: F = { its { returnNullable0() }.notToBeNull { toBe(1) } }
        val f1NullableHolds: F = { its { returnNullable1("a") }.notToBeNull { toBe(1) } }
        val f2NullableHolds: F = { its { returnNullable2("a", 1) }.notToBeNull { toBe(1) } }
        val f3NullableHolds: F = { its { returnNullable3("a", 1, true) }.notToBeNull { toBe(1) } }
        val f4NullableHolds: F = { its{ returnNullable4("a", 1, true, 1.2) }.notToBeNull { toBe(1) } }
        val f5NullableHolds: F = { its { returnNullable5("a", 1, true, 1.2, 'b') }.notToBeNull { toBe(1) } }
        //@formatter:on

        val propertyLazyWithNestedImmediate: F = {
            this its feature<TestData, String>({ nonNullValue }) {
                its { length }.toBe(12)
            }
        }
        val propertyLazyWithNestedLazy: F = {
            this its feature<TestData, String>({ nonNullValue }) {
                this its feature<String, Int>({ length }) { toBe(12) }
            }
        }

        val propertyEmptyAssertionCreator: F = { this its feature<TestData, String>({ nonNullValue }) {} }
        val f0EmptyAssertionCreator: F = { this its feature<TestData, String>({ return0() }) {} }
        val f1EmptyAssertionCreator: F = { this its feature<TestData, String>({ return1("a") }) {} }
        val f2EmptyAssertionCreator: F = { this its feature<TestData, String>({ return2("a", 1) }) {} }
        val f3EmptyAssertionCreator: F = { this its feature<TestData, String>({ return3("a", 1, true) }) {} }
        val f4EmptyAssertionCreator: F = { this its feature<TestData, String>({ return4("a", 1, true, 1.2) }) {} }
        val f5EmptyAssertionCreator: F =
            { this its feature<TestData, String>({ return5("a", 1, true, 1.2, 'b') }) {} }
    }
}

