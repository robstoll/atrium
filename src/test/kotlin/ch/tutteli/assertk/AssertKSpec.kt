package ch.tutteli.assertk

import ch.tutteli.assertk.assertions.IsAAssertion
import ch.tutteli.assertk.creating.IAssertionPlant
import ch.tutteli.assertk.verbs.assert.assert
import ch.tutteli.assertk.verbs.expect.expect
import org.jetbrains.spek.api.Spek

class AssertKSpec : Spek({
    describe("fun isA") {
        val immediateEvaluation = "in case of immediate evaluation"
        val lazyEvaluation = "in case of lazy evaluation"

        group("it throws an AssertionError if the subject is not of the given type") {
            val immediate: (IAssertionPlant<String>.() -> Unit) = { isA<Int>() }
            val lazy: (IAssertionPlant<String>.() -> Unit) = { isA<Int> {} }
            mapOf(immediateEvaluation to immediate, lazyEvaluation to lazy).forEach { description, isA ->
                check(description) {
                    expect {
                        assert("hello").isA()
                    }.toThrow<AssertionError> {
                        message.contains(IsAAssertion.MESSAGE_DESCRIPTION, Integer::class.java.simpleName)
                    }
                }
            }
        }

        group("it does not throw an AssertionError if the subject is the same type") {
            val immediate: (IAssertionPlant<String>.() -> Unit) = { isA<String>() }
            val lazy: (IAssertionPlant<String>.() -> Unit) = { isA<String> {} }
            mapOf(immediateEvaluation to immediate, lazyEvaluation to lazy).forEach { description, isA ->
                check(description) {
                    assert("hello").isA()
                }
            }
        }


        group("it does not throw an AssertionError if the subject is a subtype") {
            val immediate: (IAssertionPlant<String>.() -> Unit) = { isA<CharSequence>() }
            val lazy: (IAssertionPlant<String>.() -> Unit) = { isA<CharSequence> {} }
            mapOf(immediateEvaluation to immediate, lazyEvaluation to lazy).forEach { description, isA ->
                check(description) {
                    assert("hello").isA()
                }
            }
        }

        group("it throws an AssertionError if the subject is a supertype") {
            open class A
            class B : A()

            val immediate: (IAssertionPlant<A>.() -> Unit) = { isA<B>() }
            val lazy: (IAssertionPlant<A>.() -> Unit) = { isA<B> {} }
            mapOf(immediateEvaluation to immediate, lazyEvaluation to lazy).forEach { description, isA ->
                check(description) {
                    expect {
                        assert(A()).isA()
                    }.toThrow<AssertionError> {
                        message.contains(A::class.java.name, IsAAssertion.MESSAGE_DESCRIPTION, B::class.java.name)
                    }
                }
            }
        }

        group("allows to perform an assertion specific for the subtype which holds") {
            val immediate: (IAssertionPlant<Any>.() -> Unit) = { isA<Int>().isSmallerThan(2) }
            val lazy: (IAssertionPlant<Any>.() -> Unit) = { isA<Int> { isSmallerThan(2) } }
            mapOf(immediateEvaluation to immediate, lazyEvaluation to lazy).forEach { description, isAWithAssertion ->
                check(description) {
                    assert(1).isAWithAssertion()
                }
            }
        }

        group("allows to perform an assertion specific for the subtype which fails") {
            val expectedSmallerThan = 2
            val actualValue = 5
            val immediate: (IAssertionPlant<Any>.() -> Unit) = { isA<Int>().isSmallerThan(expectedSmallerThan) }
            val lazy: (IAssertionPlant<Any>.() -> Unit) = { isA<Int> { isSmallerThan(expectedSmallerThan) } }
            mapOf(immediateEvaluation to immediate, lazyEvaluation to lazy).forEach { description, isAWithAssertion ->
                check(description) {
                    expect {
                        assert(actualValue).isAWithAssertion()
                    }.toThrow<AssertionError>().and.message.contains(actualValue, "is smaller than", expectedSmallerThan)
                }
            }
        }
    }
})
