package ch.tutteli.atrium

import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.spec.checkNarrowingAssertion
import ch.tutteli.atrium.spec.checkNarrowingNullableAssertion
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe

object NarrowingAssertionsSpec : Spek({
    describe("fun isNotNull") {

        checkNarrowingNullableAssertion<Int?>("it throws an AssertionError if the subject is null", { isNotNull ->
            expect {
                val i: Int? = null
                assert(i).isNotNull()
            }.toThrow<AssertionError>().and.message.contains("null", "is not")
        }, { isNotNull() }, { isNotNull {} })

        checkNarrowingNullableAssertion<Int?>("it does not throw an Exception if the subject is not null", { isNotNull ->
            val i: Int? = 1
            assert(i).isNotNull()
        }, { isNotNull() }, { isNotNull {} })

        context("it allows to define an assertion on the subject in case it is not null") {
            checkNarrowingNullableAssertion<Int?>("it throws an AssertionError if the assertion does not hold", { isNotNullWithCheck ->
                expect {
                    val i: Int? = 1
                    assert(i).isNotNullWithCheck()
                }.toThrow<AssertionError>()
            }, { isNotNull().isSmallerOrEquals(0) }, { isNotNull { isSmallerOrEquals(0) } })

            checkNarrowingNullableAssertion<Int?>("it does not throw an Exception if assertion holds", { isNotNullWithCheck ->
                val i: Int? = 1
                assert(i).isNotNullWithCheck()
            }, { isNotNull().isGreaterOrEquals(1) }, { isNotNull { isGreaterOrEquals(0) } })
        }
    }

    describe("fun isA") {

        checkNarrowingAssertion<String>("it throws an AssertionError if the subject is not of the given type", { isA ->
            expect {
                assert("hello").isA()
            }.toThrow<AssertionError> {
                message.contains("is type or sub-type of", Int::class.java.name)
            }
        }, { isA<Int>() }, { isA<Int> {} })

        checkNarrowingAssertion<String>("it does not throw an AssertionError if the subject is the same type", { isA ->
            assert("hello").isA()
        }, { isA<String>() }, { isA<String> {} })


        checkNarrowingAssertion<String>("it does not throw an AssertionError if the subject is a subtype", { isA ->
            assert("hello").isA()
        }, { isA<CharSequence>() }, { isA<CharSequence> {} })

        open class A
        class B : A()
        checkNarrowingAssertion<A>("it throws an AssertionError if the subject is a supertype", { isA ->
            expect {
                assert(A()).isA()
            }.toThrow<AssertionError> {
                message.contains(A::class.java.name, "is type or sub-type of", B::class.java.name)
            }
        }, { isA<B>() }, { isA<B> {} })

        checkNarrowingAssertion<Int>("it allows to perform an assertion specific for the subtype which holds", { isAWithAssertion ->
            assert(1).isAWithAssertion()
        }, { isA<Int>().isSmallerThan(2) }, { isA<Int> { isSmallerThan(2) } })

        val expectedSmallerThan = 2
        val actualValue = 5
        checkNarrowingAssertion<Int>("it allows to perform an assertion specific for the subtype which fails", { isAWithAssertion ->
            expect {
                assert(actualValue).isAWithAssertion()
            }.toThrow<AssertionError>().and.message.contains(actualValue, "is smaller than", expectedSmallerThan)
        }, { isA<Int>().isSmallerThan(expectedSmallerThan) }, { isA<Int> { isSmallerThan(expectedSmallerThan) } })
    }

    describe("fun `its` (feature assertion)") {
        data class TestData(val description: String, val nullableValue: Int?)

        context("it allows to define an assertion for the feature") {
            val nullableDoesNotHold: IAssertionPlant<TestData>.() -> Unit = { its(subject::nullableValue).isNull() }
            checkNarrowingAssertion("it throws an AssertionError if the assertion does not hold", { andWithCheck ->
                expect {
                    assert(TestData("hallo robert", 1)).andWithCheck()
                }.toThrow<AssertionError>()
            }, { its(subject::description).contains("hello") }, { its(subject::description) { contains("hello") } }, "nullable" to nullableDoesNotHold)

            val nullableHolds: IAssertionPlant<TestData>.() -> Unit = { its(subject::nullableValue).isNotNull() }
            checkNarrowingAssertion("it does not throw an exception if the assertion holds", { andWithCheck ->
                assert(TestData("hello robert", 1)).andWithCheck()
            }, { its(subject::description).contains("hello") }, { its(subject::description) { contains("hello") } }, "nullable" to nullableHolds)
        }
    }


})
