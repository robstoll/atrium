package ch.tutteli.atrium

import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.spec.checkNarrowingAssertion
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe

object FeatureAssertionsSpec : Spek({
    describe("fun `its`/`property` (feature assertion)") {
        data class TestData(val description: String, val nullableValue: Int?)

        context("it allows to define an assertion for the feature") {
            val nullableDoesNotHold: IAssertionPlant<TestData>.() -> Unit = { its(subject::nullableValue).isNull() }
            val propertyImmediate: IAssertionPlant<TestData>.() -> Unit = { property(subject::description).contains("hello") }
            val propertyLazy: IAssertionPlant<TestData>.() -> Unit = { property(subject::description) { contains("hello") } }
            val propertyNullableDoesNotHold: IAssertionPlant<TestData>.() -> Unit = { property(subject::nullableValue).isNull() }
            checkNarrowingAssertion("it throws an AssertionError if the assertion does not hold", { andWithCheck ->
                expect {
                    assert(TestData("hallo robert", 1)).andWithCheck()
                }.toThrow<AssertionError>()
            }, { its(subject::description).contains("hello") }, { its(subject::description) { contains("hello") } }, "nullable" to nullableDoesNotHold,
                "property immediate" to propertyImmediate, "property lazy" to propertyLazy, "property nullable" to propertyNullableDoesNotHold)

            val nullableHolds: IAssertionPlant<TestData>.() -> Unit = { its(subject::nullableValue).isNotNull() }
            val propertyNullableHolds: IAssertionPlant<TestData>.() -> Unit = { property(subject::nullableValue).isNotNull() }
            checkNarrowingAssertion("it does not throw an exception if the assertion holds", { andWithCheck ->
                assert(TestData("hello robert", 1)).andWithCheck()
            }, { its(subject::description).contains("hello") }, { its(subject::description) { contains("hello") } }, "nullable" to nullableHolds,
                "property immediate" to propertyImmediate, "property lazy" to propertyLazy, "property nullable" to propertyNullableHolds)
        }
    }

})
