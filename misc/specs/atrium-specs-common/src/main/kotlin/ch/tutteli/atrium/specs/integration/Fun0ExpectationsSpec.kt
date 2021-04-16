package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionFunLikeAssertion
import ch.tutteli.atrium.translations.DescriptionThrowableAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class Fun0ExpectationsSpec(
    toThrowFeature: Feature0<out () -> Any?, IllegalArgumentException>,
    toThrow: Feature1<out () -> Any?, Expect<IllegalArgumentException>.() -> Unit, IllegalArgumentException>,
    notToThrowFeature: Feature0<() -> Int, Int>,
    notToThrow: Feature1<() -> Int, Expect<Int>.() -> Unit, Int>,
    describePrefix: String = "[Atrium] "
) : Spek({

    @Suppress("NAME_SHADOWING")
    val toThrow = toThrow.adjustName { it.substringBefore(" (feature)") }

    @Suppress("NAME_SHADOWING")
    val notToThrow = notToThrow.adjustName { it.substringBefore(" (feature)") }

    include(object : SubjectLessSpec<() -> Any?>(
        "$describePrefix[toThrow] ",
        toThrowFeature.forSubjectLess().adjustName { "$it feature" },
        toThrow.forSubjectLess { messageContains("bla") }
    ) {})

    include(object : SubjectLessSpec<() -> Int>(describePrefix,
        notToThrowFeature.forSubjectLess().adjustName { "$it feature" },
        notToThrow.forSubjectLess { toEqual(2) }
    ) {})

    include(object : AssertionCreatorSpec<() -> Any?>(
        "$describePrefix[toThrow] ", { throw IllegalArgumentException("bla") },
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
            { apply { notToThrow.invoke(this) { toEqual(1) } } },
            { apply { notToThrow.invoke(this) {} } }
        )
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    val messageDescr = DescriptionThrowableAssertion.OCCURRED_EXCEPTION_MESSAGE.getDefault()
    val stackTraceDescr = DescriptionThrowableAssertion.OCCURRED_EXCEPTION_STACKTRACE.getDefault()
    val causeDescr = DescriptionThrowableAssertion.OCCURRED_EXCEPTION_CAUSE.getDefault()
    val separator = lineSeparator

    val errMessage = "oho... error occurred"
    fun messageAndStackTrace(message: String) =
        "\\s+\\Q$explanatoryBulletPoint\\E$messageDescr: \"$message\".*$separator" +
            "\\s+\\Q$explanatoryBulletPoint\\E$stackTraceDescr: $separator" +
            "\\s+\\Q$listBulletPoint\\E${Fun0ExpectationsSpec::class.fullName}"

    describeFun(toThrowFeature, toThrow, notToThrowFeature, notToThrow) {
        val toThrowFunctions = unifySignatures(toThrowFeature, toThrow)
        val notToThrowFunctions = unifySignatures(notToThrowFeature, notToThrow)

        context("no exception occurs") {
            toThrowFunctions.forEach { (name, toThrowFun, hasExtraHint) ->
                it("$name - throws an AssertionError" + showsSubAssertionIf(hasExtraHint)) {
                    expect {
                        expect<() -> Any?> { /* no exception occurs */ 1 }.toThrowFun {
                            toEqual(
                                IllegalArgumentException(
                                    "what"
                                )
                            )
                        }
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).values(
                                "${DescriptionFunLikeAssertion.THROWN_EXCEPTION_WHEN_CALLED.getDefault()}: " +
                                    DescriptionFunLikeAssertion.NO_EXCEPTION_OCCURRED.getDefault(),
                                "$isADescr: ${IllegalArgumentException::class.simpleName}"
                            )
                            if (hasExtraHint) toContain("$toBeDescr: ${IllegalArgumentException::class.fullName}")
                        }
                    }
                }
            }

            notToThrowFunctions.forEach { (name, notToThrowFun, _) ->
                it("$name - does not throw, allows to make a sub assertion") {
                    expect { 1 }.notToThrowFun { toEqual(1) }
                }
            }

            notToThrowFunctions.forEach { (name, notToThrowFun, _) ->
                it("$name - shows return value in case sub-assertion fails") {
                    expect {
                        expect { 123456789 }.notToThrowFun { toEqual(1) }
                    }.toThrow<AssertionError>() {
                        messageContains("123456789")
                    }
                }
            }
        }

        context("exception is thrown") {

            val wrongException = UnsupportedOperationException(errMessage)

            toThrowFunctions.forEach { (name, toThrowFun, hasExtraHint) ->
                it("$name - allows to define assertions for the Throwable if the correct exception is thrown") {
                    expect<() -> Any?> {
                        throw IllegalArgumentException("hello")
                    }.toThrowFun { message.toEqual("hello") }
                }

                it(
                    "$name - throws an AssertionError in case of a wrong exception and shows message and stacktrace as extra hint" +
                        showsSubAssertionIf(hasExtraHint)
                ) {
                    expect {
                        expect<() -> Any?> {
                            throw wrongException
                        }.toThrowFun { message.toEqual("hello") }
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "$isADescr:.+" + IllegalArgumentException::class.fullName,
                                UnsupportedOperationException::class.simpleName + separator +
                                    messageAndStackTrace(errMessage)
                            )
                            if (hasExtraHint) toContain("$toBeDescr: \"hello\"")
                        }
                    }
                }
            }

            notToThrowFunctions.forEach { (name, notToThrowFun, hasExtraHint) ->
                it(
                    "$name - throws an AssertionError and shows message and stacktrace as extra hint" +
                        showsSubAssertionIf(hasExtraHint)
                ) {
                    expect {
                        expect<() -> Int> {
                            throw wrongException
                        }.notToThrowFun { toEqual(2) }
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "\\Qinvoke()\\E: ${
                                    DescriptionFunLikeAssertion.THREW.getDefault()
                                        .format(UnsupportedOperationException::class.fullName)
                                }",
                                UnsupportedOperationException::class.simpleName + separator +
                                    messageAndStackTrace(errMessage)
                            )
                            if (hasExtraHint) toContain("$toBeDescr: 2")
                        }
                    }
                }
            }

            context("with a cause") {

                val exceptionWithCause =
                    UnsupportedOperationException("not supported", IllegalStateException(errMessage))

                fun Expect<AssertionError>.expectCauseInReporting() =
                    message {
                        toContainRegex(
                            UnsupportedOperationException::class.simpleName + separator +
                                messageAndStackTrace("not supported"),
                            "\\s+\\Q$explanatoryBulletPoint\\E$causeDescr: ${IllegalStateException::class.fullName}" +
                                messageAndStackTrace(errMessage)
                        )

                    }

                toThrowFunctions.forEach { (name, toThrowFun, hasExtraHint) ->
                    it(
                        "$name - shows cause as extra hint in case of a wrong exception" +
                            showsSubAssertionIf(hasExtraHint)
                    ) {
                        expect {
                            expect<() -> Any?> {
                                throw exceptionWithCause
                            }.toThrowFun { message.toEqual("hello") }
                        }.toThrow<AssertionError> {
                            expectCauseInReporting()
                            if (hasExtraHint) messageContains("$toBeDescr: \"hello\"")
                        }
                    }
                }

                notToThrowFunctions.forEach { (name, notToThrowFun, hasExtraHint) ->
                    it("$name - shows cause as extra hint" + showsSubAssertionIf(hasExtraHint)) {
                        expect {
                            expect<() -> Int> {
                                throw exceptionWithCause
                            }.notToThrowFun { toEqual(2) }
                        }.toThrow<AssertionError> {
                            expectCauseInReporting()
                            if (hasExtraHint) messageContains("$toBeDescr: 2")
                        }
                    }
                }

                context("with nested cause") {
                    val exceptionWithNestedCause = UnsupportedOperationException(
                        "not supported",
                        RuntimeException("io", IllegalStateException(errMessage))
                    )

                    fun Expect<AssertionError>.expectCauseAndNestedInReporting() =
                        message {
                            toContainRegex(
                                UnsupportedOperationException::class.simpleName + separator +
                                    messageAndStackTrace("not supported"),
                                "\\s+\\Q$explanatoryBulletPoint\\E$causeDescr: ${RuntimeException::class.fullName}" +
                                    messageAndStackTrace("io"),
                                "\\s+\\Q$explanatoryBulletPoint\\E$causeDescr: ${IllegalStateException::class.fullName}" +
                                    messageAndStackTrace(errMessage)
                            )
                        }


                    toThrowFunctions.forEach { (name, toThrowFun, hasExtraHint) ->
                        it("$name - shows both causes as extra hint" + showsSubAssertionIf(hasExtraHint)) {
                            expect {
                                expect<() -> Any?> {
                                    throw exceptionWithNestedCause
                                }.toThrowFun { message.toEqual("hello") }
                            }.toThrow<AssertionError> {
                                expectCauseAndNestedInReporting()
                                if (hasExtraHint) messageContains("$toBeDescr: \"hello\"")
                            }
                        }
                    }

                    notToThrowFunctions.forEach { (name, notToThrowFun, hasExtraHint) ->
                        it("$name - shows both causes as extra hint" + showsSubAssertionIf(hasExtraHint)) {
                            expect {
                                expect<() -> Int> {
                                    throw exceptionWithNestedCause
                                }.notToThrowFun { toEqual(2) }
                            }.toThrow<AssertionError> {
                                expectCauseAndNestedInReporting()
                                if (hasExtraHint) messageContains("$toBeDescr: 2")
                            }
                        }
                    }
                }
            }
        }
    }
})
