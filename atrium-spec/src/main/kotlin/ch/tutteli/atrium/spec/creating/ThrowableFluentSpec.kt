package ch.tutteli.atrium.spec.creating

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.DescriptionAnyAssertion.IS_SAME
import ch.tutteli.atrium.assertions.DescriptionThrowableAssertion.IS_A
import ch.tutteli.atrium.assertions.DescriptionThrowableAssertion.NO_EXCEPTION_OCCURRED
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.creating.IThrowableFluent
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.TranslatableRawString
import ch.tutteli.atrium.spec.AssertionVerb
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.checkGenericNarrowingAssertion
import ch.tutteli.atrium.spec.prefixedDescribe
import com.nhaarman.mockito_kotlin.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context

abstract class ThrowableFluentSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (assertionVerb: ITranslatable, act: () -> Unit, IAssertionChecker) -> IThrowableFluent,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit) {
        prefixedDescribe(describePrefix, description, body)
    }

    fun SpecBody.checkToThrow(description: String,
                              act: (IThrowableFluent.() -> Unit) -> Unit,
                              immediate: (IThrowableFluent.() -> Unit),
                              lazy: (IThrowableFluent.() -> Unit)) {
        checkGenericNarrowingAssertion(description, act, immediate, lazy)
    }

    fun createThrowable(assertionVerb: ITranslatable, throwable: Throwable?, checker: IAssertionChecker): IThrowableFluent {
        val act = {
            if (throwable != null) {
                throw throwable
            }
        }
        return testeeFactory(assertionVerb, act, checker)
    }

    prefixedDescribe("fun toThrow") {

        checkToThrow("it throws an AssertionError when no exception occurs", { doToThrow ->
            verbs.checkException {
                verbs.checkException {
                    /* no exception occurs */
                }.doToThrow()
            }.toThrow<AssertionError>().and.message {
                containsDefaultTranslationOf(NO_EXCEPTION_OCCURRED, IS_A)
                contains(IllegalArgumentException::class.java.name)
            }
        }, { toThrow(IllegalArgumentException::class, IS_A, NO_EXCEPTION_OCCURRED) },
            { toThrow(IllegalArgumentException::class, IS_A, NO_EXCEPTION_OCCURRED) {} })

        checkToThrow("it throws an AssertionError when the wrong exception occurs", { doToThrow ->
            verbs.checkException {
                verbs.checkException {
                    throw UnsupportedOperationException()
                }.doToThrow()
            }.toThrow<AssertionError> {
                message.contains(UnsupportedOperationException::class.java.name, IS_SAME.getDefault(), IllegalArgumentException::class.java.name)
            }
        }, { toThrow(IllegalArgumentException::class, IS_SAME, NO_EXCEPTION_OCCURRED) },
            { toThrow(IllegalArgumentException::class, IS_SAME, NO_EXCEPTION_OCCURRED) {} })

        checkToThrow("it allows to define assertions for the Throwable if the correct exception is thrown", { toThrowWithCheck ->
            verbs.checkException {
                throw IllegalArgumentException("hello")
            }.toThrowWithCheck()
        }, { toThrow(IllegalArgumentException::class, IS_A, NO_EXCEPTION_OCCURRED).and.message.toBe("hello") },
            { toThrow(IllegalArgumentException::class, IS_A, NO_EXCEPTION_OCCURRED) { and.message.toBe("hello") } })

        val assertionVerb = AssertionVerb.VERB

        class CheckerAndFluent(var checker: IAssertionChecker = mock(),
                               var fluent: IThrowableFluent = AtriumFactory.newThrowableFluent(AssertionVerb.VERB, {}, checker))

        context("dependencies") {
            group("in case the expected exception is thrown") {
                val subject = IllegalArgumentException()
                val checkerAndFluent = CheckerAndFluent()
                beforeEachTest {
                    checkerAndFluent.checker = mock()
                    checkerAndFluent.fluent = createThrowable(assertionVerb, subject, checkerAndFluent.checker)
                }
                checkToThrow("it uses the given AssertionChecker to check that the correct exception is thrown", { doToThrow ->
                    checkerAndFluent.fluent.doToThrow()
                    verify(checkerAndFluent.checker).check(eq(assertionVerb), eq(subject), any())
                }, { toThrow(IllegalArgumentException::class, IS_A, NO_EXCEPTION_OCCURRED) },
                    { toThrow(IllegalArgumentException::class, IS_A, NO_EXCEPTION_OCCURRED) {} })

                group("it uses the given AssertionChecker to check additional assertions") {
                    test("in case of immediate evaluation") {
                        checkerAndFluent.fluent.toThrow(IllegalArgumentException::class, IS_A, NO_EXCEPTION_OCCURRED).toBe(subject)
                        verify(checkerAndFluent.checker, times(2)).check(eq(assertionVerb), eq(subject), any())
                    }
                    test("in case of lazy evaluation") {
                        checkerAndFluent.fluent.toThrow(IllegalArgumentException::class, IS_A, NO_EXCEPTION_OCCURRED) { toBe(subject) }
                        verify(checkerAndFluent.checker, times(1)).check(eq(assertionVerb), eq(subject), any())
                    }
                }
            }

            val assertionError = AssertionError()
            fun setUpCheckerAndFluentThrowingAssertionError(checkerAndFluent: CheckerAndFluent, subject: Throwable?) {
                beforeEachTest {
                    checkerAndFluent.checker = mock {
                        on { fail(any(), any(), any()) }.doThrow(assertionError)
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
                    verify(checkerAndFluent.checker).fail(eq(assertionVerb), eq(TranslatableRawString(NO_EXCEPTION_OCCURRED)), any())
                }, { toThrow(AssertionError::class, IS_A, NO_EXCEPTION_OCCURRED).and.toBe(assertionError) },
                    { toThrow(AssertionError::class, IS_A, NO_EXCEPTION_OCCURRED) { toBe(assertionError) } })
            }

            group("in case the wrong exception is thrown") {
                val subject = UnsupportedOperationException()
                val checkerAndFluent = CheckerAndFluent()
                setUpCheckerAndFluentThrowingAssertionError(checkerAndFluent, subject)
                checkToThrow("it uses the given AssertionChecker to report the failure", { toThrowWithCheck ->
                    verbs.checkException {
                        checkerAndFluent.fluent.toThrow<IllegalArgumentException>()
                    }.toThrowWithCheck()
                    verify(checkerAndFluent.checker).fail(eq(assertionVerb), eq(subject), any())
                }, { toThrow(AssertionError::class, IS_A, NO_EXCEPTION_OCCURRED).and.toBe(assertionError) },
                    { toThrow(AssertionError::class, IS_A, NO_EXCEPTION_OCCURRED) { toBe(assertionError) } })
            }
            val assertionChecker = mock<IAssertionChecker>()
            val subject: IllegalArgumentException? = null
            val fluent = createThrowable(assertionVerb, subject, assertionChecker)

            checkToThrow("it throws an IllegalStateException, if the checker does not throw an AssertionError even though no exception was thrown", { doToThrow ->
                verbs.checkException {
                    fluent.doToThrow()
                }.toThrow<IllegalStateException>()
            }, { toThrow(IllegalArgumentException::class, IS_A, NO_EXCEPTION_OCCURRED) },
                { toThrow(IllegalArgumentException::class, IS_A, NO_EXCEPTION_OCCURRED) {} })
        }
    }
})
