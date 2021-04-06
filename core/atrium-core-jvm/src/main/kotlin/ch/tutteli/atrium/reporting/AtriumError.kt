@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.reporting.AtriumError.Companion

/**
 * Indicates that an assertion made by Atrium failed.
 *
 * Its [stackTrace] might be filtered so that reporting does not include all stack frames.
 * This depends on the chosen [AtriumErrorAdjuster] - so theoretically more than the stack trace
 * could be adjusted.
 *
 * To create such an error you need to use the [AtriumError.Companion.create][Companion.create] function.
 */
actual class AtriumError internal actual constructor(message: String) : AssertionError(message, null) {

    /**
     * Usually the error message but an empty string in case of certain test-runners.
     *
     * The Spek plugin for Intellij as well as running JUnit tests in IntelliJ print this message and
     * printStacktrace in addition which uses localizedMessage which in turn usually calls message,
     * resulting in showing this message twice shortly after each other.
     * This hack combined with the changed behaviour in [getLocalizedMessage] works around this double error message
     * in reporting by setting the message to an empty string in case of the aforementioned runners.
     */
    override val message: String?
        get() {
            val isDoublePrintingTestRunner = Thread.currentThread().stackTrace[2].let {
                it.className.startsWith("org.jetbrains.spek.tooling.adapter.sm") ||
                    it.className.startsWith("org.spekframework.ide") ||
                    it.className == "org.gradle.internal.serialize.ExceptionPlaceholder"
            }
            return if (isDoublePrintingTestRunner) {
                ""
            } else {
                super.message
            }
        }

    /**
     * Returns `super.message` in order to be not affected by the hack implemented in message
     */
    override fun getLocalizedMessage(): String? = super.message

    /**
     * Returns first [getLocalizedMessage] and then the qualified name of this exception.
     *
     * Which has the effect that printStackTrace will show first the error message and then qualified name including
     * stacktrace - resulting in a tidier report 😊
     *
     * One unwanted effect, we show the qualified name even if someone has chosen the following for the gradle runner
     * showExceptions=true, showStacktrace=false => however, I think that's fine.
     */
    override fun toString(): String = localizedMessage + "\n\n" + this::class.fullName

    actual companion object {
        /**
         * * Creates an [AtriumError] and adjusts it with the given [atriumErrorAdjuster] before it is returned
         * (adjusting might filter the [stackTrace]).
         */
        actual fun create(message: String, atriumErrorAdjuster: AtriumErrorAdjuster): AtriumError =
            createAtriumError(message, atriumErrorAdjuster)
    }
}
