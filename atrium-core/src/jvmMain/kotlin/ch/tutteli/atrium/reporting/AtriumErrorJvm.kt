package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.RootAssertionGroupType
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.kbox.takeIf
import org.opentest4j.AssertionFailedError

actual typealias AssertionErrorLikeIntermediate = java.lang.AssertionError
actual typealias AssertionErrorLike = AssertionFailedError

/**
 * Indicates that an assertion made by Atrium failed.
 *
 * Its [stackTrace] might be filtered so that reporting does not include all stack frames.
 * This depends on the chosen [AtriumErrorAdjuster] - so theoretically more than the stack trace
 * could be adjusted.
 *
 * To create such an error you need to use the [AtriumError.Companion.create][Companion.create] function.
 */
actual class AtriumError internal actual constructor(
    message: String,
    actual val rootAssertion: Assertion
) : AssertionFailedError(message, extractExpected(rootAssertion), extractActual(rootAssertion), null) {

    /**
     * Usually the error message but an empty string in case of certain test-runners.
     *
     * The Spek plugin for Intellij as well as running JUnit tests in IntelliJ print this message and
     * printStacktrace in addition which uses localizedMessage which in turn typically calls [message],
     * resulting in showing this message twice shortly after each other.
     * This hack combined with the changed behaviour in [getLocalizedMessage] works around this double error message
     * in reporting by setting the message to an empty string in case of the aforementioned runners.
     */
    override val message: String?
        get() {
            val isDoublePrintingTestRunner = Thread.currentThread().stackTrace[2].let {
                it.className.startsWith("org.jetbrains.spek.tooling.adapter.sm") ||
                    it.className.startsWith("org.spekframework.ide") ||
                    it.className == "org.gradle.internal.serialize.ExceptionPlaceholder" ||
                    it.className == "org.gradle.api.internal.tasks.testing.DefaultTestFailure"
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
        actual fun create(
            message: String,
            rootAssertion: Assertion,
            atriumErrorAdjuster: AtriumErrorAdjuster
        ): AtriumError = createAtriumError(message, rootAssertion, atriumErrorAdjuster)
    }
}

fun extractExpected(rootAssertion: Assertion): String = extractRepresentation(rootAssertion) { _, first ->
    first.representation
}

fun extractActual(rootAssertion: Assertion): String = extractRepresentation(rootAssertion) { root, _ ->
    root.representation
}

private fun extractRepresentation(
    rootAssertion: Assertion,
    representationProvider: (rootAssertionGroup: AssertionGroup, first: ch.tutteli.atrium.assertions.BasicDescriptiveAssertion) -> Any
): String {
    val expectedRepresentation = if (rootAssertion is AssertionGroup && rootAssertion.type == RootAssertionGroupType) {
        rootAssertion.assertions.singleOrNull()?.let { first ->
            if (first is ch.tutteli.atrium.assertions.BasicDescriptiveAssertion) {
                takeIf(first.description.getDefault() == "to equal") {
                    representationProvider(rootAssertion, first)
                }
            } else null
        }?.let { representation ->
            //TODO 1.5.0 we should use the ObjectFormatter here, good enough for now but should be improved
            val stringRepresentation = when (representation) {
                is Text -> representation.string
                else -> representation.toString()
            }
            stringRepresentation + """
                |
                |
                |===================== powered by Atrium:
                |type full name: ${representation::class.fullName(representation)}
                """
                .trimMargin()
        }
    } else null
    return expectedRepresentation ?: "please take a look at the exception message, no diff supported for this type of expectation for now"
}
