package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import ch.tutteli.atrium.translations.DescriptionFunLikeAssertion
import ch.tutteli.atrium.translations.DescriptionThrowableAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class Fun0AssertionsSpec(
    toThrowFeature: Feature0<out () -> Any?, IllegalArgumentException>,
    toThrow: Feature1<out () -> Any?, Expect<IllegalArgumentException>.() -> Unit, IllegalArgumentException>,
    notToThrowFeature: Feature0<() -> Int, Int>,
    notToThrow: Feature1<() -> Int, Expect<Int>.() -> Unit, Int>,
    listBulletPoint: String,
    explanationBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<() -> Any?>(describePrefix,
        toThrowFeature.forSubjectLess().adjustName { "$it feature" },
        toThrow.forSubjectLess { messageContains("bla") }
    ) {})

    include(object : SubjectLessSpec<() -> Int>(describePrefix,
        notToThrowFeature.forSubjectLess().adjustName { "$it feature" },
        notToThrow.forSubjectLess { toBe(2) }
    ) {})

    include(object : AssertionCreatorSpec<() -> Any?>(
        describePrefix, { throw IllegalArgumentException("bla") },
        assertionCreatorSpecTriple(
            toThrow.name,
            "bla",
            { apply { toThrow.invoke(this) { messageContains("bla") } } },
            { apply { toThrow.invoke(this) {} } }
        )
    ) {})

    include(object : AssertionCreatorSpec<() -> Int>(
        describePrefix, { 1 },
        assertionCreatorSpecTriple(
            notToThrow.name,
            "$toBeDescr: 1",
            { apply { notToThrow.invoke(this) { toBe(1) } } },
            { apply { notToThrow.invoke(this) {} } }
        )
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    fun Suite.checkToThrow(
        description: String,
        act: (Expect<out () -> Any?>.() -> Unit) -> Unit,
        lazy: (Expect<out () -> Any?>.() -> Unit),
        immediate: (Expect<out () -> Any?>.() -> Unit)
    ) {
        checkGenericNarrowingAssertion(description, act, lazy, "immediate" to immediate)
    }

    fun <R> expectThrowsAssertionErrorAndMessageContainsRegex(
        toThrowFun: Expect<() -> R>.() -> Unit,
        throwable: Throwable,
        pattern: String, vararg otherPatterns: String
    ) {
        expect {
            val act: () -> R = { throw throwable }
            expect(act).toThrowFun()
        }.toThrow<AssertionError> {
            message { containsRegex(pattern, *otherPatterns) }
        }
    }

    val isADescr = DescriptionAnyAssertion.IS_A.getDefault()
    val messageDescr = DescriptionThrowableAssertion.OCCURRED_EXCEPTION_MESSAGE.getDefault()
    val stackTraceDescr = DescriptionThrowableAssertion.OCCURRED_EXCEPTION_STACKTRACE.getDefault()
    val causeDescr = DescriptionThrowableAssertion.OCCURRED_EXCEPTION_CAUSE.getDefault()
    val separator = lineSeperator

    val errMessage = "oho... error occurred"
    fun messageAndStackTrace(message: String) =
        "\\s+\\Q$explanationBulletPoint\\E$messageDescr: \"$message\".*$separator" +
            "\\s+\\Q$explanationBulletPoint\\E$stackTraceDescr: $separator" +
            "\\s+\\Q$listBulletPoint\\E${Fun0AssertionsSpec::class.fullName}"

    describeFun("${toThrowFeature.name} feature and ${toThrow.name}") {
        val toThrowFeatureFun = toThrowFeature.lambda
        val toThrowFun = toThrow.lambda

        checkToThrow("it throws an AssertionError when no exception occurs", { doToThrow ->
            expect {
                expect { /* no exception occurs */ 1 }.doToThrow()
            }.toThrow<AssertionError> {
                message {
                    contains.exactly(1).regex(
                        "${DescriptionFunLikeAssertion.THROWN_EXCEPTION_WHEN_CALLED.getDefault()}: " +
                            DescriptionFunLikeAssertion.NO_EXCEPTION_OCCURRED.getDefault(),
                        "$isADescr: ${IllegalArgumentException::class.simpleName}"
                    )
                }
            }
        }, { toThrowFun { toBe(IllegalArgumentException("what")) } }, { toThrowFeatureFun() })


        checkToThrow(
            "it allows to define assertions for the Throwable if the correct exception is thrown",
            { toThrowWithCheck ->
                expect {
                    throw IllegalArgumentException("hello")
                }.toThrowWithCheck()
            },
            { toThrowFun { message { toBe("hello") } } }, { toThrowFeatureFun().message.toBe("hello") })

        context("wrong exception") {

            checkToThrow("it throws an AssertionError and shows message and stacktrace as extra hint", { doToThrow ->
                expectThrowsAssertionErrorAndMessageContainsRegex(
                    doToThrow,
                    UnsupportedOperationException(errMessage),
                    "$isADescr:.+" + IllegalArgumentException::class.fullName,
                    UnsupportedOperationException::class.simpleName + separator + messageAndStackTrace(errMessage)
                )
            }, { toThrowFun { message { toBe("hello") } } }, { toThrowFeatureFun().message.toBe("hello") })

            context("with a cause") {

                checkToThrow("shows cause as extra hint", { doToThrow ->
                    expectThrowsAssertionErrorAndMessageContainsRegex(
                        doToThrow,
                        UnsupportedOperationException("not supported", IllegalStateException(errMessage)),
                        UnsupportedOperationException::class.simpleName + separator +
                            messageAndStackTrace("not supported"),
                        "\\s+\\Q$explanationBulletPoint\\E$causeDescr: ${IllegalStateException::class.fullName}" +
                            messageAndStackTrace(errMessage)
                    )
                }, { toThrowFun { message { toBe("hello") } } }, { toThrowFeatureFun() })

                checkToThrow(
                    "with nested cause, shows both causes as extra hint",
                    { doToThrow ->
                        expectThrowsAssertionErrorAndMessageContainsRegex(
                            doToThrow,
                            UnsupportedOperationException(
                                "not supported",
                                RuntimeException("io", IllegalStateException(errMessage))
                            ),
                            UnsupportedOperationException::class.simpleName + separator +
                                messageAndStackTrace("not supported"),
                            "\\s+\\Q$explanationBulletPoint\\E$causeDescr: ${RuntimeException::class.fullName}" +
                                messageAndStackTrace("io"),
                            "\\s+\\Q$explanationBulletPoint\\E$causeDescr: ${IllegalStateException::class.fullName}" +
                                messageAndStackTrace(errMessage)
                        )
                    },
                    { toThrowFun { message { toBe("hello") } } },
                    { toThrowFeatureFun().message.toBe("hello") })
            }
        }
    }

    describeFun("${notToThrowFeature.name} feature") {
        val notToThrowFeatureFun = notToThrowFeature.lambda

        context("no exception occurs") {
            it("does not throw, allows to make a sub assertion") {
                expect { 1 }.notToThrowFeatureFun().toBe(1)
            }
        }
        context("exception is thrown") {
            val notThrown: Expect<() -> Int>.() -> Unit = { notToThrowFeatureFun().toBe(1) }

            it("throws an AssertionError and shows message and stackTrace as well as intended sub assertion") {
                expectThrowsAssertionErrorAndMessageContainsRegex(
                    notThrown,
                    UnsupportedOperationException(errMessage),
                    UnsupportedOperationException::class.simpleName + separator + messageAndStackTrace(errMessage),
                    "..."
                )
            }

            context("with a cause") {
                it("shows cause as extra hint") {
                    expectThrowsAssertionErrorAndMessageContainsRegex(
                        notThrown,
                        UnsupportedOperationException("not supported", IllegalStateException(errMessage)),
                        UnsupportedOperationException::class.simpleName + separator +
                            messageAndStackTrace("not supported"),
                        "\\s+\\Q$explanationBulletPoint\\E$causeDescr: ${IllegalStateException::class.fullName}" +
                            messageAndStackTrace(errMessage)
                    )
                }

                it("with nested cause, shows both causes as extra hint") {
                    expectThrowsAssertionErrorAndMessageContainsRegex(
                        notThrown,
                        UnsupportedOperationException(
                            "not supported",
                            RuntimeException("io", IllegalStateException(errMessage))
                        ),
                        UnsupportedOperationException::class.simpleName + separator +
                            messageAndStackTrace("not supported"),
                        "\\s+\\Q$explanationBulletPoint\\E$causeDescr: ${RuntimeException::class.fullName}" +
                            messageAndStackTrace("io"),
                        "\\s+\\Q$explanationBulletPoint\\E$causeDescr: ${IllegalStateException::class.fullName}" +
                            messageAndStackTrace(errMessage)
                    )
                }
            }
        }
    }

    describeFun(notToThrow.name) {
        val notToThrowFun = notToThrow.lambda

        context("no exception occurs") {
            it("does not throw, allows to make a sub assertion") {
                expect { 1 }.notToThrowFun { toBe(1) }
            }
        }
        context("exception is thrown") {
            val notThrown: Expect<() -> Int>.() -> Unit = { notToThrowFun { toBe(1) } }

            it("throws an AssertionError and shows message and stackTrace as well as intended sub assertion") {
                expectThrowsAssertionErrorAndMessageContainsRegex(
                    notThrown,
                    UnsupportedOperationException(errMessage),
                    UnsupportedOperationException::class.simpleName + separator + messageAndStackTrace(errMessage),
                    "...",
                    "$toBeDescr: 1"
                )
            }

            context("with a cause") {
                it("shows cause as extra hint") {
                    expectThrowsAssertionErrorAndMessageContainsRegex(
                        notThrown,
                        UnsupportedOperationException("not supported", IllegalStateException(errMessage)),
                        UnsupportedOperationException::class.simpleName + separator +
                            messageAndStackTrace("not supported"),
                        "\\s+\\Q$explanationBulletPoint\\E$causeDescr: ${IllegalStateException::class.fullName}" +
                            messageAndStackTrace(errMessage)
                    )
                }

                it("with nested cause, shows both causes as extra hint") {
                    expectThrowsAssertionErrorAndMessageContainsRegex(
                        notThrown,
                        UnsupportedOperationException(
                            "not supported",
                            RuntimeException("io", IllegalStateException(errMessage))
                        ),
                        UnsupportedOperationException::class.simpleName + separator +
                            messageAndStackTrace("not supported"),
                        "\\s+\\Q$explanationBulletPoint\\E$causeDescr: ${RuntimeException::class.fullName}" +
                            messageAndStackTrace("io"),
                        "\\s+\\Q$explanationBulletPoint\\E$causeDescr: ${IllegalStateException::class.fullName}" +
                            messageAndStackTrace(errMessage)
                    )
                }
            }
        }
    }
})
