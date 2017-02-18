package ch.tutteli.assertk

import com.nhaarman.mockito_kotlin.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it

class ThrowableFluentSpec : Spek({


    describe("immediate evaluation") {
        it("throws an AssertionError when no exception occurs") {
            expect {
                expect {
                    /* no exception occurs */
                }.toThrow<IllegalArgumentException>()
            }.toThrow<AssertionError> {
                //TODO message contains 'exception was not thrown'
            }
        }

        it("throws an AssertionError when the wrong exception occurs") {
            expect {
                expect {
                    throw UnsupportedOperationException()
                }.toThrow<IllegalArgumentException>()
            }.toThrow<AssertionError> {
                //TODO message contains 'exception was: Unsupported'
            }
        }
        it("does not throw if the correct exception is thrown") {
            expect {
                throw IllegalArgumentException()
            }.toThrow<IllegalArgumentException>()
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

            it("throws an IllegalStateException if the checker does not throw an exception when calling '${IAssertionChecker::fail.name}'") {
                expect {
                    val assertionChecker = mock<IAssertionChecker>()
                    val assertionVerb = "assertionVerb"
                    val subject: IllegalArgumentException? = null
                    val fluent = ThrowableFluent(assertionVerb, subject, assertionChecker)
                    fluent.toThrow<IllegalArgumentException>()
                }.toThrow<IllegalStateException> {
                    //TODO check message
                }
            }
        }
    }
})
