package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.DescriptionNarrowingAssertion
import ch.tutteli.atrium.assertions.DescriptionThrowableAssertion
import ch.tutteli.atrium.assertions.throwable.thrown.builders.ThrowableThrownBuilder
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.checkGenericNarrowingAssertion
import ch.tutteli.atrium.spec.checkNarrowingAssertion
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe

abstract class ThrowableAssertionsSpec(
    verbs: IAssertionVerbFactory,
    toThrowTriple: Triple<String,
        ThrowableThrownBuilder.() -> Unit,
        ThrowableThrownBuilder.(assertionCreator: IAssertionPlant<Throwable>.() -> Unit) -> Unit
        >,
    messagePair: Pair<String, IAssertionPlant<Throwable>.(assertionCreator: IAssertionPlant<String>.() -> Unit) -> Unit>,
    messageContainsFun: IAssertionPlant<Throwable>.(String) -> Unit
) : Spek({

    val expect = verbs::checkException
    val assert: (IllegalArgumentException) -> IAssertionPlant<IllegalArgumentException> = verbs::checkImmediately

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

    describe("fun $toThrow") {
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

    describe("fun `$message` (for Throwable)") {
        checkNarrowingAssertion<Throwable>("it throws an AssertionError if the ${Throwable::message.name} is null", { message ->
            val throwable = IllegalArgumentException()
            expect {
                assert(throwable).message()
            }.toThrow<AssertionError> {
                message {
                    containsDefaultTranslationOf(DescriptionNarrowingAssertion.IS_A)
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
