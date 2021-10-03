package ch.tutteli.atrium.api.fluent.en_GB


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

    isAbleToEvaluateDescription = true
) {

    companion object {
        //@formatter:off
        val propertyImmediate: F = { feature("nonNullValue") { nonNullValue }.toContain("hello" ) }
        val propertyLazy: F = { feature("nonNullValue", { nonNullValue }) { toContain("hello" ) } }
        val f0Immediate: F = { feature("return0()") { return0() }.toContain("hello" ) }
        val f1Immediate: F = { feature("return1(\"a\")") { return1("a") }.toContain("hello" ) }
        val f2Immediate: F = { feature("return2(\"a\", 1)") { return2("a", 1) }.toContain("hello" ) }
        val f3Immediate: F = { feature("return3(\"a\", 1, true)") { return3("a", 1, true) }.toContain("hello" ) }
        val f4Immediate: F = { feature("return4(\"a\", 1, true, 1.2)") { return4("a", 1, true, 1.2) }.toContain("hello" ) }
        val f5Immediate: F = { feature("return5(\"a\", 1, true, 1.2, 'b')") { return5("a", 1, true, 1.2, 'b') }.toContain("hello" ) }
        val f0Lazy: F = { feature("return0()", { return0() }) { toContain("hello" ) } }
        val f1Lazy: F = { feature("return1(\"a\")", { return1("a") }) { toContain("hello" ) } }
        val f2Lazy: F = { feature("return2(\"a\", 1)", { return2("a", 1) }) { toContain("hello" ) } }
        val f3Lazy: F = { feature("return3(\"a\", 1, true)", { return3("a", 1, true) }) { toContain("hello" ) } }
        val f4Lazy: F = { feature("return4(\"a\", 1, true, 1.2)", { return4("a", 1, true, 1.2) }) { toContain("hello" ) } }
        val f5Lazy: F = { feature("return5(\"a\", 1, true, 1.2, 'b')", { return5("a", 1, true, 1.2, 'b') }) { toContain("hello" ) } }

        val propertyNullableDoesNotHold: F = { feature("nullableValue") { nullableValue }.toEqual(null) }
        val f0NullableDoesNotHold: F = { feature("returnNullable0()") { returnNullable0() }.toEqual(null) }
        val f1NullableDoesNotHold: F = { feature("returnNullable1(\"a\")") { returnNullable1("a") }.toEqual(null) }
        val f2NullableDoesNotHold: F = { feature("returnNullable2(\"a\", 1)") { returnNullable2("a", 1) }.toEqual(null) }
        val f3NullableDoesNotHold: F = { feature("returnNullable3(\"a\", 1, true)") { returnNullable3("a", 1, true) }.toEqual(null) }
        val f4NullableDoesNotHold: F = { feature("returnNullable4(\"a\", 1, true, 1.2)") { returnNullable4("a", 1, true, 1.2) }.toEqual(null) }
        val f5NullableDoesNotHold: F = { feature("returnNullable5(\"a\", 1, true, 1.2, 'b')") { returnNullable5("a", 1, true, 1.2, 'b') }.toEqual(null) }

        val propertyNullableHolds: F = { feature("nullableValue") { nullableValue }.notToEqualNull { toEqual(1) } }
        val f0NullableHolds: F = { feature("returnNullable0()") { returnNullable0() }.notToEqualNull { toEqual(1) } }
        val f1NullableHolds: F = { feature("returnNullable1(\"a\")") { returnNullable1("a") }.notToEqualNull { toEqual(1) } }
        val f2NullableHolds: F = { feature("returnNullable2(\"a\", 1)") { returnNullable2("a", 1) }.notToEqualNull { toEqual(1) } }
        val f3NullableHolds: F = { feature("returnNullable3(\"a\", 1, true)") { returnNullable3("a", 1, true) }.notToEqualNull { toEqual(1) } }
        val f4NullableHolds: F = { feature("returnNullable4(\"a\", 1, true, 1.2)") { returnNullable4("a", 1, true, 1.2) }.notToEqualNull { toEqual(1) } }
        val f5NullableHolds: F = { feature("returnNullable5(\"a\", 1, true, 1.2, 'b')") { returnNullable5("a", 1, true, 1.2, 'b') }.notToEqualNull { toEqual(1) } }
        //@formatter:on

        val propertyLazyWithNestedImmediate: F = {
            feature("nonNullValue", { nonNullValue }) {
                feature("length") { length }.toEqual(12)
            }
        }
        val propertyLazyWithNestedLazy: F = {
            feature("nonNullValue", { nonNullValue }) {
                feature("length", { length }) { toEqual(12) }
            }
        }

        val propertyEmptyAssertionCreator: F = { feature("nonNullValue", { nonNullValue }) {} }
        val f0EmptyAssertionCreator: F = { feature("return0()", { return0() }) {} }
        val f1EmptyAssertionCreator: F = { feature("return1(\"a\")", { return1("a") }) {} }
        val f2EmptyAssertionCreator: F = { feature("return2(\"a\", 1)", { return2("a", 1) }) {} }
        val f3EmptyAssertionCreator: F = { feature("return3(\"a\", 1, true)", { return3("a", 1, true) }) {} }
        val f4EmptyAssertionCreator: F = { feature("return4(\"a\", 1, true, 1.2)", { return4("a", 1, true, 1.2) }) {} }
        val f5EmptyAssertionCreator: F =
            { feature("return5(\"a\", 1, true, 1.2, 'b')", { return5("a", 1, true, 1.2, 'b') }) {} }
    }
}

