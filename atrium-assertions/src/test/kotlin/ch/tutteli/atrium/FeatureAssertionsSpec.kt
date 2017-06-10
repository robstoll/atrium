package ch.tutteli.atrium

import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.spec.checkGenericNarrowingAssertion
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe

object FeatureAssertionsSpec : Spek({
    data class TestData(val description: String, val nullableValue: Int?) {
        fun getDescr() = description
        fun getValue() = nullableValue
    }

    val itsImmediate: IAssertionPlant<TestData>.() -> Unit = { its(subject::description).contains("hello") }
    val itsLazy: IAssertionPlant<TestData>.() -> Unit = { its(subject::description) { contains("hello") } }
    val propertyImmediate: IAssertionPlant<TestData>.() -> Unit = { property(subject::description).contains("hello") }
    val propertyLazy: IAssertionPlant<TestData>.() -> Unit = { property(subject::description) { contains("hello") } }
    val returnValueImmediate: IAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::getDescr).contains("hello") }
    val returnValueLazy: IAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::getDescr) { contains("hello") } }

    val itsNullableDoesNotHold: IAssertionPlant<TestData>.() -> Unit = { its(subject::nullableValue).isNull() }
    val propertyNullableDoesNotHold: IAssertionPlant<TestData>.() -> Unit = { property(subject::nullableValue).isNull() }
    val returnValueNullableDoesNotHold: IAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::getValue).isNull() }

    val itsNullableHolds: IAssertionPlant<TestData>.() -> Unit = { its(subject::nullableValue).isNotNull() }
    val propertyNullableHolds: IAssertionPlant<TestData>.() -> Unit = { property(subject::nullableValue).isNotNull() }
    val returnValueNullableHolds: IAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::getValue).isNotNull() }

    val functions = arrayOf(
        "`its` immediate" to itsImmediate, "`its` lazy" to itsLazy,
        "`property` immediate" to propertyImmediate, "`property` lazy" to propertyLazy,
        "`returnValueOf` immediate" to returnValueImmediate, "`returnValueOf` lazy" to returnValueLazy
    )

    describe("different feature assertion functions") {
        checkGenericNarrowingAssertion("it throws an AssertionError if the assertion does not hold", { andWithCheck ->

            expect {
                assert(TestData("hallo robert", 1)).andWithCheck()
            }.toThrow<AssertionError>()

        }, *functions,
            "`its` nullable" to itsNullableDoesNotHold,
            "`property` nullable" to propertyNullableDoesNotHold,
            "`returnValueOf`" to returnValueNullableDoesNotHold)

        checkGenericNarrowingAssertion("it does not throw an exception if the assertion holds", { andWithCheck ->

            assert(TestData("hello robert", 1)).andWithCheck()

        }, *functions,
            "`its` nullable" to itsNullableHolds,
            "`property` nullable" to propertyNullableHolds,
            "`returnValueOf`" to returnValueNullableHolds)
    }

})
