package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.RootProofGroup
import ch.tutteli.atrium.creating.proofs.SimpleProof
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionAnyProof
import ch.tutteli.kbox.takeIf
import org.opentest4j.AssertionFailedError

private const val poweredByAtrium = "\n\n===================== powered by Atrium"

fun extractExpected(causingProof: Proof): Any? =
    when (causingProof) {
        is RootProofGroup -> takeIf(causingProof.proofs.size == 1) {
            @Suppress("DEPRECATION")
            when (val first = causingProof.proofs.first()) {
                is SimpleProof ->
                    takeIf(first.description == DescriptionAnyProof.TO_EQUAL) {
                        first.representation.toString()
                    }

                is ch.tutteli.atrium.assertions.BasicDescriptiveAssertion ->
                    takeIf(first.description.getDefault() == "to equal") {
                        first.representation.toString()
                    }

                else -> null
            }?.let {
                if (it.contains("\n").not()) {
                    it + poweredByAtrium
                } else it
            }
        }

        else -> null
    }


fun extractActual(causingProof: Proof): Any? =
    when (causingProof) {
        is RootProofGroup -> takeIf(causingProof.proofs.size == 1) {
            @Suppress("DEPRECATION")
            when (val first = causingProof.proofs.first()) {
                is SimpleProof ->
                    takeIf(first.description == DescriptionAnyProof.TO_EQUAL) {
                        causingProof.representation.toString()
                    }

                is ch.tutteli.atrium.assertions.BasicDescriptiveAssertion ->
                    takeIf(first.description.getDefault() == "to equal") {
                        causingProof.representation.toString()
                    }

                else -> null
            }?.let {
                if (it.contains("\n").not()) {
                    it + poweredByAtrium
                } else it
            }
        }

        else -> null
    }

actual typealias AssertionErrorLikeIntermediate = java.lang.AssertionError
actual typealias AssertionErrorLike = AssertionFailedError

/**
 * Indicates that an expectation stated via Atrium was not.
 *
 * Its [stackTrace] might be filtered so that reporting does not include all stack frames.
 * This depends on the chosen [AtriumErrorAdjuster] - so theoretically more than the stack trace
 * could be adjusted.
 *
 * To create such an error you need to use the [AtriumError.Companion.create][Companion.create] function.
 */
actual class AtriumError internal actual constructor(
    message: String,
    actual val causingProof: Proof
) : AssertionFailedError(message, extractExpected(causingProof), extractActual(causingProof), null) {

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
            causingProof: Proof,
            atriumErrorAdjuster: AtriumErrorAdjuster
        ): AtriumError = createAtriumError(message, causingProof, atriumErrorAdjuster)
    }
}
