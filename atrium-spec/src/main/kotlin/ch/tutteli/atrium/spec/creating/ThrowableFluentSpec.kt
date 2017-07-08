package ch.tutteli.atrium.spec.creating

import ch.tutteli.atrium.*
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.creating.ThrowableFluent
import ch.tutteli.atrium.creating.IThrowableFluent.AssertionDescription.NO_EXCEPTION_OCCURRED
import ch.tutteli.atrium.creating.IThrowableFluent.AssertionDescription.IS_A
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.TranslatableRawString
import ch.tutteli.atrium.spec.AssertionVerb
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.checkGenericNarrowingAssertion
import com.nhaarman.mockito_kotlin.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context

open class ThrowableFluentSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (assertionVerb: ITranslatable, act: () -> Unit, IAssertionChecker) -> ThrowableFluent
) : Spek({

    fun SpecBody.checkToThrow(description: String,
                              act: (ThrowableFluent.() -> Unit) -> Unit,
                              immediate: (ThrowableFluent.() -> Unit),
                              lazy: (ThrowableFluent.() -> Unit)) {
        checkGenericNarrowingAssertion(description, act, immediate, lazy)
    }

    fun createThrowable(assertionVerb: ITranslatable, throwable: Throwable?, checker: IAssertionChecker): ThrowableFluent {
        val act = {
            if (throwable != null) {
                throw throwable
            }
        }
        return testeeFactory(assertionVerb, act, checker)
    }


    checkToThrow("it throws an AssertionError when no exception occurs", { doToThrow ->
        verbs.checkException {
            verbs.checkException {
                /* no exception occurs */
            }.doToThrow()
        }.toThrow<AssertionError>().and.message {
            containsDefaultTranslationOf(NO_EXCEPTION_OCCURRED, IS_A)
            contains(IllegalArgumentException::class.java.name)
        }
    }, { toThrow<IllegalArgumentException>() }, { toThrow<IllegalArgumentException> {} })

    checkToThrow("it throws an AssertionError when the wrong exception occurs", { doToThrow ->
        verbs.checkException {
            verbs.checkException {
                throw UnsupportedOperationException()
            }.doToThrow()
        }.toThrow<AssertionError> {
            message.contains(UnsupportedOperationException::class.java.name, "is a", IllegalArgumentException::class.java.name)
        }
    }, { toThrow<IllegalArgumentException>() }, { toThrow<IllegalArgumentException> {} })

    checkToThrow("it allows to define assertions for the Throwable if the correct exception is thrown", { toThrowWithCheck ->
        verbs.checkException {
            throw IllegalArgumentException("hello")
        }.toThrowWithCheck()
    }, { toThrow<IllegalArgumentException>().and.message.toBe("hello") }, { toThrow<IllegalArgumentException> { and.message.toBe("hello") } })

    val assertionVerb = AssertionVerb.VERB
    class CheckerAndFluent(var checker: IAssertionChecker = mock<IAssertionChecker>(), var fluent: ThrowableFluent = AtriumFactory.newThrowableFluent(AssertionVerb.VERB, {}, checker))

    context("dependencies") {
        group("in case the expected exception is thrown") {
            val subject = IllegalArgumentException()
            val checkerAndFluent = CheckerAndFluent()
            beforeEachTest {
                checkerAndFluent.checker = mock<IAssertionChecker>()
                checkerAndFluent.fluent = createThrowable(assertionVerb, subject, checkerAndFluent.checker)
            }
            checkToThrow("it uses the given AssertionChecker to check that the correct exception is thrown", { doToThrow ->
                checkerAndFluent.fluent.doToThrow()
                verify(checkerAndFluent.checker).check(eq(assertionVerb), eq(subject), any<List<IAssertion>>())
            }, { toThrow<IllegalArgumentException>() }, { toThrow<IllegalArgumentException> {} })

            group("it uses the given AssertionChecker to check additional assertions"){
                test("in case of immediate evaluation"){
                    checkerAndFluent.fluent.toThrow<IllegalArgumentException>().toBe(subject)
                    verify(checkerAndFluent.checker, times(2)).check(eq(assertionVerb), eq(subject), any<List<IAssertion>>())
                }
                test("in case of lazy evaluation"){
                    checkerAndFluent.fluent.toThrow<IllegalArgumentException> { toBe(subject) }
                    verify(checkerAndFluent.checker, times(1)).check(eq(assertionVerb), eq(subject), any<List<IAssertion>>())
                }
            }
        }

        val assertionError = AssertionError()
        fun setUpCheckerAndFluentThrowingAssertionError(checkerAndFluent: CheckerAndFluent, subject: Throwable?) {
            beforeEachTest {
                checkerAndFluent.checker = mock<IAssertionChecker> {
                    on { fail(any<ITranslatable>(), any(), any<IAssertion>()) }.doThrow(assertionError)
                }
                checkerAndFluent.fluent = createThrowable(assertionVerb, subject, checkerAndFluent.checker)
            }
        }

        group("in case no exception is thrown") {
            val subject: IllegalArgumentException? = null
            val checkerAndFluent = CheckerAndFluent()
            setUpCheckerAndFluentThrowingAssertionError(checkerAndFluent, subject)
            checkToThrow("it uses the given AssertionChecker to report the failure", { toThrowWithCheck ->
                verbs.checkException {
                    checkerAndFluent.fluent.toThrow<IllegalArgumentException>()
                }.toThrowWithCheck()
                verify(checkerAndFluent.checker).fail(eq(assertionVerb), eq(TranslatableRawString(NO_EXCEPTION_OCCURRED)), any<IAssertion>())
            }, { toThrow<AssertionError>().and.toBe(assertionError) }, { toThrow<AssertionError> { toBe(assertionError) } })
        }

        group("in case the wrong exception is thrown") {
            val subject = UnsupportedOperationException()
            val checkerAndFluent = CheckerAndFluent()
            setUpCheckerAndFluentThrowingAssertionError(checkerAndFluent, subject)
            checkToThrow("it uses the given AssertionChecker to report the failure", { toThrowWithCheck ->
                verbs.checkException {
                    checkerAndFluent.fluent.toThrow<IllegalArgumentException>()
                }.toThrowWithCheck()
                verify(checkerAndFluent.checker).fail(eq(assertionVerb), eq(subject), any<IAssertion>())
            }, { toThrow<AssertionError>().and.toBe(assertionError) }, { toThrow<AssertionError> { toBe(assertionError) } })
        }
        val assertionChecker = mock<IAssertionChecker>()
        val subject: IllegalArgumentException? = null
        val fluent = createThrowable(assertionVerb, subject, assertionChecker)

        checkToThrow("it throws an IllegalStateException, if the checker does not throw an AssertionError even though no exception was thrown", { doToThrow ->
            verbs.checkException {
                fluent.doToThrow()
            }.toThrow<IllegalStateException>()
        }, { toThrow<IllegalArgumentException>() }, { toThrow<IllegalArgumentException> {} })
    }

})
