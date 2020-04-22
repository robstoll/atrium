// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")

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
import org.jetbrains.spek.api.dsl.it
import java.io.IOException

@Deprecated("Switch from Assert to Expect and use Spec from atrium-specs-common; will be removed with 1.0.0")
abstract class ThrowableAssertionsSpec(
    verbs: AssertionVerbFactory,
    toThrowTriple: Triple<String,
        ThrowableThrown.Builder.() -> Unit,
        ThrowableThrown.Builder.(assertionCreator: Assert<Throwable>.() -> Unit) -> Unit
        >,
    notToThrowPair: Pair<String, ThrowableThrown.Builder.()->Unit>,
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
    val (notToThrow, notThrowFun) = notToThrowPair
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
    fun SpecBody.expectThrowsAndMessageContainsRegex(
        toThrowFun: ThrowableThrown.Builder.() -> Unit,
        act: () -> Unit,
        pattern: String, vararg otherPatterns: String
    ){
        expect {
            expect { act() }.toThrowFun()
        }.toThrow<AssertionError> {
            message { containsRegex(pattern, *otherPatterns) }
        }
    }
    fun SpecBody.expectThrowsAssertionErrorAndMessageContainsRegex(
        toThrowFun: ThrowableThrown.Builder.() -> Unit,
        throwable: Throwable,
        pattern: String, vararg otherPatterns: String
    ) = expectThrowsAndMessageContainsRegex(toThrowFun, {throw throwable}, pattern, *otherPatterns)

    val isADescr = DescriptionThrowableAssertion.IS_A.getDefault()
    val messageDescr = DescriptionThrowableAssertion.OCCURRED_EXCEPTION_MESSAGE.getDefault()
    val stackTraceDescr = DescriptionThrowableAssertion.OCCURRED_EXCEPTION_STACKTRACE.getDefault()
    val causeDescr = DescriptionThrowableAssertion.OCCURRED_EXCEPTION_CAUSE.getDefault()
    val supressedDescr = DescriptionThrowableAssertion.OCCURRED_EXCEPTION_SUPPRESSED.getDefault()
    val separator = System.getProperty("line.separator")!!

    val errMessage = "oho... error occurred"

    fun messageAndStackTrace(message: String) = "\\s+\\Q$explanationBulletPoint\\E$messageDescr: \"$message\".*$separator" +
        "\\s+\\Q$explanationBulletPoint\\E$stackTraceDescr: $separator" +
        "\\s+\\Q$listBulletPoint\\E${ThrowableAssertionsSpec::class.fullName}"



    describeFun(toThrow) {
        checkToThrow("it throws an AssertionError when no exception occurs", { doToThrow ->
            expectThrowsAndMessageContainsRegex(doToThrow,
                { /* no exception occurs */ },
                DescriptionThrowableAssertion.NO_EXCEPTION_OCCURRED.getDefault(),
                "$isADescr: ${IllegalArgumentException::class.simpleName}"
            )
        }, { toThrowFunLazy {} }, { toThrowFun() })


        checkToThrow("it allows to define assertions for the Throwable if the correct exception is thrown", { toThrowWithCheck ->
            expect {
                throw IllegalArgumentException("hello")
            }.toThrowWithCheck()
        }, { toThrowFunLazy { message { toBe("hello") } } }, {})

        context("wrong exception"){


            checkToThrow("it throws an AssertionError and shows message and stacktrace as extra hint", { doToThrow ->
                expectThrowsAssertionErrorAndMessageContainsRegex(doToThrow,
                    UnsupportedOperationException(errMessage),
                    "$isADescr:.+" + IllegalArgumentException::class.fullName,
                    UnsupportedOperationException::class.simpleName + separator + messageAndStackTrace(errMessage)
                )
            }, { toThrowFunLazy {} }, { toThrowFun() })

            context("with a cause") {

                checkToThrow("shows cause as extra hint", { doToThrow ->
                    expectThrowsAssertionErrorAndMessageContainsRegex(doToThrow,
                        UnsupportedOperationException("not supported", IllegalStateException(errMessage)),
                        UnsupportedOperationException::class.simpleName + separator + messageAndStackTrace("not supported"),
                        "\\s+\\Q$explanationBulletPoint\\E$causeDescr: ${IllegalStateException::class.fullName}" + messageAndStackTrace(errMessage)
                    )
                }, { toThrowFunLazy {} }, { toThrowFun() })

                checkToThrow("with nested cause, shows both causes as extra hint", { doToThrow ->
                    expectThrowsAssertionErrorAndMessageContainsRegex(doToThrow,
                        UnsupportedOperationException("not supported", IOException("io", IllegalStateException(errMessage))),
                        UnsupportedOperationException::class.simpleName + separator + messageAndStackTrace("not supported"),
                        "\\s+\\Q$explanationBulletPoint\\E$causeDescr: ${IOException::class.fullName}" + messageAndStackTrace("io"),
                            "\\s+\\Q$explanationBulletPoint\\E$causeDescr: ${IllegalStateException::class.fullName}" + messageAndStackTrace(errMessage)
                    )
                }, { toThrowFunLazy {} }, { toThrowFun() })
            }

            context("with suppressed") {

                checkToThrow("shows all suppressed as extra hint", { doToThrow ->
                    val ex = UnsupportedOperationException("not supported")
                    ex.addSuppressed(IllegalStateException("not good"))
                    ex.addSuppressed(IllegalArgumentException(errMessage))
                    expectThrowsAssertionErrorAndMessageContainsRegex(doToThrow,
                        ex,
                        UnsupportedOperationException::class.simpleName + separator + messageAndStackTrace("not supported"),
                        "\\s+\\Q$explanationBulletPoint\\E$supressedDescr: ${IllegalStateException::class.fullName}" + messageAndStackTrace("not good"),
                        "\\s+\\Q$explanationBulletPoint\\E$supressedDescr: ${IllegalArgumentException::class.fullName}" + messageAndStackTrace(errMessage)
                    )
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
                @Suppress("DEPRECATION")
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
                @Suppress("DEPRECATION")
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

    describeFun(notToThrow) {
        context("no exception occurs") {
            it("does not throw"){
                expect{}.notThrowFun()
            }
        }
        context("exception is thrown") {
            val notThrown : ThrowableThrown.Builder.() -> Unit = { notThrowFun() }

            it("throws an AssertionError and shows message and stackTrace") {
                expectThrowsAssertionErrorAndMessageContainsRegex(notThrown,
                    UnsupportedOperationException(errMessage),
                    UnsupportedOperationException::class.simpleName + separator + messageAndStackTrace(errMessage),
                    "..."
                )
            }

            context("with a cause") {
                it("shows cause as extra hint") {
                    expectThrowsAssertionErrorAndMessageContainsRegex(notThrown,
                        UnsupportedOperationException("not supported", IllegalStateException(errMessage)),
                        UnsupportedOperationException::class.simpleName + separator + messageAndStackTrace("not supported"),
                        "\\s+\\Q$explanationBulletPoint\\E$causeDescr: ${IllegalStateException::class.fullName}" + messageAndStackTrace(errMessage)
                    )
                }

                test("with nested cause, shows both causes as extra hint") {
                    expectThrowsAssertionErrorAndMessageContainsRegex(notThrown,
                        UnsupportedOperationException(
                            "not supported",
                            IOException("io", IllegalStateException(errMessage))
                        ),
                        UnsupportedOperationException::class.simpleName + separator + messageAndStackTrace("not supported"),
                        "\\s+\\Q$explanationBulletPoint\\E$causeDescr: ${IOException::class.fullName}" + messageAndStackTrace("io"),
                        "\\s+\\Q$explanationBulletPoint\\E$causeDescr: ${IllegalStateException::class.fullName}" + messageAndStackTrace(errMessage)
                    )
                }
            }

            context("with suppressed") {

                it("shows all suppressed as extra hint") {
                    val ex = UnsupportedOperationException("not supported")
                    ex.addSuppressed(IllegalStateException("not good"))
                    ex.addSuppressed(IllegalArgumentException(errMessage))
                    expectThrowsAssertionErrorAndMessageContainsRegex(
                        notThrown,
                        ex,
                        UnsupportedOperationException::class.simpleName + separator + messageAndStackTrace("not supported"),
                        "\\s+\\Q$explanationBulletPoint\\E$supressedDescr: ${IllegalStateException::class.fullName}" + messageAndStackTrace("not good"),
                        "\\s+\\Q$explanationBulletPoint\\E$supressedDescr: ${IllegalArgumentException::class.fullName}" + messageAndStackTrace(errMessage)
                    )
                }
                test("with nested cause, shows both causes as extra hint") {
                    val ex = UnsupportedOperationException("not supported")
                    ex.addSuppressed(IOException("io", IllegalStateException(errMessage)))
                    expectThrowsAssertionErrorAndMessageContainsRegex(notThrown,
                        ex,
                        UnsupportedOperationException::class.simpleName + separator + messageAndStackTrace("not supported"),
                        "\\s+\\Q$explanationBulletPoint\\E$supressedDescr: ${IOException::class.fullName}" + messageAndStackTrace("io"),
                        "\\s+\\Q$explanationBulletPoint\\E$causeDescr: ${IllegalStateException::class.fullName}" + messageAndStackTrace(errMessage)
                    )
                }
            }
        }
    }
})
