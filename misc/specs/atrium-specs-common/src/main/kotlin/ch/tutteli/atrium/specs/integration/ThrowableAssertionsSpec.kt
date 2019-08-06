@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)

package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import ch.tutteli.atrium.translations.DescriptionThrowableAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class ThrowableAssertionsSpec(
    verbs: AssertionVerbFactory,
    toThrowTriple: Triple<String,
        ThrowableThrown.Builder.() -> Unit,
        ThrowableThrown.Builder.(assertionCreator: Expect<IllegalArgumentException>.() -> Unit) -> Unit
        >,
    notToThrow: Pair<String, ThrowableThrown.Builder.() -> Expect<Nothing?>>,
    messageFeature: Feature0<Throwable, String>,
    message: Fun1<Throwable, Expect<String>.() -> Unit>,
    messageContains: Fun2<Throwable, Any, Array<out Any>>,
    listBulletPoint: String,
    explanationBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Throwable>("$describePrefix[nullable Key] ",
        messageFeature.forSubjectLess(),
        message.forSubjectLess { toBe("hello") },
        messageContains.forSubjectLess("hello", arrayOf())
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val expect = verbs::checkException
    val assert: (Throwable) -> Expect<Throwable> = verbs::check

    val (toThrow, toThrowFun, toThrowFunLazy) = toThrowTriple

    fun Suite.checkToThrow(
        description: String,
        act: (ThrowableThrown.Builder.() -> Unit) -> Unit,
        lazy: (ThrowableThrown.Builder.() -> Unit),
        immediate: (ThrowableThrown.Builder.() -> Unit)
    ) {
        checkGenericNarrowingAssertion(description, act, lazy, "immediate" to immediate)
    }

    fun Suite.expectThrowsAssertionErrorAndMessageContainsRegex(
        toThrowFun: ThrowableThrown.Builder.() -> Unit,
        throwable: Throwable,
        pattern: String, vararg otherPatterns: String
    ) {
        expect {
            expect { ({throw throwable})() }.toThrowFun()
        }.toThrow<AssertionError> {
            message {
                containsRegex(pattern, *otherPatterns)
            }
        }
    }

    val isADescr = DescriptionAnyAssertion.IS_A.getDefault()
    val messageDescr = DescriptionThrowableAssertion.OCCURRED_EXCEPTION_MESSAGE.getDefault()
    val stackTraceDescr = DescriptionThrowableAssertion.OCCURRED_EXCEPTION_STACKTRACE.getDefault()
    val causeDescr = DescriptionThrowableAssertion.OCCURRED_EXCEPTION_CAUSE.getDefault()
    val separator = lineSeperator

    val errMessage = "oho... error occurred"

    fun messageAndStackTrace(message: String) = "\\s+\\Q$explanationBulletPoint\\E$messageDescr: \"$message\".*$separator" +
        "\\s+\\Q$explanationBulletPoint\\E$stackTraceDescr: $separator" +
        "\\s+\\Q$listBulletPoint\\E${ThrowableAssertionsSpec::class.fullName}"

    describeFun(toThrow) {
        checkToThrow("it throws an AssertionError when no exception occurs", { doToThrow ->
            expect {
                expect { ({ /* no exception occurs */ })() }.doToThrow()
            }.toThrow<AssertionError> {
                message {
                    contains.exactly(1).regex(
                        DescriptionThrowableAssertion.NO_EXCEPTION_OCCURRED.getDefault(),
                        "$isADescr: ${IllegalArgumentException::class.simpleName}"
                    )
                }
            }
        }, { toThrowFunLazy { toBe(IllegalArgumentException("what")) } }, { toThrowFun() })


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
            }, { toThrowFunLazy { message { toBe("hello") } } }, { toThrowFun() })

            context("with a cause") {

                checkToThrow("shows cause as extra hint", { doToThrow ->
                    expectThrowsAssertionErrorAndMessageContainsRegex(doToThrow,
                        UnsupportedOperationException("not supported", IllegalStateException(errMessage)),
                        UnsupportedOperationException::class.simpleName + separator + messageAndStackTrace("not supported"),
                        "\\s+\\Q$explanationBulletPoint\\E$causeDescr: ${IllegalStateException::class.fullName}" + messageAndStackTrace(errMessage)
                    )
                }, { toThrowFunLazy { message { toBe("hello") } } }, { toThrowFun() })

                checkToThrow("with nested cause, shows both causes as extra hint", { doToThrow ->
                    expectThrowsAssertionErrorAndMessageContainsRegex(doToThrow,
                        UnsupportedOperationException("not supported", RuntimeException("io", IllegalStateException(errMessage))),
                        UnsupportedOperationException::class.simpleName + separator + messageAndStackTrace("not supported"),
                        "\\s+\\Q$explanationBulletPoint\\E$causeDescr: ${RuntimeException::class.fullName}" + messageAndStackTrace("io"),
                            "\\s+\\Q$explanationBulletPoint\\E$causeDescr: ${IllegalStateException::class.fullName}" + messageAndStackTrace(errMessage)
                    )
                }, { toThrowFunLazy { message { toBe("hello") } } }, { toThrowFun() })
            }
        }

        it("throws if no assertion is made") {
            expect {
                expect{ }.toThrowFunLazy{}
            }.toThrow<IllegalStateException> { messageContains("There was not any assertion created") }
        }
    }

    describeFun(notToThrow.name) {
        val notToThrowFun = notToThrow.lambda
        context("no exception occurs") {
            it("does not throw"){
                expect{}.notToThrowFun()
            }
        }
        context("exception is thrown") {
            val notThrown : ThrowableThrown.Builder.() -> Unit = { notToThrowFun() }

            it("throws an AssertionError and shows message and stackTrace") {
                this@context.expectThrowsAssertionErrorAndMessageContainsRegex(notThrown,
                    UnsupportedOperationException(errMessage),
                    UnsupportedOperationException::class.simpleName + separator + messageAndStackTrace(errMessage),
                    "..."
                )
            }

            context("with a cause") {
                it("shows cause as extra hint") {
                    @Suppress("LABEL_NAME_CLASH")
                    this@context.expectThrowsAssertionErrorAndMessageContainsRegex(
                        notThrown,
                        UnsupportedOperationException("not supported", IllegalStateException(errMessage)),
                        UnsupportedOperationException::class.simpleName + separator + messageAndStackTrace("not supported"),
                        "\\s+\\Q$explanationBulletPoint\\E$causeDescr: ${IllegalStateException::class.fullName}" + messageAndStackTrace(
                            errMessage
                        )
                    )
                }

                it("with nested cause, shows both causes as extra hint") {
                    @Suppress("LABEL_NAME_CLASH")
                    this@context.expectThrowsAssertionErrorAndMessageContainsRegex(
                        notThrown,
                        UnsupportedOperationException(
                            "not supported",
                            RuntimeException("io", IllegalStateException(errMessage))
                        ),
                        UnsupportedOperationException::class.simpleName + separator + messageAndStackTrace("not supported"),
                        "\\s+\\Q$explanationBulletPoint\\E$causeDescr: ${RuntimeException::class.fullName}" + messageAndStackTrace(
                            "io"
                        ),
                        "\\s+\\Q$explanationBulletPoint\\E$causeDescr: ${IllegalStateException::class.fullName}" + messageAndStackTrace(
                            errMessage
                        )
                    )
                }
            }
        }
    }

    describeFun("${messageFeature.name} feature") {
        val messageFun = messageFeature.lambda
        checkNarrowingAssertion<Throwable>("it throws an AssertionError if the ${Throwable::message.name} is null", { message ->
            val throwable = IllegalArgumentException()
            expect {
                assert(throwable).message()
            }.toThrow<AssertionError> {
                messageContains(
                    DescriptionAnyAssertion.IS_A.getDefault(),
                    String::class.fullName
                )
            }
        }, { messageFun().toBe("hello") })

        context("it allows to define an assertion for the ${Throwable::message.name} if it is not null") {
            val throwable = IllegalArgumentException("oh no")
            checkNarrowingAssertion<Throwable>("it throws an AssertionError if the assertion does not hold", { messageWithCheck ->
                expect {
                    assert(throwable).messageWithCheck()
                }.toThrow<AssertionError>()
            }, {  messageFun().contains("hello") })

            checkNarrowingAssertion<Throwable>("it does not throw an exception if the assertion holds", { messageWithCheck ->
                assert(throwable).messageWithCheck()
            }, { messageFun().contains("oh") })
        }
    }

    describeFun(message.name) {
        val messageFun = message.lambda
        checkNarrowingAssertion<Throwable>("it throws an AssertionError if the ${Throwable::message.name} is null", { message ->
            val throwable = IllegalArgumentException()
            expect {
                assert(throwable).message()
            }.toThrow<AssertionError> {
                messageContains(
                    DescriptionAnyAssertion.IS_A.getDefault(),
                    String::class.fullName
                )
            }
        }, { messageFun { toBe("hello")} })

        context("it allows to define an assertion for the ${Throwable::message.name} if it is not null") {
            val throwable = IllegalArgumentException("oh no")
            checkNarrowingAssertion<Throwable>("it throws an AssertionError if the assertion does not hold", { messageWithCheck ->
                expect {
                    assert(throwable).messageWithCheck()
                }.toThrow<AssertionError>()
            }, { messageFun { contains("hello") } })

            checkNarrowingAssertion<Throwable>("it does not throw an exception if the assertion holds", { messageWithCheck ->
                assert(throwable).messageWithCheck()
            }, { messageFun { contains("oh") } })
        }

        it("throws if no assertion is made") {
            expect {
                val throwable = IllegalArgumentException()
                assert(throwable).messageFun {  }
            }.toThrow<IllegalStateException> { messageContains("There was not any assertion created") }
        }
    }

    describeFun(messageContains.name) {

        checkNarrowingAssertion<Throwable>("it throws an AssertionError if the ${Throwable::message.name} is null", { messageContainsFun ->
            val throwable = IllegalArgumentException()
            expect {
                assert(throwable).messageContainsFun()
            }.toThrow<AssertionError> {
                messageContains(DescriptionAnyAssertion.IS_A.getDefault(), String::class.fullName)
            }
        }, { (messageContains.lambda)(1, arrayOf(2.3, 'z', "hello")) })


        context("it allows to define an assertion for the ${Throwable::message.name} if it is not null") {
            val throwable = IllegalArgumentException("1 2.3 z hello")
            checkNarrowingAssertion<Throwable>("it throws an AssertionError if the assertion does not hold", { messageContains ->
                expect {
                    assert(throwable).messageContains()
                }.toThrow<AssertionError>()
            }, { (messageContains.lambda)("nada", arrayOf()) })

            checkNarrowingAssertion<Throwable>("it does not throw an exception if the assertion holds", { messageWithCheck ->
                assert(throwable).messageWithCheck()
            }, { (messageContains.lambda)(1, arrayOf(2.3, 'z', "hello")) })


            checkNarrowingAssertion<Throwable>("it throws an IllegalArgumentException if an object is passed", { messageContains ->
                expect {
                    assert(throwable).messageContains()
                }.toThrow<IllegalArgumentException>()
            }, { (messageContains.lambda)(Pair(1,2), arrayOf()) })
        }

    }


})
