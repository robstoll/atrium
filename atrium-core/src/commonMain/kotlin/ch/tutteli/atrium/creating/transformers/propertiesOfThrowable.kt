package ch.tutteli.atrium.creating.transformers

import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.core.polyfills.stackBacktrace
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.build
import ch.tutteli.atrium.creating.proofs.builders.AnyBuilder
import ch.tutteli.atrium.creating.proofs.builders.buildProof
import ch.tutteli.atrium.creating.transformers.impl.addAdditionalHints
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.reportables.Diagnostic
import ch.tutteli.atrium.reporting.reportables.InlineElement
import ch.tutteli.atrium.reporting.reportables.Reportable
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionThrowableProof.*
import ch.tutteli.kbox.takeIf


/**
 * Returns a [Reportable] showing properties of the given [throwable].
 *
 * @since 1.3.0
 */
@OptIn(ExperimentalComponentFactoryContainer::class)
fun ProofContainer<*>.propertiesOfThrowable(
    throwable: Throwable,
): Reportable {
    components.build<AtriumErrorAdjuster>().adjust(throwable)
    return buildProof {
        val description = inlineGroup(
            OCCURRED_EXCEPTION_PROPERTIES,
            Text.SPACE,
            Text(throwable::class.simpleName ?: throwable::class.fullName)
        )
        debugGroup(description) {
            addHints(throwable, secondStackFrameOfParent = null)
        }
    }
}

//TODO 1.3.0 shouldn't the receiver be AnyReportableGroupBuilder (same same for the one below)
/** @since 1.3.0 */
private fun AnyBuilder.addHints(
    throwable: Throwable,
    secondStackFrameOfParent: String?
) {
    addMessageHint(throwable)
    addStackTraceHint(throwable, secondStackFrameOfParent)
    addAdditionalHints(throwable)
    addCauseHint(throwable)
}

/** @since 1.3.0 */
private fun AnyBuilder.addMessageHint(throwable: Throwable) =
    row {
        column(OCCURRED_EXCEPTION_MESSAGE)
        column(Diagnostic.representation(throwable.message))
    }

/** @since 1.3.0 */
private fun AnyBuilder.addStackTraceHint(
    throwable: Throwable,
    secondStackFrameOfParent: String?
) {
    diagnosticGroup(OCCURRED_EXCEPTION_STACKTRACE, Text.EMPTY) {
        val stackTrace = if (secondStackFrameOfParent != null) {
            throwable.stackBacktrace.asSequence().takeWhile { it != secondStackFrameOfParent }
        } else {
            throwable.stackBacktrace.asSequence()
        }
        stackTrace.forEach {
            text(it)
        }
    }
}

/** @since 1.3.0 */
private fun AnyBuilder.addCauseHint(throwable: Throwable) {
    throwable.cause?.let { cause ->
        addChildHint(
            throwable, cause,
            OCCURRED_EXCEPTION_CAUSE
        )
    }
}

/**
 * Creates a hint for a given [child] of the given [throwable] using the given [childDescription].
 *
 * @since 1.3.0
 */
fun AnyBuilder.addChildHint(
    throwable: Throwable,
    child: Throwable,
    childDescription: InlineElement
) {
    val secondStackTrace = takeIf(throwable.stackBacktrace.size > 1) { throwable.stackBacktrace[1] }

    diagnosticGroup(childDescription, child) {
        addHints(child, secondStackTrace)
    }
}
