package ch.tutteli.atrium

import ch.tutteli.atrium.reporting.RawString
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
            }.toThrow<AssertionError>().and.message {
                containsDefaultTranslationOf(DescriptionNarrowingAssertion.IS_NOT_NULL)
                contains(RawString.NULL.string)
            }

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
            }, { isNotNull().isLessOrEquals(0) }, { isNotNull { isLessOrEquals(0) } })

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
            }.toThrow<AssertionError>().and.message {
                containsDefaultTranslationOf(DescriptionNarrowingAssertion.IS_A)
                contains(Integer::class.java.name)
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
        }, { isA<Int>().isLessThan(2) }, { isA<Int> { isLessThan(2) } })

        val expectedLessThan = 2
        val actualValue = 5
        checkNarrowingAssertion<Int>("it allows to perform an assertion specific for the subtype which fails", { isAWithAssertion ->
            expect {
                assert(actualValue).isAWithAssertion()
            }.toThrow<AssertionError>().and.message.contains(actualValue, DescriptionNumberAssertion.IS_LESS_THAN.getDefault(), expectedLessThan)
        }, { isA<Int>().isLessThan(expectedLessThan) }, { isA<Int> { isLessThan(expectedLessThan) } })
    }

})
