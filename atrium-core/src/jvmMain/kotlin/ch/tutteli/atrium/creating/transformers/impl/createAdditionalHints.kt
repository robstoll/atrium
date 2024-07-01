package ch.tutteli.atrium.creating.transformers.impl

import ch.tutteli.atrium.creating.transformers.impl.ThrowableThrownFailureHandler.Companion.addChildHint
import ch.tutteli.atrium.creating.proofs.AnyBuilder
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionThrowableProof

actual fun AnyBuilder.addAdditionalHints(
    throwable: Throwable
) {
    throwable.suppressed.forEach { suppressed ->
        addChildHint(
            throwable, suppressed,
            DescriptionThrowableProof.OCCURRED_EXCEPTION_SUPPRESSED
        )
    }
}
