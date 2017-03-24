package ch.tutteli.assertk.creating

import ch.tutteli.assertk.*
import ch.tutteli.assertk.assertions.IAssertion
import ch.tutteli.assertk.checking.IAssertionChecker
import ch.tutteli.assertk.verbs.expect.expect
import com.nhaarman.mockito_kotlin.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody

class ThrowableFluentSpec : Spek({

    fun SpecBody.checkToThrow(description: String,
                              act: (ThrowableFluent.() -> Unit) -> Unit,
                              immediate: (ThrowableFluent.() -> Unit),
                              lazy: (ThrowableFluent.() -> Unit)) {
        checkGenericNarrowingAssertion(description, act, immediate, lazy)
    }
    checkToThrow("it throws an AssertionError when no exception occurs", { doToThrow ->
        expect {
            expect {
                /* no exception occurs */
            }.doToThrow()
        }.toThrow<AssertionError> {
            message.contains(ch.tutteli.assertk.creating.ThrowableFluent.NO_EXCEPTION_OCCURRED, "is a", IllegalArgumentException::class.java.name)
        }
    }, { toThrow<IllegalArgumentException>() }, { toThrow<IllegalArgumentException> {} })

    checkToThrow("it throws an AssertionError when the wrong exception occurs", { doToThrow ->
        expect {
            expect {
                throw UnsupportedOperationException()
            }.doToThrow()
        }.toThrow<AssertionError> {
            message.contains(UnsupportedOperationException::class.java.name, "is a", IllegalArgumentException::class.java.name)
        }
    }, { toThrow<IllegalArgumentException>() }, { toThrow<IllegalArgumentException> {} })

    checkToThrow("it allows to define assertions for the Throwable if the correct exception is thrown", { toThrowWithCheck ->
        expect {
            throw IllegalArgumentException("hello")
        }.toThrowWithCheck()
    }, { toThrow<IllegalArgumentException>().and.message.toBe("hello") }, { toThrow<IllegalArgumentException> { and.message.toBe("hello") } })

    val assertionVerb = "assertionVerb"
    class CheckerAndFluent(var checker: IAssertionChecker = mock<IAssertionChecker>(), var fluent: ThrowableFluent = ThrowableFluent("dummy", null, checker))
    context("dependencies") {
        group("in case the expected exception is thrown") {
            val subject = IllegalArgumentException()
            val checkerAndFluent = CheckerAndFluent()
            beforeEachTest {
                checkerAndFluent.checker = mock<IAssertionChecker>()
                checkerAndFluent.fluent = ThrowableFluent(assertionVerb, subject, checkerAndFluent.checker)
            }
            checkToThrow("it uses the given AssertionChecker to check that the correct exception is thrown", { doToThrow ->
                checkerAndFluent.fluent.doToThrow()
                verify(checkerAndFluent.checker).check(eq(assertionVerb), eq(subject), any<List<IAssertion>>())
            }, { toThrow<IllegalArgumentException>() }, { toThrow<IllegalArgumentException> {} })

            checkToThrow("it uses the given AssertionChecker to check that the correct exception is thrown", { toThrowWithCheck ->
                checkerAndFluent.fluent.toThrowWithCheck()
                verify(checkerAndFluent.checker, times(2)).check(eq(assertionVerb), eq(subject), any<List<IAssertion>>())
            }, { toThrow<IllegalArgumentException>().toBe(subject) }, { toThrow<IllegalArgumentException> { toBe(subject) } })
        }

        val assertionError = AssertionError()
        fun setUpCheckerAndFluentThrowingAssertionError(checkerAndFluent: CheckerAndFluent, subject:Throwable?){
            beforeEachTest {
                checkerAndFluent.checker = mock<IAssertionChecker> {
                    on { fail(any<String>(), any(), any<IAssertion>()) }.doThrow(assertionError)
                }
                checkerAndFluent.fluent = ThrowableFluent(assertionVerb, subject, checkerAndFluent.checker)
            }
        }

        group("in case no exception is thrown") {
            val subject: IllegalArgumentException? = null
            val checkerAndFluent = CheckerAndFluent()
            setUpCheckerAndFluentThrowingAssertionError(checkerAndFluent, subject)
            checkToThrow("it uses the given AssertionChecker to report the failure", { toThrowWithCheck ->
                expect {
                    checkerAndFluent.fluent.toThrow<IllegalArgumentException>()
                }.toThrowWithCheck()
                verify(checkerAndFluent.checker).fail(eq(assertionVerb), eq(ThrowableFluent.NO_EXCEPTION_OCCURRED), any<IAssertion>())
            }, { toThrow<AssertionError>().and.toBe(assertionError) }, { toThrow<AssertionError> { toBe(assertionError) } })
        }

        group("in case the wrong exception is thrown") {
            val subject = UnsupportedOperationException()
            val checkerAndFluent = CheckerAndFluent()
            setUpCheckerAndFluentThrowingAssertionError(checkerAndFluent, subject)
            checkToThrow("it uses the given AssertionChecker to report the failure", { toThrowWithCheck ->
                expect {
                    checkerAndFluent.fluent.toThrow<IllegalArgumentException>()
                }.toThrowWithCheck()
                verify(checkerAndFluent.checker).fail(eq(assertionVerb), eq(subject), any<IAssertion>())
            }, { toThrow<AssertionError>().and.toBe(assertionError) }, { toThrow<AssertionError> { toBe(assertionError) } })
        }
        val assertionChecker = mock<IAssertionChecker>()
        val subject: IllegalArgumentException? = null
        val fluent = ThrowableFluent(assertionVerb, subject, assertionChecker)

        checkToThrow("it throws an IllegalStateException, if the checker does not throw an AssertionError even though no exception was thrown", { doToThrow ->
            expect {
                fluent.doToThrow()
            }.toThrow<IllegalStateException>().and.message.contains("should throw an exception")
        }, { toThrow<IllegalArgumentException>() }, { toThrow<IllegalArgumentException> {} })
    }

})
