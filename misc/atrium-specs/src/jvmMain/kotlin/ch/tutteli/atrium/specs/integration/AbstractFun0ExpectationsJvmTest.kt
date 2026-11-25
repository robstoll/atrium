package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionThrowableExpectation
import java.io.IOException

@Suppress("FunctionName")
abstract class AbstractFun0ExpectationsJvmTest(
    private val toThrowFeatureSpec: Feature0<out () -> Any?, IllegalArgumentException>,
    private val toThrowSpec: Feature1<out () -> Any?, Expect<IllegalArgumentException>.() -> Unit, IllegalArgumentException>,
    private val notToThrowFeatureSpec: Feature0<() -> Int, Int>,
    private val notToThrowSpec: Feature1<() -> Int, Expect<Int>.() -> Unit, Int>
) : ExpectationFunctionBaseTest() {

    val messageDescr = DescriptionThrowableExpectation.OCCURRED_EXCEPTION_MESSAGE.getDefault()
    val stackTraceDescr = DescriptionThrowableExpectation.OCCURRED_EXCEPTION_STACKTRACE.getDefault()
    val causeDescr = DescriptionThrowableExpectation.OCCURRED_EXCEPTION_CAUSE.getDefault()
    val supressedDescr = DescriptionThrowableExpectation.OCCURRED_EXCEPTION_SUPPRESSED.getDefault()
    val separator = lineSeparator

    val errMessage = "oho... error occurred"

    fun messageAndStackTrace(message: String) =
        "\\s+\\Q$explanatoryBulletPoint\\E$messageDescr: \"$message\".*$separator" +
            "\\s+\\Q$explanatoryBulletPoint\\E$stackTraceDescr: $separator" +
            "\\s+\\Q$listBulletPoint\\E${AbstractFun0ExpectationsJvmTest::class.fullName}"

    @TestFactory
    fun toThrow__shows_all_suppressed_as_extra_hint() = testFactoryForFeatureNonFeature(
        toThrowFeatureSpec, toThrowSpec
    ) { name, toThrowFun, hasExtraHint ->
        it("$name - shows all suppressed as extra hint" + showsSubExpectationIf(hasExtraHint)) {
            expect {
                expect<() -> Any?> {
                    throw exceptionWithSuppressed()
                }.toThrowFun { message.toEqual("hello") }
            }.toThrow<AssertionError> {
                expectSuppressedInReporting()
                if (hasExtraHint) messageToContain("$toEqualDescr: \"hello\"")
            }
        }
    }

    @TestFactory
    fun notToThrow__shows_all_suppressed_as_extra_hint() = testFactoryForFeatureNonFeature(
        notToThrowFeatureSpec, notToThrowSpec
    ) { name, notToThrowFun, hasExtraHint ->
        it("$name - shows all suppressed as extra hint" + showsSubExpectationIf(hasExtraHint)) {
            expect {
                expect<() -> Int> {
                    throw exceptionWithSuppressed()
                }.notToThrowFun { toEqual(2) }
            }.toThrow<AssertionError> {
                expectSuppressedInReporting()
                if (hasExtraHint) messageToContain("$toEqualDescr: 2")
            }
        }
    }

    @TestFactory
    fun toThrow__shows_suppressed_including_cause_as_extra_hint() = testFactoryForFeatureNonFeature(
        toThrowFeatureSpec, toThrowSpec
    ) { name, toThrowFun, hasExtraHint ->
        it("$name - shows suppressed including cause as extra hint" + showsSubExpectationIf(hasExtraHint)) {
            expect {
                expect<() -> Any?> {
                    throw exceptionWithSuppressedWhichHasCause()
                }.toThrowFun { message.toEqual("hello") }
            }.toThrow<AssertionError> {
                expectSuppressedAndCauseInReporting()
                if (hasExtraHint) messageToContain("$toEqualDescr: \"hello\"")
            }
        }
    }

    @TestFactory
    fun notToThrow__shows_suppressed_including_cause_as_extra_hint() = testFactoryForFeatureNonFeature(
        notToThrowFeatureSpec, notToThrowSpec
    ) { name, notToThrowFun, hasExtraHint ->
        it("$name - shows suppressed including cause as extra hint" + showsSubExpectationIf(hasExtraHint)) {
            expect {
                expect<() -> Int> {
                    throw exceptionWithSuppressedWhichHasCause()
                }.notToThrowFun { toEqual(2) }
            }.toThrow<AssertionError> {
                expectSuppressedAndCauseInReporting()
                if (hasExtraHint) messageToContain("$toEqualDescr: 2")
            }
        }
    }

    private fun exceptionWithSuppressed(): UnsupportedOperationException =
        UnsupportedOperationException("not supported").apply {
            addSuppressed(IllegalStateException("not good"))
            addSuppressed(IllegalArgumentException(errMessage))
        }

    private fun exceptionWithSuppressedWhichHasCause(): UnsupportedOperationException =
        UnsupportedOperationException("not supported").apply {
            addSuppressed(IOException("io", IllegalStateException(errMessage)))
        }

    private fun Expect<AssertionError>.expectSuppressedInReporting() =
        message {
            toContainRegex(
                UnsupportedOperationException::class.simpleName + separator + messageAndStackTrace("not supported"),
                "\\s+\\Q$explanatoryBulletPoint\\E$supressedDescr: ${IllegalStateException::class.fullName}" +
                    messageAndStackTrace("not good"),
                "\\s+\\Q$explanatoryBulletPoint\\E$supressedDescr: ${IllegalArgumentException::class.fullName}" +
                    messageAndStackTrace(errMessage)
            )
        }

    private fun Expect<AssertionError>.expectSuppressedAndCauseInReporting() =
        message {
            toContainRegex(
                UnsupportedOperationException::class.simpleName + separator + messageAndStackTrace("not supported"),
                "\\s+\\Q$explanatoryBulletPoint\\E$supressedDescr: ${IOException::class.fullName}" +
                    messageAndStackTrace("io"),
                "\\s+\\Q$explanatoryBulletPoint\\E$causeDescr: ${IllegalStateException::class.fullName}" +
                    messageAndStackTrace(errMessage)
            )
        }
}
