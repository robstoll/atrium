package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.spec.*
import ch.tutteli.atrium.translations.DescriptionThrowableAssertion
import ch.tutteli.atrium.translations.DescriptionTypeTransformationAssertion
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import java.io.IOException

abstract class ThrowableAssertionsSpec(
    verbs: AssertionVerbFactory,
    toThrowTriple: Triple<String,
        ThrowableThrown.Builder.() -> Unit,
        ThrowableThrown.Builder.(assertionCreator: Assert<Throwable>.() -> Unit) -> Unit
        >,
    messagePair: Pair<String, Assert<Throwable>.(assertionCreator: Assert<String>.() -> Unit) -> Unit>,
    messageWithContainsFun: Assert<Throwable>.(String) -> Unit,
    messageContainsPair: Pair<String, Assert<Throwable>.(Any, Array<out Any>) -> Unit>,
    listBulletPoint: String,
    explanationBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    fun describeProperty(vararg funName: String, body: SpecBody.() -> Unit)
        = describeProperty(describePrefix, funName, body = body)

    val expect = verbs::checkException
    val assert: (IllegalArgumentException) -> Assert<IllegalArgumentException> = verbs::checkImmediately

    val (toThrow, toThrowFun, toThrowFunLazy) = toThrowTriple
    val (message, messageFun) = messagePair
    val (messageContains, messageContainsFun) = messageContainsPair

    fun SpecBody.checkToThrow(
        description: String,
        act: (ThrowableThrown.Builder.() -> Unit) -> Unit,
        lazy: (ThrowableThrown.Builder.() -> Unit),
        immediate: (ThrowableThrown.Builder.() -> Unit)
    ) {
        checkGenericNarrowingAssertion(description, act, lazy, "immediate" to immediate)
    }

    val messageDescr = DescriptionThrowableAssertion.OCCURRED_EXCEPTION_MESSAGE.getDefault()
    val stackTraceDescr = DescriptionThrowableAssertion.OCCURRED_EXCEPTION_STACKTRACE.getDefault()
    val causeDescr = DescriptionThrowableAssertion.OCCURRED_EXCEPTION_CAUSE.getDefault()
    val supressedDescr = DescriptionThrowableAssertion.OCCURRED_EXCEPTION_SUPPRESSED.getDefault()
    val separator = System.getProperty("line.separator")!!

    describeFun(toThrow) {
        checkToThrow("it throws an AssertionError when no exception occurs", { doToThrow ->
            verbs.checkException {
                verbs.checkException {
                    /* no exception occurs */
                }.doToThrow()
            }.toThrow<AssertionError> {
                messageContains(
                    DescriptionThrowableAssertion.NO_EXCEPTION_OCCURRED.getDefault(),
                    "${DescriptionThrowableAssertion.IS_A.getDefault()}: ${IllegalArgumentException::class.simpleName}"
                )
            }
        }, { toThrowFunLazy {} }, { toThrowFun() })


        checkToThrow("it allows to define assertions for the Throwable if the correct exception is thrown", { toThrowWithCheck ->
            verbs.checkException {
                throw IllegalArgumentException("hello")
            }.toThrowWithCheck()
        }, { toThrowFunLazy { message { toBe("hello") } } }, {})

        context("wrong exception"){
            val errMessage = "oho... error occurred"

            fun messageAndStackTrace(message: String) = "\\s+\\Q$explanationBulletPoint\\E$messageDescr: \"$message\".*$separator" +
                "\\s+\\Q$explanationBulletPoint\\E$stackTraceDescr: $separator" +
                "\\s+\\Q$listBulletPoint\\E${ThrowableAssertionsSpec::class.fullName}"

            checkToThrow("it throws an AssertionError and shows message and stacktrace as extra hint", { doToThrow ->
                verbs.checkException {
                    verbs.checkException {
                        throw UnsupportedOperationException(errMessage)
                    }.doToThrow()
                }.toThrow<AssertionError> {
                    message {
                        containsRegex(
                            DescriptionThrowableAssertion.IS_A.getDefault() + ":.+" + IllegalArgumentException::class.fullName,
                            UnsupportedOperationException::class.simpleName + separator + messageAndStackTrace(errMessage)
                        )
                    }
                }
            }, { toThrowFunLazy {} }, { toThrowFun() })

            context("with a cause") {

                checkToThrow("shows cause as extra hint", { doToThrow ->
                    verbs.checkException {
                        verbs.checkException {
                            throw UnsupportedOperationException("not supported", IllegalStateException(errMessage))
                        }.doToThrow()
                    }.toThrow<AssertionError> {
                        message {
                            containsRegex(
                                UnsupportedOperationException::class.simpleName + separator + messageAndStackTrace("not supported"),
                                "\\s+\\Q$explanationBulletPoint\\E$causeDescr: ${IllegalStateException::class.fullName}" + messageAndStackTrace(errMessage)
                            )
                        }
                    }
                }, { toThrowFunLazy {} }, { toThrowFun() })

                checkToThrow("with nested cause, shows both causes as extra hint", { doToThrow ->
                    verbs.checkException {
                        verbs.checkException {
                            throw UnsupportedOperationException("not supported", IOException("io", IllegalStateException(errMessage)))
                        }.doToThrow()
                    }.toThrow<AssertionError> {
                        message {
                            containsRegex(
                                UnsupportedOperationException::class.simpleName + separator + messageAndStackTrace("not supported"),
                                "\\s+\\Q$explanationBulletPoint\\E$causeDescr: ${IOException::class.fullName}" + messageAndStackTrace("io"),
                                    "\\s+\\Q$explanationBulletPoint\\E$causeDescr: ${IllegalStateException::class.fullName}" + messageAndStackTrace(errMessage)
                            )
                        }
                    }
                }, { toThrowFunLazy {} }, { toThrowFun() })
            }

            context("with suppressed") {

                checkToThrow("shows all suppressed as extra hint", { doToThrow ->
                        verbs.checkException {
                            verbs.checkException {
                                val ex = UnsupportedOperationException("not supported")
                                ex.addSuppressed(IllegalStateException("not good"))
                                ex.addSuppressed(IllegalArgumentException(errMessage))
                                throw ex
                            }.doToThrow()
                        }.toThrow<AssertionError> {
                            message {
                                containsRegex(
                                    UnsupportedOperationException::class.simpleName + separator + messageAndStackTrace("not supported"),
                                    "\\s+\\Q$explanationBulletPoint\\E$supressedDescr: ${IllegalStateException::class.fullName}" + messageAndStackTrace("not good"),
                                    "\\s+\\Q$explanationBulletPoint\\E$supressedDescr: ${IllegalArgumentException::class.fullName}" + messageAndStackTrace(errMessage)
                                )
                            }
                        }
                    }, { toThrowFunLazy {} }, { toThrowFun() })
            }
        }
    }

    describeProperty(message) {
        checkNarrowingAssertion<Throwable>("it throws an AssertionError if the ${Throwable::message.name} is null", { message ->
            val throwable = IllegalArgumentException()
            expect {
                assert(throwable).message()
            }.toThrow<AssertionError> {
                messageContains(
                    DescriptionTypeTransformationAssertion.IS_A.getDefault(),
                    String::class.java.name
                )
            }
        }, { messageFun {} })

        context("it allows to define an assertion for the ${Throwable::message.name} if it is not null") {
            val throwable = IllegalArgumentException("oh no")
            checkNarrowingAssertion<Throwable>("it throws an AssertionError if the assertion does not hold", { messageWithCheck ->
                expect {
                    assert(throwable).messageWithCheck()
                }.toThrow<AssertionError>{}
            }, { messageWithContainsFun("hello") })

            checkNarrowingAssertion<Throwable>("it does not throw an exception if the assertion holds", { messageWithCheck ->
                assert(throwable).messageWithCheck()
            }, { messageWithContainsFun("oh") })
        }
    }

    describeFun(messageContains) {
        checkNarrowingAssertion<Throwable>("it throws an AssertionError if the ${Throwable::message.name} is null", { messageContains ->
            val throwable = IllegalArgumentException()
            expect {
                assert(throwable).messageContains()
            }.toThrow<AssertionError> {
                messageContains(
                    DescriptionTypeTransformationAssertion.IS_A.getDefault(),
                    String::class.java.name
                )
            }
        }, { messageContainsFun(1, arrayOf(2.3, 'z', "hello")) })

        context("it allows to define an assertion for the ${Throwable::message.name} if it is not null") {
            val throwable = IllegalArgumentException("1 2.3 z hello")
            checkNarrowingAssertion<Throwable>("it throws an AssertionError if the assertion does not hold", { messageContains ->
                expect {
                    assert(throwable).messageContains()
                }.toThrow<AssertionError>{}
            }, { messageContainsFun("nada", arrayOf()) })

            checkNarrowingAssertion<Throwable>("it does not throw an exception if the assertion holds", { messageWithCheck ->
                assert(throwable).messageWithCheck()
            }, { messageContainsFun(1, arrayOf(2.3, 'z', "hello")) })


            checkNarrowingAssertion<Throwable>("it throws an IllegalArgumentException if an object is passed", { messageContains ->
                expect {
                    assert(throwable).messageContains()
                }.toThrow<IllegalArgumentException>{}
            }, { messageContainsFun(Object(), arrayOf()) })
        }

    }
})
