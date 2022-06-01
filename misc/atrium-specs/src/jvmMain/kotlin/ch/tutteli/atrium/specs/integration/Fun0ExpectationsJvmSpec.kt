package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionThrowableExpectation
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite
import java.io.IOException

abstract class Fun0ExpectationsJvmSpec(
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

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    val messageDescr = DescriptionThrowableExpectation.OCCURRED_EXCEPTION_MESSAGE.getDefault()
    val stackTraceDescr = DescriptionThrowableExpectation.OCCURRED_EXCEPTION_STACKTRACE.getDefault()
    val causeDescr = DescriptionThrowableExpectation.OCCURRED_EXCEPTION_CAUSE.getDefault()
    val supressedDescr = DescriptionThrowableExpectation.OCCURRED_EXCEPTION_SUPPRESSED.getDefault()
    val separator = lineSeparator

    val errMessage = "oho... error occurred"

    fun messageAndStackTrace(message: String) =
        "\\s+\\Q$explanatoryBulletPoint\\E$messageDescr: \"$message\".*$separator" +
            "\\s+\\Q$explanatoryBulletPoint\\E$stackTraceDescr: $separator" +
            "\\s+\\Q$listBulletPoint\\E${Fun0ExpectationsJvmSpec::class.fullName}"

    describeFun(toThrowFeature, toThrow, notToThrowFeature, notToThrow) {
        val toThrowFunctions = unifySignatures(toThrowFeature, toThrow)
        val notToThrowFunctions = unifySignatures(notToThrowFeature, notToThrow)

        context("wrong exception with suppressed") {
            val exceptionWithSuppressed = UnsupportedOperationException("not supported")
            exceptionWithSuppressed.addSuppressed(IllegalStateException("not good"))
            exceptionWithSuppressed.addSuppressed(IllegalArgumentException(errMessage))

            fun Expect<AssertionError>.expectSuppressedInReporting() =
                message {
                    toContainRegex(
                        UnsupportedOperationException::class.simpleName + separator + messageAndStackTrace("not supported"),
                        "\\s+\\Q$explanatoryBulletPoint\\E$supressedDescr: ${IllegalStateException::class.fullName}" +
                                messageAndStackTrace("not good"),
                        "\\s+\\Q$explanatoryBulletPoint\\E$supressedDescr: ${IllegalArgumentException::class.fullName}" +
                                messageAndStackTrace(errMessage)
                    )
                }

            toThrowFunctions.forEach { (name, toThrowFun, hasExtraHint) ->
                it("$name - shows all suppressed as extra hint" + showsSubAssertionIf(hasExtraHint)) {

                    expect {
                        expect<() -> Any?> {
                            throw exceptionWithSuppressed
                        }.toThrowFun { message.toEqual("hello") }
                    }.toThrow<AssertionError> {
                        expectSuppressedInReporting()
                        if (hasExtraHint) messageToContain("$toEqualDescr: \"hello\"")
                    }
                }
            }

            notToThrowFunctions.forEach { (name, notToThrowFun, hasExtraHint) ->
                it("$name - shows all suppressed as extra hint" + showsSubAssertionIf(hasExtraHint)) {
                    expect {
                        expect<() -> Int> {
                            throw exceptionWithSuppressed
                        }.notToThrowFun { toEqual(2) }
                    }.toThrow<AssertionError> {
                        expectSuppressedInReporting()
                        if (hasExtraHint) messageToContain("$toEqualDescr: 2")
                    }
                }
            }


            context("with nested cause") {
                val exceptionWithSuppressedWhichHasCause = UnsupportedOperationException("not supported")
                exceptionWithSuppressedWhichHasCause.addSuppressed(IOException("io", IllegalStateException(errMessage)))

                fun Expect<AssertionError>.expectSuppressedAndCauseInReporting() =
                    message {
                        toContainRegex(
                            UnsupportedOperationException::class.simpleName + separator + messageAndStackTrace("not supported"),
                            "\\s+\\Q$explanatoryBulletPoint\\E$supressedDescr: ${IOException::class.fullName}" +
                                    messageAndStackTrace("io"),
                            "\\s+\\Q$explanatoryBulletPoint\\E$causeDescr: ${IllegalStateException::class.fullName}" +
                                    messageAndStackTrace(errMessage)
                        )

                    }

                toThrowFunctions.forEach { (name, toThrowFun, hasExtraHint) ->
                    it("$name -shows suppressed including cause as extra hint" + showsSubAssertionIf(hasExtraHint)) {

                        expect {
                            expect<() -> Any?> {
                                throw exceptionWithSuppressedWhichHasCause
                            }.toThrowFun { message.toEqual("hello") }
                        }.toThrow<AssertionError> {
                            expectSuppressedAndCauseInReporting()
                            if (hasExtraHint) messageToContain("$toEqualDescr: \"hello\"")
                        }
                    }
                }

                notToThrowFunctions.forEach { (name, notToThrowFun, hasExtraHint) ->
                    it("$name - shows suppressed including cause as extra hint" + showsSubAssertionIf(hasExtraHint)) {
                        expect {
                            expect<() -> Int> {
                                throw exceptionWithSuppressedWhichHasCause
                            }.notToThrowFun { toEqual(2) }
                        }.toThrow<AssertionError> {
                            expectSuppressedAndCauseInReporting()
                            if (hasExtraHint) messageToContain("$toEqualDescr: 2")
                        }
                    }
                }
            }
        }
    }
})
