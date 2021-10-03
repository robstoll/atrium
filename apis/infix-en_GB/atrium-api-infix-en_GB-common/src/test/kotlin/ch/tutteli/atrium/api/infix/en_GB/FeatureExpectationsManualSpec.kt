package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.workaround.it
import ch.tutteli.atrium.specs.integration.TestData


class FeatureExpectationsManualSpec : ch.tutteli.atrium.specs.integration.FeatureExpectationsSpec(
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

    isAbleToEvaluateDescription = false
) {
    //TODO remove type parameters for `of` with Kotiln 1.4 including parentheses (make the calls infix again
    companion object {
        //@formatter:off
        val propertyImmediate: F = { (it feature { f("nonNullValue", it.nonNullValue) }).toContain("hello") }

        val propertyLazy: F = { it feature(of<TestData, String>({ f("nonNullValue", it.nonNullValue) }) { it toContain "hello" }) }
        val f0Immediate: F = { (it feature { f("return0()", it.return0()) }).toContain("hello") }
        val f1Immediate: F = { (it feature { f("return1(\"a\")", it.return1("a")) }).toContain("hello") }
        val f2Immediate: F = { (it feature { f("return2(\"a\", 1)", it.return2("a", 1)) }).toContain("hello") }
        val f3Immediate: F = { (it feature { f("return3(\"a\", 1, true)", it.return3("a", 1, true)) }).toContain("hello") }
        val f4Immediate: F = { (it feature { f("return4(\"a\", 1, true, 1.2)", it.return4("a", 1, true, 1.2)) }).toContain("hello") }
        val f5Immediate: F = { (it feature { f("return5(\"a\", 1, true, 1.2, 'b')", it.return5("a", 1, true, 1.2, 'b')) }).toContain("hello") }
        val f0Lazy: F = { it feature of<TestData, String>({ f("return0()", it.return0()) }) { it toContain "hello" } }
        val f1Lazy: F = { it feature of<TestData, String>({ f("return1(\"a\")", it.return1("a")) }) { it toContain "hello" } }
        val f2Lazy: F = { it feature of<TestData, String>({ f("return2(\"a\", 1)", it.return2("a", 1)) }) { it toContain "hello" } }
        val f3Lazy: F = { it feature of<TestData, String>({ f("return3(\"a\", 1, true)", it.return3("a", 1, true)) }) { it toContain "hello" } }
        val f4Lazy: F = { it feature of<TestData, String>({ f("return4(\"a\", 1, true, 1.2)", it.return4("a", 1, true, 1.2)) }) { it toContain "hello" } }
        val f5Lazy: F = { it feature of<TestData, String>({ f("return5(\"a\", 1, true, 1.2, 'b')", it.return5("a", 1, true, 1.2, 'b')) }) { it toContain "hello" } }

        val propertyNullableDoesNotHold: F = { it feature { f("nullableValue", it.nullableValue) } toEqual null }
        val f0NullableDoesNotHold: F = { it feature { f("returnNullable0()", it.returnNullable0()) } toEqual null }
        val f1NullableDoesNotHold: F = { it feature { f("returnNullable1(\"a\")", it.returnNullable1("a")) } toEqual null }
        val f2NullableDoesNotHold: F = { it feature { f("returnNullable2(\"a\", 1)", it.returnNullable2("a", 1)) } toEqual null }
        val f3NullableDoesNotHold: F = { it feature { f("returnNullable3(\"a\", 1, true)", it.returnNullable3("a", 1, true)) } toEqual null }
        val f4NullableDoesNotHold: F = { it feature { f("returnNullable4(\"a\", 1, true, 1.2)", it.returnNullable4("a", 1, true, 1.2)) } toEqual null }
        val f5NullableDoesNotHold: F = { it feature { f("returnNullable5(\"a\", 1, true, 1.2, 'b')", it.returnNullable5("a", 1, true, 1.2, 'b')) } toEqual null }

        val propertyNullableHolds: F = { it feature { f("nullableValue", it.nullableValue) } notToEqualNull { it toEqual 1 } }
        val f0NullableHolds: F = { it feature { f("returnNullable0()", it.returnNullable0()) } notToEqualNull { it toEqual 1 } }
        val f1NullableHolds: F = { it feature { f("returnNullable1(\"a\")", it.returnNullable1("a")) } notToEqualNull { it toEqual 1 } }
        val f2NullableHolds: F = { it feature { f("returnNullable2(\"a\", 1)", it.returnNullable2("a", 1)) } notToEqualNull { it toEqual 1 } }
        val f3NullableHolds: F = { it feature { f("returnNullable3(\"a\", 1, true)", it.returnNullable3("a", 1, true)) } notToEqualNull { it toEqual 1 } }
        val f4NullableHolds: F = { it feature { f("returnNullable4(\"a\", 1, true, 1.2)", it.returnNullable4("a", 1, true, 1.2)) } notToEqualNull { it toEqual 1 } }
        val f5NullableHolds: F = { it feature { f("returnNullable5(\"a\", 1, true, 1.2, 'b')", it.returnNullable5("a", 1, true, 1.2, 'b')) } notToEqualNull { it toEqual 1 } }
        //@formatter:on

        val propertyLazyWithNestedImmediate: F = {
            it feature of<TestData, String>({ f("nonNullValue", it.nonNullValue) }) {
                feature { f("length", it.length) } toEqual 12
            }
        }
        val propertyLazyWithNestedLazy: F = {
            it feature of<TestData, String>({ f("nonNullValue", it.nonNullValue) }) {
                feature { f("length", it.length) } it { it toEqual 12 }
            }
        }

        val propertyEmptyAssertionCreator: F =
            { it feature of<TestData, String>({ f("nonNullValue", it.nonNullValue) }) {} }
        val f0EmptyAssertionCreator: F = { it feature of<TestData, String>({ f("return0()", it.return0()) }) {} }
        val f1EmptyAssertionCreator: F =
            { it feature of<TestData, String>({ f("return1(\"a\")", it.return1("a")) }) {} }
        val f2EmptyAssertionCreator: F =
            { it feature of<TestData, String>({ f("return2(\"a\", 1)", it.return2("a", 1)) }) {} }
        val f3EmptyAssertionCreator: F =
            { it feature of<TestData, String>({ f("return3(\"a\", 1, true)", it.return3("a", 1, true)) }) {} }
        val f4EmptyAssertionCreator: F =
            { it feature of<TestData, String>({ f("return4(\"a\", 1, true, 1.2)", it.return4("a", 1, true, 1.2)) }) {} }
        val f5EmptyAssertionCreator: F =
            {
                it feature of<TestData, String>({
                    f("return5(\"a\", 1, true, 1.2, 'b')", it.return5("a", 1, true, 1.2, 'b'))
                }) {}
            }
    }
}

