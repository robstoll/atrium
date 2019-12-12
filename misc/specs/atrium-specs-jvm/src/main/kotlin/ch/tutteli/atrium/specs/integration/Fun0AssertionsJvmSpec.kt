@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)

package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.containsRegex
import ch.tutteli.atrium.api.fluent.en_GB.message
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionThrowableAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite
import java.io.IOException

abstract class Fun0AssertionsJvmSpec(
    toThrowFeature: Feature0<out () -> Any?, IllegalArgumentException>,
    toThrow: Feature1<out () -> Any?, Expect<IllegalArgumentException>.() -> Unit, IllegalArgumentException>,
    notToThrowFeature: Feature0<() -> Int, Int>,
    notToThrow: Feature1<() -> Int, Expect<Int>.() -> Unit, Int>,
    listBulletPoint: String,
    explanationBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : Spek({

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

    val messageDescr = DescriptionThrowableAssertion.OCCURRED_EXCEPTION_MESSAGE.getDefault()
    val stackTraceDescr = DescriptionThrowableAssertion.OCCURRED_EXCEPTION_STACKTRACE.getDefault()
    val causeDescr = DescriptionThrowableAssertion.OCCURRED_EXCEPTION_CAUSE.getDefault()
    val supressedDescr = DescriptionThrowableAssertion.OCCURRED_EXCEPTION_SUPPRESSED.getDefault()
    val separator = lineSeperator

    val errMessage = "oho... error occurred"

    fun messageAndStackTrace(message: String) =
        "\\s+\\Q$explanationBulletPoint\\E$messageDescr: \"$message\".*$separator" +
            "\\s+\\Q$explanationBulletPoint\\E$stackTraceDescr: $separator" +
            "\\s+\\Q$listBulletPoint\\E${Fun0AssertionsJvmSpec::class.fullName}"

    describeFun("${toThrowFeature.name} feature and ${toThrow.name}") {
        val toThrowFeatureFun = toThrowFeature.lambda
        val toThrowFun = toThrow.lambda

        context("wrong exception with suppressed") {

            checkToThrow("shows all suppressed as extra hint", { doToThrow ->
                val ex = UnsupportedOperationException("not supported")
                ex.addSuppressed(IllegalStateException("not good"))
                ex.addSuppressed(IllegalArgumentException(errMessage))
                expectThrowsAssertionErrorAndMessageContainsRegex(
                    doToThrow,
                    ex,
                    UnsupportedOperationException::class.simpleName + separator + messageAndStackTrace("not supported"),
                    "\\s+\\Q$explanationBulletPoint\\E$supressedDescr: ${IllegalStateException::class.fullName}" +
                        messageAndStackTrace("not good"),
                    "\\s+\\Q$explanationBulletPoint\\E$supressedDescr: ${IllegalArgumentException::class.fullName}" +
                        messageAndStackTrace(errMessage)
                )
            }, { toThrowFun { message { toBe("hello") } } }, { toThrowFeatureFun() })
        }
    }

    describeFun("${notToThrowFeature.name} feature") {
        val notToThrowFeatureFun = notToThrowFeature.lambda

        context("exception is thrown with suppressed") {
            val notThrown: Expect<() -> Int>.() -> Unit = { notToThrowFeatureFun().toBe(1) }

            it("shows all suppressed as extra hint") {
                val ex = UnsupportedOperationException("not supported")
                ex.addSuppressed(IllegalStateException("not good"))
                ex.addSuppressed(IllegalArgumentException(errMessage))
                expectThrowsAssertionErrorAndMessageContainsRegex(
                    notThrown,
                    ex,
                    UnsupportedOperationException::class.simpleName + separator + messageAndStackTrace("not supported"),
                    "\\s+\\Q$explanationBulletPoint\\E$supressedDescr: ${IllegalStateException::class.fullName}" +
                        messageAndStackTrace("not good"),
                    "\\s+\\Q$explanationBulletPoint\\E$supressedDescr: ${IllegalArgumentException::class.fullName}" +
                        messageAndStackTrace(errMessage)
                )
            }
            it("with nested cause, shows both causes as extra hint") {
                val ex = UnsupportedOperationException("not supported")
                ex.addSuppressed(IOException("io", IllegalStateException(errMessage)))
                expectThrowsAssertionErrorAndMessageContainsRegex(
                    notThrown,
                    ex,
                    UnsupportedOperationException::class.simpleName + separator + messageAndStackTrace("not supported"),
                    "\\s+\\Q$explanationBulletPoint\\E$supressedDescr: ${IOException::class.fullName}" +
                        messageAndStackTrace("io"),
                    "\\s+\\Q$explanationBulletPoint\\E$causeDescr: ${IllegalStateException::class.fullName}" +
                        messageAndStackTrace(errMessage)
                )
            }
        }
    }

    describeFun(notToThrow.name) {
        val notToThrowFun = notToThrow.lambda

        context("exception is thrown with suppressed") {
            val notThrown: Expect<() -> Int>.() -> Unit = { notToThrowFun { toBe(1) } }

            it("shows all suppressed as extra hint") {
                val ex = UnsupportedOperationException("not supported")
                ex.addSuppressed(IllegalStateException("not good"))
                ex.addSuppressed(IllegalArgumentException(errMessage))
                expectThrowsAssertionErrorAndMessageContainsRegex(
                    notThrown,
                    ex,
                    UnsupportedOperationException::class.simpleName + separator + messageAndStackTrace("not supported"),
                    "\\s+\\Q$explanationBulletPoint\\E$supressedDescr: ${IllegalStateException::class.fullName}" +
                        messageAndStackTrace("not good"),
                    "\\s+\\Q$explanationBulletPoint\\E$supressedDescr: ${IllegalArgumentException::class.fullName}" +
                        messageAndStackTrace(errMessage)
                )
            }
            it("with nested cause, shows both causes as extra hint") {
                val ex = UnsupportedOperationException("not supported")
                ex.addSuppressed(IOException("io", IllegalStateException(errMessage)))
                expectThrowsAssertionErrorAndMessageContainsRegex(
                    notThrown,
                    ex,
                    UnsupportedOperationException::class.simpleName + separator + messageAndStackTrace("not supported"),
                    "\\s+\\Q$explanationBulletPoint\\E$supressedDescr: ${IOException::class.fullName}" +
                        messageAndStackTrace("io"),
                    "\\s+\\Q$explanationBulletPoint\\E$causeDescr: ${IllegalStateException::class.fullName}" +
                        messageAndStackTrace(errMessage)
                )
            }
        }
    }
})
