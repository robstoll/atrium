package ch.tutteli.atrium

import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.spec.checkGenericNarrowingAssertion
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
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
        Triple("`its` immediate", itsImmediate, TestData::description.name),
        Triple("`its` lazy", itsLazy, TestData::description.name),
        Triple("`property` immediate", propertyImmediate, TestData::description.name),
        Triple("`property` lazy", propertyLazy, TestData::description.name),
        Triple("`returnValueOf` without arguments and immediate", return0ValueImmediate,"${TestData::return0.name}()"),
        Triple("`returnValueOf` with 1 argument and immediate", return1ValueImmediate, "${TestData::return1.name}(\"a\")"),
        Triple("`returnValueOf` with 2 arguments and immediate", return2ValueImmediate, "${TestData::return2.name}(\"a\", 1)"),
        Triple("`returnValueOf` with 3 arguments and immediate", return3ValueImmediate, "${TestData::return3.name}(\"a\", 1, true)"),
        Triple("`returnValueOf` with 4 arguments and immediate", return4ValueImmediate, "${TestData::return4.name}(\"a\", 1, true, 1.2)"),
        Triple("`returnValueOf` with 5 arguments and immediate", return5ValueImmediate, "${TestData::return5.name}(\"a\", 1, true, 1.2, 'b')"),
        Triple("`returnValueOf` without arguments and lazy", return0ValueLazy,"${TestData::return0.name}()"),
        Triple("`returnValueOf` with 1 argument and lazy", return1ValueLazy,  "${TestData::return1.name}(\"a\")"),
        Triple("`returnValueOf` with 2 arguments and lazy", return2ValueLazy, "${TestData::return2.name}(\"a\", 1)"),
        Triple("`returnValueOf` with 3 arguments and lazy", return3ValueLazy, "${TestData::return3.name}(\"a\", 1, true)"),
        Triple("`returnValueOf` with 4 arguments and lazy", return4ValueLazy, "${TestData::return4.name}(\"a\", 1, true, 1.2)"),
        Triple("`returnValueOf` with 5 arguments and lazy", return5ValueLazy, "${TestData::return5.name}(\"a\", 1, true, 1.2, 'b')")
    )

    fun <T> SpecBody.checkGenericNarrowingAssertionWithExceptionMessage(
        description: String, act: (T.() -> Unit) -> Unit, vararg methods: Triple<String, (T.() -> Unit), String>
    ) {
        group(description) {
            methods.forEach { (checkMethod, assertion, stringInExceptionMessage) ->
                test("in case of $checkMethod evaluation") {
                    expect {
                        act(assertion)
                    }.toThrow<AssertionError>().and.message.contains(stringInExceptionMessage)
                }
            }
        }
    }

    describe("different feature assertion functions") {
        checkGenericNarrowingAssertionWithExceptionMessage("it throws an AssertionError if the assertion does not hold", { andWithCheck ->
            assert(TestData("hallo robert", 1)).andWithCheck()
        }, *functions,
            Triple("`its` nullable", itsNullableDoesNotHold, TestData::nullableValue.name),
            Triple("`property` nullable", propertyNullableDoesNotHold, TestData::nullableValue.name),
            Triple("`returnValueOf` without argument and nullable", return0ValueNullableDoesNotHold, "${TestData::returnNullable0.name}()"),
            Triple("`returnValueOf` with 1 argument and nullable", return1ValueNullableDoesNotHold, "${TestData::returnNullable1.name}(\"a\")"),
            Triple("`returnValueOf` with 2 arguments and nullable", return2ValueNullableDoesNotHold, "${TestData::returnNullable2.name}(\"a\", 1)"),
            Triple("`returnValueOf` with 3 arguments and nullable", return3ValueNullableDoesNotHold, "${TestData::returnNullable3.name}(\"a\", 1, true)"),
            Triple("`returnValueOf` with 4 arguments and nullable", return4ValueNullableDoesNotHold, "${TestData::returnNullable4.name}(\"a\", 1, true, 1.2)"),
            Triple("`returnValueOf` with 5 arguments and nullable", return5ValueNullableDoesNotHold, "${TestData::returnNullable5.name}(\"a\", 1, true, 1.2, 'b')")
        )

        checkGenericNarrowingAssertion("it does not throw an exception if the assertion holds", { andWithCheck ->

            assert(TestData("hello robert", 1)).andWithCheck()

        }, *functions.map { it.first to it.second }.toTypedArray(),
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
