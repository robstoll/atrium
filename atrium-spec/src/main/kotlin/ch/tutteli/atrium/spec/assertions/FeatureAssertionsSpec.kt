package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.contains
import ch.tutteli.atrium.api.cc.en_UK.message
import ch.tutteli.atrium.api.cc.en_UK.toThrow
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.checkGenericNarrowingAssertion
import ch.tutteli.atrium.spec.prefixedDescribe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.include

@Suppress("UNUSED_PARAMETER")
data class TestData(val description: String, val nullableValue: Int?) {
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
private typealias F = AssertionPlant<TestData>.() -> Unit

abstract class FeatureAssertionsSpec(
    verbs: AssertionVerbFactory,

    propertyImmediate: F,
    propertyLazy: F,
    return0ValueImmediate: F,
    return1ValueImmediate: F,
    return2ValueImmediate: F,
    return3ValueImmediate: F,
    return4ValueImmediate: F,
    return5ValueImmediate: F,
    return0ValueLazy: F,
    return1ValueLazy: F,
    return2ValueLazy: F,
    return3ValueLazy: F,
    return4ValueLazy: F,
    return5ValueLazy: F,

    propertyNullableDoesNotHold: F,
    return0ValueNullableDoesNotHold: F,
    return1ValueNullableDoesNotHold: F,
    return2ValueNullableDoesNotHold: F,
    return3ValueNullableDoesNotHold: F,
    return4ValueNullableDoesNotHold: F,
    return5ValueNullableDoesNotHold: F,

    propertyNullableHolds: F,
    return0ValueNullableHolds: F,
    return1ValueNullableHolds: F,
    return2ValueNullableHolds: F,
    return3ValueNullableHolds: F,
    return4ValueNullableHolds: F,
    return5ValueNullableHolds: F,

    itsLazyWithNestedImmediate: F,
    itsLazyWithNestedLazy: F,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit)
        = prefixedDescribe(describePrefix, description, body)

    val assert: (TestData) -> AssertionPlant<TestData> = verbs::checkImmediately
    val expect = verbs::checkException

    val functions = arrayOf(
        Triple("`property` immediate", propertyImmediate, TestData::description.name),
        Triple("`property` lazy", propertyLazy, TestData::description.name),
        Triple("`returnValueOf` without arguments and immediate", return0ValueImmediate, "${TestData::return0.name}()"),
        Triple("`returnValueOf` with 1 argument and immediate", return1ValueImmediate, "${TestData::return1.name}(\"a\")"),
        Triple("`returnValueOf` with 2 arguments and immediate", return2ValueImmediate, "${TestData::return2.name}(\"a\", 1)"),
        Triple("`returnValueOf` with 3 arguments and immediate", return3ValueImmediate, "${TestData::return3.name}(\"a\", 1, true)"),
        Triple("`returnValueOf` with 4 arguments and immediate", return4ValueImmediate, "${TestData::return4.name}(\"a\", 1, true, 1.2)"),
        Triple("`returnValueOf` with 5 arguments and immediate", return5ValueImmediate, "${TestData::return5.name}(\"a\", 1, true, 1.2, 'b')"),
        Triple("`returnValueOf` without arguments and lazy", return0ValueLazy, "${TestData::return0.name}()"),
        Triple("`returnValueOf` with 1 argument and lazy", return1ValueLazy, "${TestData::return1.name}(\"a\")"),
        Triple("`returnValueOf` with 2 arguments and lazy", return2ValueLazy, "${TestData::return2.name}(\"a\", 1)"),
        Triple("`returnValueOf` with 3 arguments and lazy", return3ValueLazy, "${TestData::return3.name}(\"a\", 1, true)"),
        Triple("`returnValueOf` with 4 arguments and lazy", return4ValueLazy, "${TestData::return4.name}(\"a\", 1, true, 1.2)"),
        Triple("`returnValueOf` with 5 arguments and lazy", return5ValueLazy, "${TestData::return5.name}(\"a\", 1, true, 1.2, 'b')")
    )
    val nullableFailingFunctions = arrayOf(
        Triple("`property` isNull", propertyNullableDoesNotHold, TestData::nullableValue.name),
        Triple("`returnValueOf` without argument and isNull", return0ValueNullableDoesNotHold, "${TestData::returnNullable0.name}()"),
        Triple("`returnValueOf` with 1 argument and isNull", return1ValueNullableDoesNotHold, "${TestData::returnNullable1.name}(\"a\")"),
        Triple("`returnValueOf` with 2 arguments and isNull", return2ValueNullableDoesNotHold, "${TestData::returnNullable2.name}(\"a\", 1)"),
        Triple("`returnValueOf` with 3 arguments and isNull", return3ValueNullableDoesNotHold, "${TestData::returnNullable3.name}(\"a\", 1, true)"),
        Triple("`returnValueOf` with 4 arguments and isNull", return4ValueNullableDoesNotHold, "${TestData::returnNullable4.name}(\"a\", 1, true, 1.2)"),
        Triple("`returnValueOf` with 5 arguments and isNull", return5ValueNullableDoesNotHold, "${TestData::returnNullable5.name}(\"a\", 1, true, 1.2, 'b')")
    )
    val nullableHoldsFunctions = arrayOf(
        "`property` isNotNull" to propertyNullableHolds,
        "`returnValueOf` with 1 argument and isNotNull" to return1ValueNullableHolds,
        "`returnValueOf` without argument and isNotNull" to return0ValueNullableHolds,
        "`returnValueOf` with 2 arguments and isNotNull" to return2ValueNullableHolds,
        "`returnValueOf` with 3 arguments and isNotNull" to return3ValueNullableHolds,
        "`returnValueOf` with 4 arguments and isNotNull" to return4ValueNullableHolds,
        "`returnValueOf` with 5 arguments and isNotNull" to return5ValueNullableHolds
    )

    val holdingTestData = TestData("hello robert", 1)
    val failingTestData = TestData("by robert", null)
    include(object : ch.tutteli.atrium.spec.assertions.CheckingAssertionSpec<TestData>(verbs, describePrefix,
        *(functions.map { (description, lambda, _) -> checkingTriple(description, lambda, holdingTestData, failingTestData) }.toTypedArray()),
        *(nullableFailingFunctions.map { (description, lambda, _) -> checkingTriple(description, lambda, failingTestData, holdingTestData) }.toTypedArray()),
        *(nullableHoldsFunctions.map { (description, lambda) -> checkingTriple(description, lambda, holdingTestData, failingTestData) }.toTypedArray()),
        checkingTriple("propertyLazy with nested immediate", itsLazyWithNestedImmediate, holdingTestData, TestData("by robert", 1))
    ) {})

    fun <T> SpecBody.checkGenericNarrowingAssertionWithExceptionMessage(
        description: String, act: (T.() -> Unit) -> Unit, vararg methods: Triple<String, (T.() -> Unit), String>
    ) {
        group(description) {
            methods.forEach { (checkMethod, assertion, stringInExceptionMessage) ->
                test("in case of $checkMethod evaluation") {
                    expect {
                        act(assertion)
                    }.toThrow<AssertionError> { message { contains(stringInExceptionMessage) } }
                }
            }
        }
    }

    prefixedDescribe("different feature assertion functions") {

        checkGenericNarrowingAssertionWithExceptionMessage("it throws an AssertionError if the assertion does not hold", { andWithCheck ->

            assert(TestData("what ever", 1)).andWithCheck()

        }, *functions, *nullableFailingFunctions)

        checkGenericNarrowingAssertion("it does not throw an exception if the assertion holds", { andWithCheck ->

            assert(TestData("hello robert", 1)).andWithCheck()

        }, *functions.map { it.first to it.second }.toTypedArray(), *nullableHoldsFunctions)
    }

    prefixedDescribe("assertion plant which checks immediately; use lazy property which has nested...") {
        test("... immediate feature property") {
            assert(TestData("hello robert", 1)).itsLazyWithNestedImmediate()
        }
        test("... lazy feature property") {
            assert(TestData("hello robert", 1)).itsLazyWithNestedLazy()
        }
    }

})
