package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.throwable.thrown.builders.ThrowableThrownBuilder
import ch.tutteli.atrium.spec.*
import ch.tutteli.atrium.translations.DescriptionThrowableAssertion
import ch.tutteli.atrium.translations.DescriptionTypeTransformationAssertion
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context

abstract class ThrowableAssertionsSpec(
    verbs: AssertionVerbFactory,
    toThrowTriple: Triple<String,
        ThrowableThrownBuilder.() -> Unit,
        ThrowableThrownBuilder.(assertionCreator: Assert<Throwable>.() -> Unit) -> Unit
        >,
    messagePair: Pair<String, Assert<Throwable>.(assertionCreator: Assert<String>.() -> Unit) -> Unit>,
    messageContainsFun: Assert<Throwable>.(String) -> Unit,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    fun describeProperty(vararg funName: String, body: SpecBody.() -> Unit)
        = describeProperty(describePrefix, funName, body = body)

    val expect = verbs::checkException
    val assert: (IllegalArgumentException) -> Assert<IllegalArgumentException> = verbs::checkImmediately

    val (message, messageFun) = messagePair
    val (toThrow, toThrowFun, toThrowFunLazy) = toThrowTriple

    fun SpecBody.checkToThrow(
        description: String,
        act: (ThrowableThrownBuilder.() -> Unit) -> Unit,
        lazy: (ThrowableThrownBuilder.() -> Unit),
        immediate: (ThrowableThrownBuilder.() -> Unit)
    ) {
        checkGenericNarrowingAssertion(description, act, lazy, "immediate" to immediate)
    }

    describeFun(toThrow) {
        checkToThrow("it throws an AssertionError when no exception occurs", { doToThrow ->
            verbs.checkException {
                verbs.checkException {
                    /* no exception occurs */
                }.doToThrow()
            }.toThrow<AssertionError> {
                message {
                    containsDefaultTranslationOf(DescriptionThrowableAssertion.NO_EXCEPTION_OCCURRED)
                    contains("${DescriptionThrowableAssertion.IS_A.getDefault()}: ${IllegalArgumentException::class.simpleName}")
                }
            }
        }, { toThrowFunLazy {} }, { toThrowFun() })

        checkToThrow("it throws an AssertionError when the wrong exception occurs", { doToThrow ->
            verbs.checkException {
                verbs.checkException {
                    throw UnsupportedOperationException()
                }.doToThrow()
            }.toThrow<AssertionError> {
                message {
                    contains(
                        UnsupportedOperationException::class.java.name,
                        DescriptionThrowableAssertion.IS_A.getDefault(),
                        IllegalArgumentException::class.java.name
                    )
                }
            }
        }, { toThrowFunLazy {} }, { toThrowFun() })

        checkToThrow("it allows to define assertions for the Throwable if the correct exception is thrown", { toThrowWithCheck ->
            verbs.checkException {
                throw IllegalArgumentException("hello")
            }.toThrowWithCheck()
        }, { toThrowFunLazy { message { toBe("hello") } } }, {})
    }

    describeProperty(message) {
        checkNarrowingAssertion<Throwable>("it throws an AssertionError if the ${Throwable::message.name} is null", { message ->
            val throwable = IllegalArgumentException()
            expect {
                assert(throwable).message()
            }.toThrow<AssertionError> {
                message {
                    containsDefaultTranslationOf(DescriptionTypeTransformationAssertion.IS_A)
                    contains(String::class.java.name)
                }
            }
        }, { messageFun {} })

        context("it allows to define an assertion for the ${Throwable::message.name} if it is not null") {
            val throwable = IllegalArgumentException("oh no")
            checkNarrowingAssertion<Throwable>("it throws an AssertionError if the assertion does not hold", { messageWithCheck ->
                expect {
                    assert(throwable).messageWithCheck()
                }.toThrow<AssertionError>()
            }, { messageContainsFun("hello") })

            checkNarrowingAssertion<Throwable>("it does not throw an exception if the assertion holds", { messageWithCheck ->
                assert(throwable).messageWithCheck()
            }, { messageContainsFun("oh") })
        }
    }
})
