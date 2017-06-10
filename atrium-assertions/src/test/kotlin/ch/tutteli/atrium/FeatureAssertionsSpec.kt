package ch.tutteli.atrium

import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.spec.checkGenericNarrowingAssertion
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe

@Suppress("UNUSED_PARAMETER")
internal data class TestData(val description: String, val nullableValue: Int?) {
    fun return0() = description
    fun return1(arg1: String) = description
    fun return2(arg1: String, arg2: Int?) = description
    fun return3(arg1: String, arg2: Int?, arg3: Boolean) = description
    fun return4(arg1: String, arg2: Int?, arg3: Boolean, arg4: Double) = description
    fun return5(arg1: String, arg2: Int?, arg3: Boolean, arg4: Double, arg5: Char) = description
    fun returnNullable0() = nullableValue
    fun returnNullable1(arg1: String) = nullableValue
    fun returnNullable2(arg1: String, arg2: Int?) = nullableValue
    fun returnNullable3(arg1: String, arg2: Int?, arg3: Boolean) = nullableValue
    fun returnNullable4(arg1: String, arg2: Int?, arg3: Boolean, arg4: Double) = nullableValue
    fun returnNullable5(arg1: String, arg2: Int?, arg3: Boolean, arg4: Double, arg5: Char) = nullableValue
}
private typealias F = IAssertionPlant<TestData>.() -> Unit

object FeatureAssertionsSpec : Spek({

    val itsImmediate: F = { its(subject::description).contains("hello") }
    val itsLazy: F = { its(subject::description) { contains("hello") } }
    val propertyImmediate: F = { property(subject::description).contains("hello") }
    val propertyLazy: F = { property(subject::description) { contains("hello") } }
    val return0ValueImmediate: F = { returnValueOf(subject::return0).contains("hello") }
    val return1ValueImmediate: F = { returnValueOf(subject::return1, "a").contains("hello") }
    val return2ValueImmediate: F = { returnValueOf(subject::return2, "a", 1).contains("hello") }
    val return3ValueImmediate: F = { returnValueOf(subject::return3, "a", 1, true).contains("hello") }
    val return4ValueImmediate: F = { returnValueOf(subject::return4, "a", 1, true, 1.2).contains("hello") }
    val return5ValueImmediate: F = { returnValueOf(subject::return5, "a", 1, true, 1.2, 'b').contains("hello") }
    val return0ValueLazy: F = { returnValueOf(subject::return0) { contains("hello") } }
    val return1ValueLazy: F = { returnValueOf(subject::return1, "a") { contains("hello") } }
    val return2ValueLazy: F = { returnValueOf(subject::return2, "a", 1) { contains("hello") } }
    val return3ValueLazy: F = { returnValueOf(subject::return3, "a", 1, true) { contains("hello") } }
    val return4ValueLazy: F = { returnValueOf(subject::return4, "a", 1, true, 1.2) { contains("hello") } }
    val return5ValueLazy: F = { returnValueOf(subject::return5, "a", 1, true, 1.2, 'b') { contains("hello") } }

    val itsNullableDoesNotHold: F = { its(subject::nullableValue).isNull() }
    val propertyNullableDoesNotHold: F = { property(subject::nullableValue).isNull() }
    val return0ValueNullableDoesNotHold: F = { returnValueOf(subject::returnNullable0).isNull() }
    val return1ValueNullableDoesNotHold: F = { returnValueOf(subject::returnNullable1, "a").isNull() }
    val return2ValueNullableDoesNotHold: F = { returnValueOf(subject::returnNullable2, "a", 1).isNull() }
    val return3ValueNullableDoesNotHold: F = { returnValueOf(subject::returnNullable3, "a", 1, true).isNull() }
    val return4ValueNullableDoesNotHold: F = { returnValueOf(subject::returnNullable4, "a", 1, true, 1.2).isNull() }
    val return5ValueNullableDoesNotHold: F = { returnValueOf(subject::returnNullable5, "a", 1, true, 1.2, 'b').isNull() }

    val itsNullableHolds: F = { its(subject::nullableValue).isNotNull() }
    val propertyNullableHolds: F = { property(subject::nullableValue).isNotNull() }
    val return0ValueNullableHolds: F = { returnValueOf(subject::returnNullable0).isNotNull() }
    val return1ValueNullableHolds: F = { returnValueOf(subject::returnNullable1, "a").isNotNull() }
    val return2ValueNullableHolds: F = { returnValueOf(subject::returnNullable2, "a", 1).isNotNull() }
    val return3ValueNullableHolds: F = { returnValueOf(subject::returnNullable3, "a", 1, true).isNotNull() }
    val return4ValueNullableHolds: F = { returnValueOf(subject::returnNullable4, "a", 1, true, 1.2).isNotNull() }
    val return5ValueNullableHolds: F = { returnValueOf(subject::returnNullable5, "a", 1, true, 1.2, 'b').isNotNull() }

    val functions = arrayOf(
        "`its` immediate" to itsImmediate, "`its` lazy" to itsLazy,
        "`property` immediate" to propertyImmediate, "`property` lazy" to propertyLazy,
        "`returnValueOf` without arguments and immediate" to return0ValueImmediate,
        "`returnValueOf` with 1 argument and immediate" to return1ValueImmediate,
        "`returnValueOf` with 2 arguments and immediate" to return2ValueImmediate,
        "`returnValueOf` with 3 arguments and immediate" to return3ValueImmediate,
        "`returnValueOf` with 4 arguments and immediate" to return4ValueImmediate,
        "`returnValueOf` with 5 arguments and immediate" to return5ValueImmediate,
        "`returnValueOf` without arguments and lazy" to return0ValueLazy,
        "`returnValueOf` with 1 argument and lazy" to return1ValueLazy,
        "`returnValueOf` with 2 arguments and lazy" to return2ValueLazy,
        "`returnValueOf` with 3 arguments and lazy" to return3ValueLazy,
        "`returnValueOf` with 4 arguments and lazy" to return4ValueLazy,
        "`returnValueOf` with 5 arguments and lazy" to return5ValueLazy
    )

    describe("different feature assertion functions") {
        checkGenericNarrowingAssertion("it throws an AssertionError if the assertion does not hold", { andWithCheck ->

            expect {
                assert(TestData("hallo robert", 1)).andWithCheck()
            }.toThrow<AssertionError>()

        }, *functions,
            "`its` nullable" to itsNullableDoesNotHold,
            "`property` nullable" to propertyNullableDoesNotHold,
            "`returnValueOf` without argument and nullable" to return0ValueNullableDoesNotHold,
            "`returnValueOf` with 1 argument and nullable" to return1ValueNullableDoesNotHold,
            "`returnValueOf` with 2 arguments and nullable" to return2ValueNullableDoesNotHold,
            "`returnValueOf` with 3 arguments and nullable" to return3ValueNullableDoesNotHold,
            "`returnValueOf` with 4 arguments and nullable" to return4ValueNullableDoesNotHold,
            "`returnValueOf` with 5 arguments and nullable" to return5ValueNullableDoesNotHold
        )

        checkGenericNarrowingAssertion("it does not throw an exception if the assertion holds", { andWithCheck ->

            assert(TestData("hello robert", 1)).andWithCheck()

        }, *functions,
            "`its` nullable" to itsNullableHolds,
            "`property` nullable" to propertyNullableHolds,
            "`returnValueOf` without argument and nullable" to return0ValueNullableHolds,
            "`returnValueOf` with 1 argument and nullable" to return1ValueNullableHolds,
            "`returnValueOf` with 2 arguments and nullable" to return2ValueNullableHolds,
            "`returnValueOf` with 3 arguments and nullable" to return3ValueNullableHolds,
            "`returnValueOf` with 4 arguments and nullable" to return4ValueNullableHolds,
            "`returnValueOf` with 5 arguments and nullable" to return5ValueNullableHolds
        )
    }

})
