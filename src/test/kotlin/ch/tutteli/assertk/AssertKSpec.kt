package ch.tutteli.assertk

import ch.tutteli.assertk.assertions.IsAAssertion
import ch.tutteli.assertk.assertions.IsNotNullAssertion
import ch.tutteli.assertk.creating.IAssertionPlant
import ch.tutteli.assertk.verbs.assert.assert
import ch.tutteli.assertk.verbs.expect.expect
import org.jetbrains.spek.api.Spek

class AssertKSpec : Spek({
    describe("fun isNotNull") {
        checkNarrowingNullableAssertion<Int?>("it throws an AssertionError if the subject is null", { isNotNull ->
            expect {
                val i: Int? = null
                assert(i).isNotNull()
            }.toThrow<AssertionError>().and.message.contains("null", IsNotNullAssertion.MESSAGE_DESCRIPTION)
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
                message.contains(IsAAssertion.MESSAGE_DESCRIPTION, Integer::class.java.simpleName)
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
                message.contains(A::class.java.name, IsAAssertion.MESSAGE_DESCRIPTION, B::class.java.name)
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

    describe("fun `and` (feature assertion)") {
        data class TestData(val description: String, val nullableValue: Int?)

        context("it allows to define an assertion for the feature") {
            val nullableDoesNotHold: IAssertionPlant<TestData>.() -> Unit = { and(subject::nullableValue).isNull() }
            checkNarrowingAssertion("it throws an AssertionError if the assertion does not hold", { andWithCheck ->
                expect {
                    assert(TestData("hallo robert", 1)).andWithCheck()
                }.toThrow<AssertionError>()
            }, { and(subject::description).contains("hello") }, { and(subject::description) { contains("hello") } }, "nullable" to nullableDoesNotHold)

            val nullableHolds: IAssertionPlant<TestData>.() -> Unit = { and(subject::nullableValue).isNotNull() }
            checkNarrowingAssertion("it does not throw an exception if the assertion holds", { andWithCheck ->
                assert(TestData("hello robert", 1)).andWithCheck()
            }, { and(subject::description).contains("hello") }, { and(subject::description) { contains("hello") } }, "nullable" to nullableHolds)
        }
    }

    describe("fun `message` (for Throwable)") {
        checkNarrowingAssertion<Throwable>("it throws an AssertionError if the ${Throwable::message.name} is null", { message ->
            val throwable = IllegalArgumentException()
            expect {
                assert(throwable).message()
            }.toThrow<AssertionError>()
        }, { message }, { message {} })


        context("it allows to define an assertion for the ${Throwable::message.name} if it is not null") {
            val throwable = IllegalArgumentException("oh no")
            checkNarrowingAssertion<Throwable>("it throws an AssertionError if the assertion does not hold", { messageWithCheck ->
                expect {
                    assert(throwable).messageWithCheck()
                }.toThrow<AssertionError>()
            }, { message.contains("hello") }, { message { contains("hello") } })

            checkNarrowingAssertion<Throwable>("it does not throw an exception if the assertion holds", { messageWithCheck ->
                assert(throwable).messageWithCheck()
            }, { message.contains("oh") }, { message { contains("oh") } })
        }
    }
})
