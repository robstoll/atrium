package ch.tutteli.atrium.creating.transformers.impl

import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.core.polyfills.stackBacktrace
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.creating.proofs.AnyBuilder
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.transformers.SubjectChanger
import ch.tutteli.atrium.creating.proofs.buildProof
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.reportables.InlineElement
import ch.tutteli.atrium.reporting.reportables.Reportable
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionThrowableProof.*
import ch.tutteli.kbox.takeIf

class ThrowableThrownFailureHandler<SubjectT : Throwable?, SubjectAfterChangeT> :
    SubjectChanger.FailureHandler<SubjectT, SubjectAfterChangeT> {

    override fun createProof(
        container: ProofContainer<SubjectT>,
        proof: Proof,
        maybeExpectationCreatorWithUsageHints: Option<ExpectationCreatorWithUsageHints<SubjectAfterChangeT>>
    ): Proof = container.buildProof {
        invisibleGroup {
            add(proof)
            maybeExpectationCreatorWithUsageHints.fold({ /* nothing to do */ }) { expectationCreatorWithUsageHints ->
                explanatoryGroup {
                    collect(expectationCreatorWithUsageHints)
                }
            }
            container.maybeSubject.fold(
                { /* nothing to do */ },
                {
                    if (it != null) add(container.propertiesOfThrowable(it))
                }
            )
        }
    }


    companion object {

        /**
         * Returns a [Reportable] showing properties of the given [throwable].
         */
        @OptIn(ExperimentalComponentFactoryContainer::class)
        fun ProofContainer<*>.propertiesOfThrowable(
            throwable: Throwable,
        ): Reportable {
            components.build<AtriumErrorAdjuster>().adjust(throwable)
            return buildProof {
                val description = Reportable.inlineGroup(
                    listOf(
                        OCCURRED_EXCEPTION_PROPERTIES,
                        Text(throwable::class.simpleName ?: throwable::class.fullName)
                    )
                )
                debugGroup(description) {
                    addHints(throwable, secondStackFrameOfParent = null)
                }
            }
        }

        private fun AnyBuilder.addHints(
            throwable: Throwable,
            secondStackFrameOfParent: String?
        ) {
            addMessageHint(throwable)
            addStackTraceHint(throwable, secondStackFrameOfParent)
            addAdditionalHints(throwable)
            addCauseHint(throwable)
        }

        private fun AnyBuilder.addMessageHint(throwable: Throwable) =
            inlineGroup(OCCURRED_EXCEPTION_MESSAGE, throwable.message?.let { Text(it) } ?: Text.NULL)

        private fun AnyBuilder.addStackTraceHint(
            throwable: Throwable,
            secondStackFrameOfParent: String?
        ) {
            reportableGroup(OCCURRED_EXCEPTION_STACKTRACE, Text.EMPTY) {
                val stackTrace = if (secondStackFrameOfParent != null) {
                    throwable.stackBacktrace.asSequence().takeWhile { it != secondStackFrameOfParent }
                } else {
                    throwable.stackBacktrace.asSequence()
                }
                stackTrace.forEach {
                    add(Text(it))
                }
            }
        }


        private fun AnyBuilder.addCauseHint(throwable: Throwable) {
            throwable.cause?.let { cause -> addChildHint(throwable, cause, OCCURRED_EXCEPTION_CAUSE) }
        }

        /**
         * Creates a hint for a given [child] of the given [throwable] using the given [childDescription].
         */
        fun AnyBuilder.addChildHint(
            throwable: Throwable,
            child: Throwable,
            childDescription: InlineElement
        ) {
            val secondStackTrace = takeIf(throwable.stackBacktrace.size > 1) { throwable.stackBacktrace[1] }

            reportableGroup(childDescription, Text.EMPTY) {
                addHints(child, secondStackTrace)
            }
        }
    }
}

/**
 * Hook for platform specific additional hints.
 */
expect fun AnyBuilder.addAdditionalHints(throwable: Throwable)
