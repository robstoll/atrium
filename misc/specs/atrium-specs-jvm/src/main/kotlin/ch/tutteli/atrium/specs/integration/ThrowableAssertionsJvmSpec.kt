@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)

package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.containsRegex
import ch.tutteli.atrium.api.fluent.en_GB.message
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.api.verbs.internal.expectOld
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.specs.checkGenericNarrowingAssertion
import ch.tutteli.atrium.specs.describeFunTemplate
import ch.tutteli.atrium.specs.lineSeperator
import ch.tutteli.atrium.translations.DescriptionThrowableAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite
import java.io.IOException

abstract class ThrowableAssertionsJvmSpec(
    toThrowTriple: Triple<String,
        ThrowableThrown.Builder.() -> Unit,
        ThrowableThrown.Builder.(assertionCreator: Expect<IllegalArgumentException>.() -> Unit) -> Unit
        >,
    notToThrowPair: Pair<String, ThrowableThrown.Builder.() -> Expect<Nothing?>>,
    listBulletPoint: String,
    explanationBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val (toThrow, toThrowFun, toThrowFunLazy) = toThrowTriple
    val (notToThrow, notThrowFun) = notToThrowPair

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
            expectOld { ({ throw throwable })() }.toThrowFun()
        }.toThrow<AssertionError> {
            message {
                containsRegex(pattern, *otherPatterns)
            }
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
            "\\s+\\Q$listBulletPoint\\E${ThrowableAssertionsJvmSpec::class.fullName}"



    describeFun(toThrow) {

        context("wrong exception with suppressed") {

            checkToThrow("shows all suppressed as extra hint", { doToThrow ->
                val ex = UnsupportedOperationException("not supported")
                ex.addSuppressed(IllegalStateException("not good"))
                ex.addSuppressed(IllegalArgumentException(errMessage))
                expectThrowsAssertionErrorAndMessageContainsRegex(
                    doToThrow,
                    ex,
                    UnsupportedOperationException::class.simpleName + separator + messageAndStackTrace("not supported"),
                    "\\s+\\Q$explanationBulletPoint\\E$supressedDescr: ${IllegalStateException::class.fullName}" + messageAndStackTrace(
                        "not good"
                    ),
                    "\\s+\\Q$explanationBulletPoint\\E$supressedDescr: ${IllegalArgumentException::class.fullName}" + messageAndStackTrace(
                        errMessage
                    )
                )
            }, { toThrowFunLazy { message { toBe("hello") } } }, { toThrowFun() })
        }
    }

    describeFun(notToThrow) {

        context("exception is thrown with suppressed") {
            val notThrown: ThrowableThrown.Builder.() -> Unit = { notThrowFun() }

            it("shows all suppressed as extra hint") {
                val ex = UnsupportedOperationException("not supported")
                ex.addSuppressed(IllegalStateException("not good"))
                ex.addSuppressed(IllegalArgumentException(errMessage))
                this@context.expectThrowsAssertionErrorAndMessageContainsRegex(
                    notThrown,
                    ex,
                    UnsupportedOperationException::class.simpleName + separator + messageAndStackTrace("not supported"),
                    "\\s+\\Q$explanationBulletPoint\\E$supressedDescr: ${IllegalStateException::class.fullName}" + messageAndStackTrace(
                        "not good"
                    ),
                    "\\s+\\Q$explanationBulletPoint\\E$supressedDescr: ${IllegalArgumentException::class.fullName}" + messageAndStackTrace(
                        errMessage
                    )
                )
            }
            it("with nested cause, shows both causes as extra hint") {
                val ex = UnsupportedOperationException("not supported")
                ex.addSuppressed(IOException("io", IllegalStateException(errMessage)))
                this@context.expectThrowsAssertionErrorAndMessageContainsRegex(
                    notThrown,
                    ex,
                    UnsupportedOperationException::class.simpleName + separator + messageAndStackTrace("not supported"),
                    "\\s+\\Q$explanationBulletPoint\\E$supressedDescr: ${IOException::class.fullName}" + messageAndStackTrace(
                        "io"
                    ),
                    "\\s+\\Q$explanationBulletPoint\\E$causeDescr: ${IllegalStateException::class.fullName}" + messageAndStackTrace(
                        errMessage
                    )
                )
            }
        }
    }
})
