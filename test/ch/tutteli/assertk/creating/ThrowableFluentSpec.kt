package ch.tutteli.assertk.creating

import ch.tutteli.assertk.*
import ch.tutteli.assertk.assertions.IAssertion
import ch.tutteli.assertk.checking.IAssertionChecker
import ch.tutteli.assertk.verbs.expect.expect
import com.nhaarman.mockito_kotlin.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it

class ThrowableFluentSpec : Spek({

    it("throws an AssertionError when no exception occurs") {
        expect {
            expect {
                /* no exception occurs */
            }.toThrow<IllegalArgumentException>()
        }.toThrow<AssertionError> {
            and.message.contains("exception was", "null")
        }
    }

    it("throws an AssertionError when the wrong exception occurs") {
        expect {
            expect {
                throw UnsupportedOperationException()
            }.toThrow<IllegalArgumentException>()
        }.toThrow<AssertionError> {
            and(subject::message).isNotNull().contains("exception was", UnsupportedOperationException::class.java.name)
        }
    }
    it("does not throw if the correct exception is thrown") {
        expect {
            throw IllegalArgumentException("hello")
        }.toThrow<IllegalArgumentException>().and.message.toBe("hello")
    }

    context("dependencies") {
        inCaseOf("the expected exception is thrown") {
            val assertionChecker = mock<IAssertionChecker>()
            val assertionVerb = "assertionVerb"
            val subject = IllegalArgumentException()
            val fluent = ThrowableFluent(assertionVerb, subject, assertionChecker)
            val throwable = fluent.toThrow<IllegalArgumentException>()
            it("uses the given AssertionChecker to check that the correct exception is thrown") {
                verify(assertionChecker).check(eq(assertionVerb), eq(subject), any<List<IAssertion>>())
            }

            it("uses the given AssertionChecker to check additional assertions") {
                throwable.toBe(subject)
                verify(assertionChecker, times(2)).check(eq(assertionVerb), eq(subject), any<List<IAssertion>>())
            }
        }

        inCaseOf("no exception is thrown") {
            val assertionError = AssertionError()
            val assertionChecker = mock<IAssertionChecker> {
                on { fail(any<String>(), any(), any<IAssertion>()) }.doThrow(assertionError)
            }
            val assertionVerb = "assertionVerb"
            val subject: IllegalArgumentException? = null
            val fluent = ThrowableFluent(assertionVerb, subject, assertionChecker)
            it("uses the given AssertionChecker to report the failure") {
                expect {
                    fluent.toThrow<IllegalArgumentException>()
                }.toThrow<AssertionError>().and.toBe(assertionError)
                verify(assertionChecker).fail(eq(assertionVerb), eq(IllegalArgumentException::class.java), any<IAssertion>())
            }
        }

        inCaseOf("the wrong exception is thrown") {
            val assertionError = AssertionError()
            val assertionChecker = mock<IAssertionChecker> {
                on { fail(any<String>(), any(), any<IAssertion>()) }.doThrow(assertionError)
            }
            val assertionVerb = "assertionVerb"
            val subject = UnsupportedOperationException()
            val fluent = ThrowableFluent(assertionVerb, subject, assertionChecker)
            it("uses the given AssertionChecker to report the failure") {
                expect {
                    fluent.toThrow<IllegalArgumentException>()
                }.toThrow<AssertionError>().and.toBe(assertionError)
                verify(assertionChecker).fail(eq(assertionVerb), eq(IllegalArgumentException::class.java), any<IAssertion>())
            }
        }
        val assertionChecker = mock<IAssertionChecker>()
        val assertionVerb = "assertionVerb"
        val subject: IllegalArgumentException? = null
        val fluent = ThrowableFluent(assertionVerb, subject, assertionChecker)

        inCaseOf("using the immediate evaluating signature"){
            it("throws an IllegalStateException, if the checker does not throw an AssertionError even though no exception was thrown") {
                expect {
                    fluent.toThrow<IllegalArgumentException>()
                }.toThrow<IllegalStateException>().and.message.contains("should throw an exception")
            }
        }

        inCaseOf("using the lazy evaluating signature"){
            it("throws an IllegalStateException, if the checker does not throw an AssertionError even though no exception was thrown") {
                expect {
                    fluent.toThrow<IllegalArgumentException>{}
                }.toThrow<IllegalStateException>().and.message.contains("should throw an exception")
            }
        }

    }

})
